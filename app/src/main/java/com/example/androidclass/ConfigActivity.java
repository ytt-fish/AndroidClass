package com.example.androidclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ConfigActivity extends AppCompatActivity {
    private final String TAG="ConfigActivity";
    EditText dollartext;
    EditText eurotext;
    EditText wontext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        Intent intent=getIntent();
        float dollar2=intent.getFloatExtra("dollar_rate_key",0.0f);
        float euro2=intent.getFloatExtra("euro_rate_key",0.0f);
        float won2=intent.getFloatExtra("won_rate_key",0.0f);

        Log.i(TAG,"onCreate:dollar2"+dollar2);
        Log.i(TAG,"onCreate:euro2"+euro2);
        Log.i(TAG,"onCreate:won2"+won2);

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
        //获取新的值
        float newDollar=Float.parseFloat(dollartext.getText().toString());
        float newEuro=Float.parseFloat(eurotext.getText().toString());
        float newWon=Float.parseFloat(wontext.getText().toString());

        Log.i(TAG,"获取到新的值");
        Log.i(TAG,"onSave:newDollar"+newDollar);
        Log.i(TAG,"onSave:newEuro"+newEuro);
        Log.i(TAG,"onSave:newWon"+newWon);

        //将新的值存入Bundle或放入extra
        Intent intent=getIntent();
        Bundle bundle=new Bundle();
        bundle.putFloat("key_dollar",newDollar);
        bundle.putFloat("key_euro",newEuro);
        bundle.putFloat("key_won",newWon);
        intent.putExtras(bundle);
        setResult(2,intent);

        //返回到调用页面
        finish();

    }
}
