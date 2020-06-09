package com.example.androidclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidclass.classtraining.RateItem;

import java.util.ArrayList;
import java.util.List;

public class PlantManager {
    private PlantDBHelper plantDBHelper;
    private String PBNAME;

    public PlantManager(Context context){
        plantDBHelper=new PlantDBHelper(context);
        PBNAME=plantDBHelper.PB_NAME;
    }

    public void add(PlantItem item){
        SQLiteDatabase db=plantDBHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("curname",item.getCurTitle());
        values.put("currate",item.getCurUrl());
        db.insert(PBNAME,null,values);
        db.close();
    }

    public void addAll(List<PlantItem> list){
        SQLiteDatabase db=plantDBHelper.getWritableDatabase();
        for(PlantItem item:list){
            ContentValues values=new ContentValues();
            values.put("curtitle",item.getCurTitle());
            values.put("cururl",item.getCurUrl());
            db.insert(PBNAME,null,values);
        }
        db.close();
    }

    public void deleteAll(){
        SQLiteDatabase db=plantDBHelper.getWritableDatabase();
        db.delete(PBNAME,null,null);
        db.close();
    }

    public List<PlantItem> listAll(){
        List<PlantItem> plantList=null;
        SQLiteDatabase db=plantDBHelper.getReadableDatabase();
        //光标
        Cursor cursor=db.query(PBNAME,null,null,null,null,null,null);
        if(cursor!=null){
            //实列化，分配空间
            plantList = new ArrayList<PlantItem>();
            //移到下一行
            while (cursor.moveToNext()){
                PlantItem item=new PlantItem();
                //添加行数据
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setCurTitle(cursor.getString(cursor.getColumnIndex("CURTITLE")));
                item.setCurUrl(cursor.getString(cursor.getColumnIndex("CURURL")));
                plantList.add(item);
            }
            cursor.close();
        }
        db.close();
        return plantList;
    }
}
