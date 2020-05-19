package com.example.androidclass;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyList2Activity extends ListActivity implements Runnable, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    Handler handler;
    private ArrayList<HashMap<String,String>>listItems;//存放文字，图片信息
    private SimpleAdapter listItemAdapter;//适配器
    private  int msgWhat=7;
    private  final  String TAG="MyList2Activity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        //MyAdapter myAdapter=new MyAdapter(this,R.layout.list_item,listItems);
        //this.setListAdapter(myAdapter);
        this.setListAdapter(listItemAdapter);

        //开启子线程，访问网络资源一定不能在主线程中，当前对象，会寻找run方法
        Thread t=new Thread(this);
        t.start();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==7){
                    List<HashMap<String,String>> list2=(List<HashMap<String, String>>)msg.obj;
                    listItemAdapter=new SimpleAdapter(MyList2Activity.this,list2,//数据源
                            R.layout.list_item,//listItem的xml布局实现
                            new String[]{"ItemTitle","ItemDetail"},//放到布局的控件中，一一匹配的关系
                            new int[]{R.id.itemTitle,R.id.itemDetail});


                }
                setListAdapter(listItemAdapter);
            }
        };
        //获取控件,点击事件的监听
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this);
    }

    private  void initListView(){
        listItems=new ArrayList<HashMap<String, String>>();
        for(int i=0;i<10;i++){
            HashMap<String,String> map=new HashMap<String, String>();//创建一个对象，向里面放数据
            map.put("ItemTitle","Rate:"+i);//标题文字，key不能重复
            map.put("ItemDetail",""+i);//详情描述
            listItems.add(map);//加入数据
        }
        //生成适配器的item和动态数组相对应的元素
        listItemAdapter=new SimpleAdapter(this,listItems,//数据源
                R.layout.list_item,//listItem的xml布局实现
                new String[]{"ItemTitle","ItemDetail"},//放到布局的控件中，一一匹配的关系
                new int[]{R.id.itemTitle,R.id.itemDetail});
    };

    @Override
    public void run() {
        //获取网络数据，放入list带回到主线程中
        List<HashMap<String,String>> retList=new ArrayList<HashMap<String, String>>();
        Document doc = null;
        try {
            Thread.sleep(3000);
            doc = Jsoup.connect("http://www.usd-cny.com/bankofchina.htm").get();
            //Log.i(TAG, "run:" + doc.title());
            Elements tables = doc.getElementsByTag("table");
            Element table1 = tables.get(0);
            Elements tds = table1.getElementsByTag("td");
            for (int i = 0; i < tds.size(); i += 6) {
                Element td1 = tds.get(i);
                Element td2 = tds.get(i + 5);

                String str1 = td1.text();
                String val = td2.text();

                Log.i(TAG,"run:"+str1+"==>"+val);
                HashMap<String,String> map=new HashMap<String, String>();
                map.put("ItemTitle",str1);
                map.put("ItemDetail",val);
                retList.add(map);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        Message msg = handler.obtainMessage(7);
        //填写msg对象的内容
        msg.obj = retList;
        //传回主线程
        handler.sendMessage(msg);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //parent-adapter对象
        Log.i(TAG,"onItemClick:parent"+parent);
        Log.i(TAG,"onItemClick:view:"+view);
        Log.i(TAG,"onClickItem:position"+position);
        Log.i(TAG,"onClickItem:id"+id);
        //从map中获得
        HashMap<String,String> map= (HashMap<String,String>)getListView().getItemAtPosition(position);
        String titleStr=map.get("ItemTitle");
        String detailStr=map.get("ItemDetail");
        Log.i(TAG,"onItemClick:titleStr"+titleStr);
        Log.i(TAG,"onItemClick:detailStr"+detailStr);
        //从控件中获得
        TextView title=view.findViewById(R.id.itemTitle);
        TextView detail=view.findViewById(R.id.itemDetail);
        String title2=String.valueOf(title.getText());
        String detail2=String.valueOf(detail.getText());
        Log.i(TAG,"onClick:title2"+title2);
        Log.i(TAG,"onItemClick:detail2"+detail2);

        //打开新的页面传入参数
        Intent rateCalc=new Intent(this,RateCalcActivity.class);
        rateCalc.putExtra("title",titleStr);
        rateCalc.putExtra("rate",Float.parseFloat(detailStr));
        startActivity(rateCalc);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG,"onItemLongclick:长按："+position);
        return false;
    }
}
