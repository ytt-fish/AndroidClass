package com.example.androidclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyLitst extends AppCompatActivity implements AdapterView.OnItemClickListener {
    List<String> data=new ArrayList<String>();
    ArrayAdapter adapter;
    String TAG="MyList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_litst);

        ListView listView=findViewById(R.id.mylist);
        for(int i=0;i<10;i++){
            data.add("item"+i);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        listView.setEmptyView(findViewById(R.id.nodata));
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> listV, View view, int position, long id) {
        Log.i(TAG,"OnItemClick:position:"+position);
        Log.i(TAG,"OnItemClick:parent:"+listV);
        adapter.remove(listV.getItemAtPosition(position));
        //adapter.notifyDataSetChanged();
    }
}
