package com.example.androidclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RateManager {
    private DBHelper dbHelper;
    private String TBNAME;

    public RateManager(Context context){
        dbHelper=new DBHelper(context);
        TBNAME=DBHelper.TB_NAME;
    }

    public void add(RateItem item){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("curname",item.getCurName());
        values.put("currate",item.getCurRate());
        db.insert(TBNAME,null,values);
        db.close();
    }

    public void deleteAll(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete("TBNAME",null,null);
        db.close();
    }

    public void addAll(List<RateItem> list){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        for(RateItem item:list){
            ContentValues values=new ContentValues();
            values.put("curname",item.getCurName());
            values.put("currate",item.getCurRate());
            db.insert(TBNAME,null,values);
        }
        db.close();
    }

    public List<RateItem> listAll(){
        List<RateItem> rateList=null;
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        //光标
        Cursor cursor=db.query(TBNAME,null,null,null,null,null,null);
        if(cursor!=null){
            //实列化，分配空间
            rateList = new ArrayList<RateItem>();
            //移到下一行
            while (cursor.moveToNext()){
                RateItem item=new RateItem();
                //添加行数据
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setCurName(cursor.getString(cursor.getColumnIndex("CURNAME")));
                item.setCurRate(cursor.getString(cursor.getColumnIndex("CURRATE")));
                rateList.add(item);
            }
            cursor.close();
        }
        db.close();
        return rateList;
    }

    public void update(RateItem item){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("curname",item.getCurName());
        values.put("currate",item.getCurRate());
        db.update(TBNAME,values,"ID=?",new String[]{String.valueOf(item.getId())});
        db.close();
    }

    public RateItem findById(int id){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query(TBNAME,null,"ID=?",new String[]{String.valueOf(id)},null,null,null);
        RateItem rateItem=null;
        if(cursor!=null&&cursor.moveToFirst()){
            rateItem=new RateItem();
            rateItem.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            rateItem.setCurName(cursor.getString(cursor.getColumnIndex("CURNAME")));
            rateItem.setCurRate(cursor.getString(cursor.getColumnIndex("CURRATE")));
            cursor.close();
        }
        db.close();
        return  rateItem;
    }



}
