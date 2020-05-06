package com.example.androidclass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MyList2Activity extends ListActivity {
    Handler handler;
    private ArrayList<HashMap<String,String>>listItems;//存放文字，图片信息
    private SimpleAdapter listItemAdapter;//适配器
    private  int msgWhat=7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        this.setListAdapter(listItemAdapter);
    }

    private  void initListView(){
        listItems=new ArrayList<HashMap<String, String>>();
        for(int i=0;i<10;i++){
            HashMap<String,String> map=new HashMap<String, String>();//创建一个对象，向里面放数据
            map.put("ItemTitle","Rate:"+i);//标题文字，key不能重复
            map.put("ItemDetail","detail"+i);//详情描述
            listItems.add(map);//加入数据
        }
        //生成适配器的item和动态数组相对应的元素
        listItemAdapter=new SimpleAdapter(this,listItems,//数据源
                R.layout.list_item,//listItem的xml布局实现
                new String[]{"ItemTitle","ItemDetail"},//放到布局的控件中，一一匹配的关系
                new int[]{R.id.itemTitle,R.id.itemDetail});
    }
}
