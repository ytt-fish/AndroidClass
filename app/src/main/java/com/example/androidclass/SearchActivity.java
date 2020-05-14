package com.example.androidclass;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class SearchActivity extends AppCompatActivity implements Runnable {
    private final String TAG = "SearchActivity";

    Handler handler;
    EditText keywords;
    Float updateDate;
    String result;
    String data[]={"wait...."};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.activity_search);

        final List<String> list1 = new ArrayList<String>();
        for (int i = 1; i < 100; i++) {
            list1.add("item" + i);
        }

        ListAdapter adapter = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listView=findViewById(R.id.result);
        listView.setAdapter(adapter);

        SharedPreferences sharedPreferences=getSharedPreferences("search", Activity.MODE_PRIVATE);
        updateDate=sharedPreferences.getFloat("update_date",0.0f);
        result=sharedPreferences.getString("txt","");

        keywords=findViewById(R.id.keywords);
        keywords.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0) {
                    String ss[]=result.split(",");
                    int length=ss.length;
                    List<String> res=new ArrayList<String>();
                    for(int i=0;i<length;i++) {
                        if (ss[i].contains(s)) {
                            res.add(ss[i]);
                        }
                    }
                    ListAdapter adapter = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1,res);
                    ListView listView = findViewById(R.id.result);
                    listView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(SearchActivity.this,"搜索不到内容",Toast.LENGTH_LONG).show();
                }
            }
        });


//
//        //获取当前系统时间
//        Date today=Calendar.getInstance().getTime();
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        final String todayStr=sdf.format(today);
//        String todayStr1=todayStr.substring(8);
//        final Float a=Float.parseFloat(todayStr1);
//        //Log.i(TAG,"onCreate:todaystr"+todayStr+todayStr1);
////
////        //判断时间
//        if(Math.abs(updateDate-a)>7){
//            Log.i(TAG,"onCreate:需要更新");
            Thread t=new Thread(this);
            t.start();
//        }else{
//            Log.i(TAG,"onCreate:不需要更新");
//        }
//

        handler = new Handler() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==5){
                    List<String> list2=(List<String>)msg.obj;
                    String find=String.join(",",list2);
                    Log.i("TAG","find:"+find);

                    SharedPreferences sharedPreferences=getSharedPreferences("search",Activity.MODE_PRIVATE);
                    //获得editor对象,将新的数据保存在配置文件中
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    //editor.putFloat("update_date",a);
                    editor.putString("txt",find);
                    editor.apply();
                }
            }
        };
    }

    @Override
        public void run() {
            Log.i(TAG, "run:run()....");
            List<String> retList=new ArrayList<String>();
            Document doc = null;
            try {
                Thread.sleep(3000);
                doc = Jsoup.connect("https://it.swufe.edu.cn/index/tzgg.htm").get();
                Log.i(TAG, "run:" + doc.title());
                Elements spans = doc.getElementsByTag("span");
                for(int j=7;j<=45;j+=2){
                    Element titles = spans.get(j);
                    Log.i(TAG, "run:" + titles.text());
                    String txt=titles.text();
                    retList.add(txt);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            Message msg = handler.obtainMessage(5);
            //填写msg对象的内容
            msg.obj = retList;
            //传回主线程
            handler.sendMessage(msg);
    }
}
