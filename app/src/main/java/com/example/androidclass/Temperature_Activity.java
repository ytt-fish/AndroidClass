package com.example.androidclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Temperature_Activity extends AppCompatActivity {
    //定义文本框
    TextView newTem;
    EditText originTem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_2);

        //在代码中获取控件
        findViewById(R.id.change);
        originTem=findViewById(R.id.originTem);
        newTem=findViewById(R.id.newTem);

    }

    public void btnChange(View btn){
        String m=originTem.getText().toString();
        float t=Float.parseFloat(m);
        newTem.setText("结果为："+getResult(t));
    }

    private float getResult(float n){
        Log.i("btn","change");
        return (n*9/5+32);
    }


}
