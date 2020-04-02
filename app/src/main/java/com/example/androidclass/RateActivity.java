package com.example.androidclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RateActivity extends AppCompatActivity {

    EditText inp;
    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        inp=findViewById(R.id.inpMoney);
        show=findViewById(R.id.showMoney);
    }

    public void onclick(View btn){
        String str=inp.getText().toString();
        float r=0;
        if(str.length()>0){
            r=Float.parseFloat(str);
        }else{
            Toast.makeText(this,"请输入金额",Toast.LENGTH_LONG).show();
        }
        float d=0;
        if(btn.getId()==R.id.btn_dollar){
            d=r/6.7f;
        }
        else if(btn.getId()==R.id.btn_euro){
            d=r/11;
        }else {
            d = r * 500;
        }
        show.setText(String.valueOf(d));
    }
}
