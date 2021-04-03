package com.example.caracample.model;

public class LedVO {

    private String bath;
    private String kit;
    private String liv;
    private String outdoor;

    public LedVO(){

    }

    public LedVO(String bath, String kit, String liv, String outdoor) {
        this.bath = bath;
        this.kit = kit;
        this.liv = liv;
        this.outdoor = outdoor;
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bath) {
        this.bath = bath;
    }

    public String getKit() {
        return kit;
    }

    public void setKit(String kit) {
        this.kit = kit;
    }

    public String getLiv() {
        return liv;
    }

    public void setLiv(String liv) {
        this.liv = liv;
    }

    public String getOutdoor() {
        return outdoor;
    }

    public void setOutdoor(String outdoor) {
        this.outdoor = outdoor;
    }

    @Override
    public String toString() {
        return "led{" +
                "bath='" + bath + '\'' +
                ", kit='" + kit + '\'' +
                ", liv='" + liv + '\'' +
                ", outdoor='" + outdoor + '\'' +
                '}';
    }
}
