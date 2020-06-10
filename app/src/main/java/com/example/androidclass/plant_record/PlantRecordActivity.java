package com.example.androidclass.plant_record;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.androidclass.R;

import java.util.ArrayList;
import java.util.List;

public class PlantRecordActivity extends AppCompatActivity {
    private ListView listView;
    private PlantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_record);

        listView=findViewById(R.id.record_listview);
        List<PlantRecordBean> data=new ArrayList<>();
        for(int i=0;i<10;i++){
            data.add(new PlantRecordBean("ssssss"+i,"2020"));
        }

        adapter=new PlantAdapter(this,R.layout.plantrecord_item_lv,data);
        listView.setAdapter(adapter);

    }
}
