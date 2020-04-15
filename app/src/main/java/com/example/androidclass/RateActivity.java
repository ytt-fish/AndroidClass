package com.example.androidclass;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.System.in;

public class RateActivity extends AppCompatActivity implements Runnable
{

    EditText inp;
    TextView show;
    Handler handler;

    private float dollarRate=0.1f;
    private float euroRate=0.2f;
    private float wonRate=0.3f;
    private final String TAG="Rate";

    @SuppressLint("HandlerLeak")
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

        //开启子线程
        Thread t=new Thread(this);
        t.start();

        //使用handler
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==5){
                    String str= (String) msg.obj;
                    Log.i(TAG,"handle Message+"+str);
                    show.setText(str);
                }
                super.handleMessage(msg);
            }
        };

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

    @Override
    public void run() {
        Log.i(TAG,"run:run()....");
        //演示运行
        for(int i=1;i<6;i++){
            Log.i(TAG,"run:i="+i);
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        //获取msg对象，用于返回主线程
        Message msg=handler.obtainMessage(5);
        msg.obj="Hello from run()";
        handler.sendMessage(msg);

        //获取网络数据
        try {
            URL url = new URL("http://www.usd-cny.com/bankofchina.htm");
            HttpURLConnection http= (HttpURLConnection) url.openConnection();
            InputStream in=http.getInputStream();
            String html=inputStream2String(in);
            Log.i(TAG,"run:html="+html);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String inputStream2String(InputStream inputStream) throws IOException{
        final int bufferSize=1024;
        final char[] buffer=new char[bufferSize];
        final StringBuffer out=new StringBuffer();
        Reader in =new InputStreamReader(inputStream,"gb2312");
        for( ; ; ){
            int rsz=in.read(buffer,0,buffer.length);
            if(rsz<0){
                break;
            }
            out.append(buffer,0,rsz);
        }
        return out.toString();
    }
}
