package com.example.androidclass.plant_des;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.androidclass.R;
import com.example.androidclass.plant_list.PlantListActivity;

import java.util.HashMap;
import java.util.List;

public class PlantDesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    TextView plantTitle,plantSun,plantFlower,plantTem;
    String[] sun = new String[] {"喜阳光，忌暴晒","充足阳光","充足阳光","充足阳光","充足阳光","喜阳光，忌暴晒","明亮散光，耐半阴","明亮散光，耐半阴"};
    String[] flower = new String[] {"1-6月，12月","3.4月","5.6月","1-5,10-12月","4-9月","3-7月","4-11月","3-7月"};
    String[] Tem = new String[]{"15-25摄氏度","15-25摄氏度","18-32摄氏度","20-28摄氏度","15-26摄氏度","18-22摄氏度","15-25摄氏度","20-30摄氏度"};
    String [] url=new String[]{"https://baike.baidu.com/item/蓝雪花/7563278",
            "https://baike.baidu.com/item/长寿花/13007574",
            "https://baike.baidu.com/item/风车茉莉",
            "https://baike.baidu.com/item/玛格丽特花/2699064",
            "https://baike.baidu.com/item/月季花/14505544",
            "https://baike.baidu.com/item/栀子花/77102",
            "https://baike.baidu.com/item/酢浆草/4507113",
            "https://baike.baidu.com/item/凌霄/27760"};
    int position1=0;
    String TAG="PlantDesActivity";
    String[] put={"点击链接到百科"};

    private SimpleAdapter listItemAdapter;//适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_des);
        final String name=getIntent().getStringExtra("nameStr");
        position1=getIntent().getIntExtra("position",0);
        Log.i(TAG,"onCreate:title="+name);
        Log.i(TAG,"onCreate:rate="+position1);

        plantTitle=findViewById(R.id.plant_des_title);
        plantTitle.setText(name);
        plantSun=findViewById(R.id.plant_sun);
        plantSun.setText("光照："+sun[position1]);
        plantFlower=findViewById(R.id.plant_flower);
        plantFlower.setText("花期："+flower[position1]);
        plantTem=findViewById(R.id.plant_tem);
        plantTem.setText("适宜温度"+Tem[position1]);

        ListView listView = findViewById(R.id.plant_url);
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, put);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
    }

    public void onClick_back(View view) {
        if(view.getId()==R.id.plant_des_back){
//            Intent intent=new Intent(this, PlantListActivity.class);
//            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        position1=getIntent().getIntExtra("position",0);
        Uri uri = Uri.parse(url[position1]);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
