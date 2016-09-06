package ru.megafon.logic;

import scala.Serializable;

import java.util.Date;

/**
 * Created by Gevorg.Khachaturyan on 01.09.2016.
 */
public class GPS_FILE  implements Serializable {

    String msisdn;
    String subs_id;
    String pay_source;
    String user_status;
    Date last_pay;

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

    public String getPay_source() {
        return pay_source;
    }

    public void setPay_source(String pay_source) {
        this.pay_source = pay_source;
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
}
