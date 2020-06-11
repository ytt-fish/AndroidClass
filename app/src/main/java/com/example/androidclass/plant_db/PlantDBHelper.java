package com.example.androidclass.plant_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PlantDBHelper extends SQLiteOpenHelper{
    private static final int VERSION=1;
    private static final String DB_NAME="myplant.db";//数据库库名
    public static final String PB_NAME="pb_plants";//数据库表名
    public static final String PR_NAME="pr_plants";

    public PlantDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public PlantDBHelper(Context context){
        super(context,DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+PB_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,CURTITLE TEXT,CURURL TEXT)");
        db.execSQL("CREATE TABLE "+PR_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,CURCONTENT TEXT,CURTIME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE "+PB_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,CURTITLE TEXT,CURURL TEXT)");
        db.execSQL("CREATE TABLE "+PR_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,CURCONTENT TEXT,CURTIME TEXT)");
        onCreate(db);

    }
}
