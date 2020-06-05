package com.example.androidclass.classtraining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.androidclass.R;

public class ConfigActivity extends AppCompatActivity {
    private final String TAG="ConfigActivity";
    EditText dollartext;
    EditText eurotext;
    EditText wontext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        //获得传过来的数据
        Intent intent=getIntent();
        //取得数据，要指定数据类型，标签要和放进去时一样
        float dollar2=intent.getFloatExtra("dollar_rate_key",0.0f);
        float euro2=intent.getFloatExtra("euro_rate_key",0.0f);
        float won2=intent.getFloatExtra("won_rate_key",0.0f);

        Log.i(TAG,"onCreate:dollar2"+dollar2);
        Log.i(TAG,"onCreate:euro2"+euro2);
        Log.i(TAG,"onCreate:won2"+won2);

        //获取控件
        dollartext=findViewById(R.id.dollar_rate);
        eurotext=findViewById(R.id.euro_rate);
        wontext=findViewById(R.id.won_rate);

        //显示数据到text
        dollartext.setText(String.valueOf(dollar2));
        eurotext.setText(String.valueOf(euro2));
        wontext.setText(String.valueOf(won2));
    }

    public void save(View btn){
        Log.i("cfg","save:");
        //获取新的值，从对话框
        float newDollar=Float.parseFloat(dollartext.getText().toString());
        float newEuro=Float.parseFloat(eurotext.getText().toString());
        float newWon=Float.parseFloat(wontext.getText().toString());

        Log.i(TAG,"获取到新的值");
        Log.i(TAG,"onSave:newDollar"+newDollar);
        Log.i(TAG,"onSave:newEuro"+newEuro);
        Log.i(TAG,"onSave:newWon"+newWon);

        //将新的值存入Bundle或放入extra，将intent对象带回去
        Intent intent=getIntent();
        Bundle bundle=new Bundle();
        bundle.putFloat("key_dollar",newDollar);
        bundle.putFloat("key_euro",newEuro);
        bundle.putFloat("key_won",newWon);
        intent.putExtras(bundle);
        //resultCode,intent对象,带回
        setResult(2,intent);

        //返回到调用页面，回到上一级页面
        finish();

    }
}
