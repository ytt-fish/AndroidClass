package com.example.androidclass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.example.androidclass.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class PlantKnowActivity extends ListActivity implements Runnable {
    String data[]={"wait...."};
    private String logDate="";
    private  final String DATE_SP_KEY="lastRateDateStr";
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_plant_know);

        SharedPreferences sp=getSharedPreferences("plant", Context.MODE_PRIVATE);

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);

        Thread t = new Thread(this);
        t.start();


    }

    @Override
    public void run() {
        List<String> retList=new ArrayList<String>();
        Document doc = null;
        try{
            doc = Jsoup.connect("https://www.zhiwutong.com/science/index.htm").get();
            Elements tables = doc.getElementsByTag("li");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
