package com.example.caracample.model;

public class QnAVO {

    private int num;
    private String time;
    private String car_name;
    private String cmt;
    private String tel;

    public QnAVO() {
    }

    public QnAVO(int num, String time, String car_name, String cmt, String tel) {
        this.num = num;
        this.time = time;
        this.car_name = car_name;
        this.cmt = cmt;
        this.tel = tel;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "QnAVO{" +
                "num=" + num +
                ", time='" + time + '\'' +
                ", car_name='" + car_name + '\'' +
                ", cmt='" + cmt + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
