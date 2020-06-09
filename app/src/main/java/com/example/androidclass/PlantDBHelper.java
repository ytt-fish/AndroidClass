package com.example.androidclass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PlantDBHelper extends SQLiteOpenHelper{
    private static final int VERSION=1;
    private static final String DB_NAME="myplant.db";//数据库库名
    public static final String PB_NAME="pb_plants";//数据库表名

    public PlantDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public PlantDBHelper(Context context){
        super(context,DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+PB_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,CURTITLE TEXT,CURURL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO Auto-generated method stub

    }
}
