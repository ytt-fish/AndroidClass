package com.example.androidclass.plant_record;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidclass.R;
import com.example.androidclass.plant_db.PlantDBHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlantRecordActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    private ListView listView=null;
    PlantAdapter adapter=null;
    private String TAG="PlantRecordActivity";
    private List<PlantRecordBean> data=new ArrayList<>();
    PlantRecordManager plantRecordManager=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_record);

        listView=findViewById(R.id.record_listview);

        plantRecordManager=new PlantRecordManager(this);
        data=plantRecordManager.showAll();
        adapter=new PlantAdapter(this,R.layout.plantrecord_item_lv,data);
        listView.setAdapter(adapter);

//        Log.i(TAG,"更新数据");
//        data.clear();
//        data.addAll(plantRecordManager.query());
//        Toast.makeText(this,"欢迎",Toast.LENGTH_LONG).show();
//        Log.i(TAG,"获取到的数据："+this.data.size());
//        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

    }

    public void record_add(View view) {
        if(view.getId()==R.id.record_btn_add){
            Intent add=new Intent(this,PlantRecordAddActivity.class);
            startActivityForResult(add,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1&&resultCode==2){
            Log.i(TAG,"更新数据");
            this.data.clear();
            this.data.addAll(plantRecordManager.showAll());
            //Toast.makeText(this,"欢迎",Toast.LENGTH_LONG).show();
            Log.i(TAG,"获取到的数据："+this.data.size());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        final PlantRecordBean bean=data.get(position);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PlantRecordActivity.this,"删除",Toast.LENGTH_LONG).show();
                plantRecordManager.delete(bean.getId());
                data.remove(position);
                adapter.notifyDataSetChanged();
                Log.i(TAG,"onItemLongClick:size"+data.size());
            }
        }).setNegativeButton("否",null);
        builder.create().show();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PlantRecordBean bean=data.get(position);
        Intent update=new Intent(this,PlantRecordAddActivity.class);
        update.putExtra("data", bean);
        startActivityForResult(update,2);
    }
}
