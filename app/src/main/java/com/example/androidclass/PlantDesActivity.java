package com.example.androidclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import plant_list.PlantListActivity;

public class PlantDesActivity extends AppCompatActivity {
    TextView plantTitle,plantDes;
    String[] des = new String[] {"蓝雪花", "长寿花"};
    int position=0;
    String TAG="PlantDesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_des);
        final String name=getIntent().getStringExtra("nameStr");
        position=getIntent().getIntExtra("position",0);
        Log.i(TAG,"onCreate:title="+name);
        Log.i(TAG,"onCreate:rate="+position);
        plantDes=findViewById(R.id.plant_des_title);
        plantDes.setText(name);
        plantDes=findViewById(R.id.plant_description);
        plantDes.setText(des[position]);
    }

    public void onClick_back(View view) {
        if(view.getId()==R.id.plant_des_back){
            Intent intent=new Intent(this, PlantListActivity.class);
            startActivity(intent);
        }
    }
}
