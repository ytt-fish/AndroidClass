package com.example.androidclass.plant_record;

public class PlantRecordBean {
    private int id;
    private String content;
    private String time;

    public PlantRecordBean(){

    }

    public PlantRecordBean(String content,String time){
        this.content=content;
        this.time=time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
