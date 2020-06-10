package com.example.androidclass.plant_db;

public class PlantItem {
    private int id;
    private String curTitle;
    private String curUrl;

    public PlantItem() {
        this.curTitle = "";
        this.curUrl = "";
    }

    public PlantItem(String curTitle, String curUrl) {
        this.curTitle = curTitle;
        this.curUrl = curUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurTitle() {
        return curTitle;
    }

    public void setCurTitle(String curTitle) {
        this.curTitle = curTitle;
    }

    public String getCurUrl() {
        return curUrl;
    }

    public void setCurUrl(String curUrl) {
        this.curUrl = curUrl;
    }
}
