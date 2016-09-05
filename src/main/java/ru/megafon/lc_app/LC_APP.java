package ru.megafon.lc_app;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

import ru.megafon.logic.HUB_FILE;

/**
 * Created by Gevorg.Khachaturyan on 01.09.2016.
 */
public class LC_APP {
    public static void main (String[] args){

        // Get properties

        final Properties file_prop = new Properties();
        final Properties sql_prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("file.properties");
            file_prop.load(input);
            input = new FileInputStream("file.properties");
            sql_prop.load(input);
        } catch (IOException ee) {
            ee.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        SparkConf conf = new SparkConf().setAppName("lc_app").setMaster(file_prop.getProperty("spark_master"));
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new org.apache.spark.sql.SQLContext(sc);
        DataFrame schemaHUB = null;
        final HUB_FILE hub_file = new HUB_FILE();

        try {
            JavaRDD<HUB_FILE> hub_val = sc.textFile(file_prop.getProperty("get_folder_name") + "/" + file_prop.getProperty("get_file_name")).map(
                    new Function<String, HUB_FILE>() {
                        public HUB_FILE call(String line) throws Exception {
                            String[] parts = line.split(file_prop.getProperty("file_delimiter"));

                            int counter = 0;
                            for (Field field : hub_file.getClass().getDeclaredFields()) {
                                field.setAccessible(true);
                                try {
                                    field.set(hub_file, parts[counter]);
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    field.set(hub_file, "");
                                }
                                counter++;
                            }
                            return hub_file;
                        }
                    });

            // Get count of unic msisdn with fiilter by status and tarif
            Integer  unique_msisdn = (int) (long) hub_val.filter(new Function<HUB_FILE, Boolean>() {
                public Boolean call(HUB_FILE hub_file) throws Exception {
                    return hub_file.getUser_status().equals("")&&hub_file.getTarif().equals("");
                }
            }).map(new Function<HUB_FILE, String>() {
                       public String call(HUB_FILE hub_file) throws Exception {
                           return hub_file.getMsisdn();
                       }
                   }).distinct().count();

            // get filter by tarif
            Long sensors_msisdn = hub_val.filter(new Function<HUB_FILE, Boolean>() {
                public Boolean call(HUB_FILE hub_file) throws Exception {
                    return hub_file.getTarif().equals("");
                }
            }).map(new Function<HUB_FILE, Integer>() {
                    public Integer call(HUB_FILE hub_file) throws Exception {
                    return hub_file.getSensors();
                }
            }).reduce(new Function2<Integer, Integer, Integer>() {
                public Integer call(Integer a, Integer b) { return a + b; }
            })/hub_val.filter(new Function<HUB_FILE, Boolean>() {
                public Boolean call(HUB_FILE hub_file) throws Exception {
                    return hub_file.getTarif().equals("");
                }
            }).map(new Function<HUB_FILE, Object>() {
                public String call(HUB_FILE hub_file) throws Exception {
                    return hub_file.getMsisdn();
                }
            }).distinct().count();

            System.out.print("unique_msisdn: "+unique_msisdn);
            System.out.println("; sensors_msisdn: "+sensors_msisdn);

            //schemaHUB = sqlContext.createDataFrame(hub_val, HUB_FILE.class);
            //schemaHUB.saveAsParquetFile(file_prop.getProperty("put_folder_name"));
        }finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}
