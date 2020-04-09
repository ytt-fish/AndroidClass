package com.example.androidclass;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RateActivity extends AppCompatActivity {

    EditText inp;
    TextView show;

    private float dollarRate=0.1f;
    private float euroRate=0.2f;
    private float wonRate=0.3f;
    private final String TAG="Rate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        inp=findViewById(R.id.inpMoney);
        show=findViewById(R.id.showMoney);

        //获取sp里保存的数据
        SharedPreferences sharedPreferences=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        dollarRate= sharedPreferences.getFloat("dollar_rate",0.0f);
        euroRate=sharedPreferences.getFloat("euro_rate",0.0f);
        wonRate=sharedPreferences.getFloat("won_rate",0.0f);
        Log.i(TAG,"onCreate:dollarRate"+dollarRate);
        Log.i(TAG,"onCreate:euroRate"+euroRate);
        Log.i(TAG,"onCreate:wonRate"+wonRate);
    }

    public void onclick(View btn){
        Log.i(TAG,"onClick:");

        String str=inp.getText().toString();
        Log.i(TAG,"onClick=str:"+str);

        float r=0;
        if(str.length()>0){
            r=Float.parseFloat(str);
        }else{
            Toast.makeText(this,"请输入金额",Toast.LENGTH_LONG).show();
        }
        if(btn.getId()==R.id.btn_dollar){
            show.setText(String.format("%.2f",r*dollarRate));
        }
        else if(btn.getId()==R.id.btn_euro){
            show.setText(String.format("%.2f",r*euroRate));
        }else {
            show.setText(String.format("%.2f",r*wonRate));
        }
    }

    public void openOne(View btn){
        //打开一个页面activity
//        Log.i("open","openOne");
//        Intent hello=new Intent(this,ScoreActivity.class);
//        Intent web=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jd.com"));
//        Intent dial=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:15928985495"));
//        startActivity(dial);

        openConfig();

    }

    private void openConfig() {
        //提取成方法
        Intent config = new Intent(this, ConfigActivity.class);
        config.putExtra("dollar_rate_key", dollarRate);
        config.putExtra("euro_rate_key", euroRate);
        config.putExtra("won_rate_key", wonRate);

        Log.i(TAG, "openOne:dollarRate" + dollarRate);
        Log.i(TAG, "openOne:euroRate" + euroRate);
        Log.i(TAG, "openOne:wonRate" + wonRate);
        startActivityForResult(config, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //资源填充器，将资源加载出来
        getMenuInflater().inflate(R.menu.rate,menu);
        //返回真，则当前activity有菜单项，false则没有
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_set){
            //提取的方法
            openConfig();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1&&resultCode==2){
            Bundle bdl=data.getExtras();
            dollarRate=bdl.getFloat("key_dollar",0.1f);
            euroRate=bdl.getFloat("key_euro",0.1f);
            wonRate=bdl.getFloat("key_won",0.1f);

            Log.i(TAG,"onActivityResult:"+dollarRate);
            Log.i(TAG,"onActivityResult:"+euroRate);
            Log.i(TAG,"onActivityResult:"+wonRate);

            //将新设置的汇率写到sp里
            //获得对象
            SharedPreferences sharedPreferences=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
            //获得editor对象
            SharedPreferences.Editor editor=sharedPreferences.edit();
            //放进数据
            editor.putFloat("dollar_rate",dollarRate);
            editor.putFloat("euro_rate",euroRate);
            editor.putFloat("won_rate",wonRate);
            //保存数据
            editor.commit();
            //日志输出
            Log.i(TAG,"onActivityResult:editor dollar"+dollarRate);
            Log.i(TAG,"onActivityResult:editor euro"+euroRate);
            Log.i(TAG,"onActivityResult:editor won"+wonRate);


        }
        super.onActivityResult(requestCode, resultCode, data);

    }

}
