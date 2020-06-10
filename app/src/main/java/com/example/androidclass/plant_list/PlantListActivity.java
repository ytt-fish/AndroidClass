package com.example.androidclass.plant_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.androidclass.plant_des.PlantDesActivity;
import com.example.androidclass.R;

import org.jsoup.internal.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlantListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private List<HashMap<String,String>> listItems;//存放文字，图片信息
    private SimpleAdapter listItemAdapter;//适配器
    String TAG="PlantlistActivity";
    String[] plantN = new String[] {"蓝雪花", "长寿花","风车茉莉","玛格丽特花","月季花","栀子花","杜鹃","桂花"};
    String[] plantD = new String[] {"白花丹科","石蒜科","夹竹桃科","菊科","蔷薇科","茜草科","杜鹃花科","木犀科"};
    EditText key;
    ImageView searchI,flushI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_list);

        key=findViewById(R.id.plant_key_search);
        searchI=findViewById(R.id.plant_list_search);
        searchI.setOnClickListener(this);
        flushI=findViewById(R.id.plant_list_flush);
        flushI.setOnClickListener(this);

        listItems=new ArrayList<HashMap<String, String>>();
        for(int i=0;i<plantN.length;i++) {
            HashMap<String, String> map = new HashMap<String, String>();//创建一个对象，向里面放数据
            map.put("PlantName", plantN[i]);//标题文字，key不能重复
            map.put("PlantDetail", plantD[i]);//详情描述
            listItems.add(map);//加入数据
        }

        //生成适配器的item和动态数组相对应的元素
        listItemAdapter=new SimpleAdapter(this,listItems,//数据源
                R.layout.item_plantlist_lv,//listItem的xml布局实现
                new String[]{"PlantName","PlantDetail"},//放到布局的控件中，一一匹配的关系
                new int[]{R.id.plantName,R.id.plantDetail});

        ListView listView = findViewById(R.id.plant_list_LV);
        listView.setAdapter(listItemAdapter);

        listView.setOnItemClickListener(this);
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG,"onItemClick:parent"+parent);
        Log.i(TAG,"onItemClick:view:"+view);
        Log.i(TAG,"onClickItem:position"+position);
        Log.i(TAG,"onClickItem:id"+id);

        ListView listView = findViewById(R.id.plant_list_LV);
        HashMap<String,String> map= (HashMap<String, String>) listView.getItemAtPosition(position);
        String nameStr=map.get("PlantName");
        Log.i(TAG,"onItemClick:titleStr"+nameStr);

        Intent plantDes=new Intent(this, PlantDesActivity.class);
        plantDes.putExtra("position",position);
        plantDes.putExtra("nameStr",nameStr);
        startActivity(plantDes);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.plant_list_search:
                //获取输入内容，判断是否为空
                String msg=key.getText().toString().trim();
                if(StringUtil.isBlank(msg)){
                    Toast.makeText(this,"输入内容不能为空",Toast.LENGTH_LONG);
                    return;
                }
                //判断是否包含
                List<HashMap<String,String>> listItems1=new ArrayList<HashMap<String, String>>();
                for(int i=0;i<listItems.size();i++){
                    String name=listItems.get(i).get("PlantName");
                    if(name.contains(msg)){
                        listItems1.add(listItems.get(i));
                    }
                }
                listItems.clear();
                listItems.addAll(listItems1);
                listItemAdapter.notifyDataSetChanged();
                break;
            case R.id.plant_list_flush:
                listItems.clear();
                for(int i=0;i<plantN.length;i++) {
                    HashMap<String, String> map = new HashMap<String, String>();//创建一个对象，向里面放数据
                    map.put("PlantName", plantN[i]);//标题文字，key不能重复
                    map.put("PlantDetail", plantD[i]);//详情描述
                    listItems.add(map);//加入数据
                }
                listItemAdapter.notifyDataSetChanged();
                break;
        }


    }
}
