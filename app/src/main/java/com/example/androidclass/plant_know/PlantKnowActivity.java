package com.example.androidclass.plant_know;

import androidx.annotation.RequiresApi;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.androidclass.plant_db.PlantItem;
import com.example.androidclass.plant_db.PlantManager;

public class PlantKnowActivity extends ListActivity implements Runnable, AdapterView.OnItemClickListener {
    String[] data ={"wait...."};
    private String logDate="";
    private  final String DATE_SP_KEY="lastRateDateStr";
    Handler handler;
    String TAG= "PlantKnowActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_plant_know);

        SharedPreferences sp=getSharedPreferences("plant", Context.MODE_PRIVATE);
        logDate=sp.getString(DATE_SP_KEY,"");
        Log.i("List","lastRateDateStr="+logDate);

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);

        Thread t = new Thread(this);
        t.start();


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==5){
                    List<String> list=(List<String>)msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(PlantKnowActivity.this, android.R.layout.simple_list_item_1, list);
                    setListAdapter(adapter);
                }

            }
        };
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void run() {
        Log.i(TAG,"run...");
        List<String> plantList=new ArrayList<String>();
        String curDateStr=(new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        Log.i("run","curDateStr:"+"logDatr:"+logDate);

        if(curDateStr.equals(logDate)) {
            //如果时间相等，，则不从网络中获取数据
            Log.i("run:", "日期相等：从数据库中获取数据");
            PlantManager manager = new PlantManager(PlantKnowActivity.this);
            for (PlantItem item : manager.listAll()) {
                plantList.add(item.getCurTitle());
            }
        }else {
            Log.i("run", "日期不相等：从网络中获取数据");
            Document doc = null;
            try {
                List<PlantItem> plList=new ArrayList<PlantItem>();
                doc = Jsoup.connect("https://www.zhiwutong.com/science/index.htm").get();
                Elements tables = doc.getElementsByTag("li");
                //int i=1;
//            for(Element table:tables) {
//                //Log.i(TAG, "run:table["+i+"]" + table);
//                i++;
//            }
                for (int j = 35; j < 85; j++) {
                    Element want = tables.get(j);
                    Log.i(TAG, "run:table[" + j + "]" + want);

                    String title = want.select("a").text();
                    Log.i(TAG, "run:title[" + j + "]" + title);

                    String url = want.select("a").attr("href");
                    Log.i(TAG, "run:url[" + j + "]" + url);

                    plantList.add(title);
                    plList.add(new PlantItem(title,url));
                }

                //把数据写入到数据库中
                PlantManager manager=new PlantManager(PlantKnowActivity.this);
                manager.deleteAll();
                Log.i(TAG,"删除记录");
                manager.addAll(plList);
                Log.i(TAG,"添加新纪录");

            } catch (Exception e) {
                e.printStackTrace();
            }
            //更新记录日期
            SharedPreferences sp=getSharedPreferences("plant",Context.MODE_PRIVATE);
            SharedPreferences.Editor edit=sp.edit();
            edit.putString(DATE_SP_KEY,curDateStr);
            edit.commit();
            Log.i("run","更新日期结束："+curDateStr);
        }

        Message msg = handler.obtainMessage(5);
        //填写msg对象的内容
        msg.obj = plantList;
        //传回主线程
        handler.sendMessage(msg);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        List<String> pList=new ArrayList<String>();
        PlantManager manager = new PlantManager(PlantKnowActivity.this);
        for (PlantItem item : manager.listAll()) {
            //Log.i(TAG,"url"+"\"https://"+ item.getCurUrl().substring(2)+"\"");
            pList.add("https://"+ item.getCurUrl().substring(2));
        }
        Uri uri = Uri.parse(pList.get(position));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
