package ru.megafon.logic;


import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

import java.util.Date;

/**
 * Created by Gevorg.Khachaturyan on 01.09.2016.
 */
public class HUB_FILE {

    String msisdn;
    String subs_id;
    String sn_hub;
    String tarif;
    Integer sensors;
    Integer camcoders;
    String cam_status;
    String pay_source;
    Integer storege_mode;
    String user_status;
    Date last_pay;
    String alyt_id;
    String ivideon_id;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getSubs_id() {
        return subs_id;
    }

    public void setSubs_id(String subs_id) {
        this.subs_id = subs_id;
    }

    public String getSn_hub() {
        return sn_hub;
    }

    public void setSn_hub(String sn_hub) {
        this.sn_hub = sn_hub;
    }

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public Integer getSensors() {
        return sensors;
    }

    public void setSensors(Integer sensors) {
        this.sensors = sensors;
    }

    public Integer getCamcoders() {
        return camcoders;
    }

    public void setCamcoders(Integer camcoders) {
        this.camcoders = camcoders;
    }

    public String getCam_status() {
        return cam_status;
    }

    public void setCam_status(String cam_status) {
        this.cam_status = cam_status;
    }

    public String getPay_source() {
        return pay_source;
    }

    public void setPay_source(String pay_source) {
        this.pay_source = pay_source;
    }

    public Integer getStorege_mode() {
        return storege_mode;
    }

    public void setStorege_mode(Integer storege_mode) {
        this.storege_mode = storege_mode;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public Date getLast_pay() {
        return last_pay;
    }

    public void setLast_pay(Date last_pay) {
        this.last_pay = last_pay;
    }

    public String getAlyt_id() {
        return alyt_id;
    }

    public void setAlyt_id(String alyt_id) {
        this.alyt_id = alyt_id;
    }

    public String getIvideon_id() {
        return ivideon_id;
    }

    public void setIvideon_id(String ivideon_id) {
        this.ivideon_id = ivideon_id;
    }

}
