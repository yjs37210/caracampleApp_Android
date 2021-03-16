package com.example.caracample;

public class CustomerVO {

    private int num;
    private String name;
    private String tel;
    private String car_name;
    private int male;
    private int female;
    private String check_in;
    private String check_out;

    public CustomerVO() {
    }

    public CustomerVO(int num, String name, String tel, String car_name, int male, int female, String check_in, String check_out) {
        this.num = num;
        this.name = name;
        this.tel = tel;
        this.car_name = car_name;
        this.male = male;
        this.female = female;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public int getMale() {
        return male;
    }

    public void setMale(int male) {
        this.male = male;
    }

    public int getFemale() {
        return female;
    }

    public void setFemale(int female) {
        this.female = female;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    @Override
    public String toString() {
        return "CustomerVO{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", car_name='" + car_name + '\'' +
                ", male=" + male +
                ", female=" + female +
                ", check_in='" + check_in + '\'' +
                ", check_out='" + check_out + '\'' +
                '}';
    }
}
