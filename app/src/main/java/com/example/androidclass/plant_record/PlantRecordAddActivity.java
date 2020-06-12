package com.example.androidclass.plant_record;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidclass.R;
import com.example.androidclass.plant_db.PlantDBHelper;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlantRecordAddActivity extends AppCompatActivity {

    private ImageView reBack;
    private EditText reContent;
    private ImageView reDelete;
    private ImageView reSave;
    private TextView reTime;
    private String TAG="PlantRecordAddActivity";
    private PlantRecordBean data;
    PlantRecordManager plantRecordManager=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_record_add);
        plantRecordManager=new PlantRecordManager(this);

        reBack=findViewById(R.id.record_back);
        reContent=findViewById(R.id.record_content);
        reDelete=findViewById(R.id.record_delete);
        reSave=findViewById(R.id.record_save);
        reTime=findViewById(R.id.record_time);

        Object object= getIntent().getSerializableExtra("data");
        if(object!=null){
            data= (PlantRecordBean) object;
        }
        if (data!=null){
            reContent.setText(data.getContent());
            reTime.setText(data.getTime());
        }
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

                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
                String time=format.format(new Date());

                boolean result=false;

                if(data!=null){
                    data.setContent(content);
                    data.setTime(time);
                    result=plantRecordManager.update(data);
                }else {
                    result=plantRecordManager.add(new PlantRecordBean(0,content,time));
                }

//                把数据封装成bean
//                PlantRecordBean bean=new PlantRecordBean();
//                String time=bean.getTime();
//                PlantRecordBean bean1=new PlantRecordBean(content,time);

                if(result){
                    Log.i(TAG,"保存成功:"+content+time);
                    Toast.makeText(this,"保存成功！",Toast.LENGTH_LONG).show();
                    setResult(2);
                    finish();
                }else {
                    Log.i(TAG,"保存失败");
                    Toast.makeText(this,"保存失败",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

}
