package com.example.androidclass.plant_grid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.androidclass.PlantDesActivity;
import com.example.androidclass.PlantGridDesActivity;
import com.example.androidclass.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlantGridActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    GridView gridView;
    String TAG="PlantGridActivity";
    String[] plantN1 = new String[] {"蓝雪花", "长寿花","风车茉莉","玛格丽特花","月季花","栀子花","杜鹃","桂花"};
    String[] plantD1 = new String[] {"白花丹科","石蒜科","夹竹桃科","菊科","蔷薇科","茜草科","杜鹃花科","木犀科"};
    private List<HashMap<String,String>> listItems1;//存放文字，图片信息
    private SimpleAdapter listItemAdapter;//适配器

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_grid);
        gridView=findViewById(R.id.plant_grid);

        listItems1=new ArrayList<HashMap<String, String>>();
        for(int i=0;i<plantN1.length;i++) {
            HashMap<String, String> map = new HashMap<String, String>();//创建一个对象，向里面放数据
            map.put("PlantTitle", plantN1[i]);
            map.put("PlantDes",plantD1[i]);//标题文字，key不能重复
            listItems1.add(map);//加入数据
        }

        //生成适配器的item和动态数组相对应的元素
        listItemAdapter=new SimpleAdapter(this,listItems1,//数据源
                R.layout.item_plant_grid,//listItem的xml布局实现
                new String[]{"PlantTitle","PlantDes"},//放到布局的控件中，一一匹配的关系
                new int[]{R.id.item_grid_title, R.id.item_grid_des});


        gridView.setAdapter(listItemAdapter);

        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        gridView = findViewById(R.id.plant_grid);
        HashMap<String,String> map= (HashMap<String, String>) gridView.getItemAtPosition(position);
        String nameStr1=map.get("PlantTitle");
        Log.i(TAG,"onItemClick:titleStr"+nameStr1);

        Intent plantDescr=new Intent(this, PlantGridDesActivity.class);
        plantDescr.putExtra("position1",position);
        plantDescr.putExtra("nameStr1",nameStr1);
        startActivity(plantDescr);

    }
}
