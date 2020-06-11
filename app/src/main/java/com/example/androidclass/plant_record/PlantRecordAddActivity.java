package com.example.androidclass.plant_record;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidclass.R;
import com.example.androidclass.plant_db.PlantDBHelper;

import java.io.Serializable;

public class PlantRecordAddActivity extends AppCompatActivity {

    private ImageView reBack;
    private EditText reContent;
    private ImageView reDelete;
    private ImageView reSave;
    private String TAG="PlantRecordAddActivity";
    private PlantRecordBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_record_add);

        data= (PlantRecordBean) getIntent().getSerializableExtra("data");
        if(data!=null){
            reContent.setText(data.getContent());
        }

        reBack=findViewById(R.id.record_back);
        reContent=findViewById(R.id.record_content);
        reDelete=findViewById(R.id.record_delete);
        reSave=findViewById(R.id.record_save);

    }

    public void record_list(View view) {
        switch (view.getId()){
            case R.id.record_back:
                Log.i(TAG,"返回");
                finish();
                break;
            case R.id.record_delete:
                Log.i(TAG,"删除");
                reContent.setText("");
                break;
            case R.id.record_save:
                Log.i(TAG,"保存");
                //获取内容
                String content=reContent.getText().toString();
                //判断
                if(TextUtils.isEmpty(content)){
                    Toast.makeText(this,"内容不能为空！",Toast.LENGTH_LONG).show();
                    return;
                }
                //把数据封装成bean
                PlantRecordBean bean=new PlantRecordBean();
                String time=bean.getTime();
                PlantRecordBean bean1=new PlantRecordBean(content,time);
                //插入数据
                boolean result=new PlantRecordManager(this).add(bean);
                if(result){
                    Log.i(TAG,"保存成功:"+content+time);
                    Toast.makeText(this,"保存成功！",Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Log.i(TAG,"保存失败");
                    Toast.makeText(this,"保存失败",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

}
