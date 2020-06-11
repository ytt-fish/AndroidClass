package com.example.androidclass.plant_record;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlantRecordBean {
    private int id;
    private String content;
    private String time;

    public PlantRecordBean(){

    }

    public PlantRecordBean(Integer id,String content,String time){
        this.id=id;
        this.content=content;
        this.time=time;
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
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(new Date());

    }

    public void setTime(String time) {
        this.time = time;
    }


}
