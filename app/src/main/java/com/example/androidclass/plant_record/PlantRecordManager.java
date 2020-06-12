package com.example.androidclass.plant_record;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidclass.plant_db.PlantDBHelper;
import com.example.androidclass.plant_db.PlantItem;

import java.util.ArrayList;
import java.util.List;

public class PlantRecordManager {
    private PlantDBHelper plantDBHelper;
    private String PRNAME;

    public PlantRecordManager(Context context){
        plantDBHelper=new PlantDBHelper(context);
        PRNAME=plantDBHelper.PR_NAME;
    }

    public boolean add(PlantRecordBean bean){
        SQLiteDatabase db=plantDBHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("curcontent",bean.getContent());
        values.put("curtime",bean.getTime());
        long result=db.insert(PRNAME,null,values);
        if(result>0){
            return true;
        }
        return false;
    }

    public void addAll(List<PlantRecordBean> beans){
        SQLiteDatabase db=plantDBHelper.getWritableDatabase();
        for(PlantRecordBean bean:beans){
            ContentValues values=new ContentValues();
            values.put("curcontent",bean.getContent());
            values.put("curtime",bean.getTime());
            db.insert(PRNAME,null,values);
        }
        db.close();
    }

    public boolean delete(int id){
        SQLiteDatabase db=plantDBHelper.getWritableDatabase();
        long result=db.delete(PRNAME,"id=?",new String[]{String.valueOf(id)});
        if(result>0){
            return true;
        }
        return false;
    }

    public boolean update(PlantRecordBean bean){
        SQLiteDatabase db=plantDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("curcontent", bean.getContent());
        values.put("curtime", bean.getTime());
        int result =db.update(PRNAME, values,"id=?",new String[]{bean.getId()+""});
        if (result > 0) {
            return true;
        }
        return false;
    }

    public List<PlantRecordBean> showAll(){
        List<PlantRecordBean> plantRecord=null;
        SQLiteDatabase db=plantDBHelper.getReadableDatabase();
        Cursor cursor=db.query(PRNAME,null,null,null,null,null,null);
        if(cursor!=null){
            //实列化，分配空间
            plantRecord = new ArrayList<PlantRecordBean>();
            //移到下一行
            while (cursor.moveToNext()){
                PlantRecordBean bean=new PlantRecordBean();
                //添加行数据
                bean.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                bean.setContent(cursor.getString(cursor.getColumnIndex("CURCONTENT")));
                bean.setTime(cursor.getString(cursor.getColumnIndex("CURTIME")));
                plantRecord.add(bean);
            }
            cursor.close();
        }
        db.close();
        return plantRecord;
    }
}
