package com.example.rxjavatest;

/**
 * Created by shuwei on 2019/1/19.
 */

public class DataBean {
    private String date;
    private String huid;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHuid() {
        return huid;
    }

    public void setHuid(String huid) {
        this.huid = huid;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "date='" + date + '\'' +
                ", huid='" + huid + '\'' +
                '}';
    }
}
