package com.example.androidclass;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RateActivity extends AppCompatActivity implements Runnable
{

    EditText inp;
    TextView show;
    Handler handler;

    private float dollarRate=0.1f;
    private float euroRate=0.2f;
    private float wonRate=0.3f;
    private String updateDate="";
    private final String TAG="RateActivity";




    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.activity_rate);
        inp=findViewById(R.id.inpMoney);
        show=findViewById(R.id.showMoney);

        //获取sp里保存的数据，SharedPreference主要用于保存临时配置，简单参数，是一个默认配置文件（文件名，访问权限）
        SharedPreferences sharedPreferences=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        //另外一种方法
        //SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        dollarRate= sharedPreferences.getFloat("dollar_rate",0.0f);
        euroRate=sharedPreferences.getFloat("euro_rate",0.0f);
        wonRate=sharedPreferences.getFloat("won_rate",0.0f);
        updateDate=sharedPreferences.getString("update_date","");

        //获取当前系统时间
        Date today=Calendar.getInstance().getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        final String todayStr=sdf.format(today);


        Log.i(TAG,"onCreate:sp:dollar"+dollarRate);
        Log.i(TAG,"onCreate:sp:euro"+euroRate);
        Log.i(TAG,"onCreate:sp:won"+wonRate);
        Log.i(TAG,"onCreate:update"+updateDate);
        Log.i(TAG,"onCreate:todaystr"+todayStr);


        //判断时间
        if(!todayStr.equals(updateDate)){
            Log.i(TAG,"onCreate:需要更新");

            //开启子线程，访问网络资源一定不能在主线程中，当前对象，会寻找run方法
            Thread t=new Thread(this);
            t.start();
        }else{
            Log.i(TAG,"onCreate:不需要更新");
        }

        //使用handler，可以在msg中存放信息
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg){
                //接收消息
                if(msg.what==5){
                    //处理消息
                    Bundle bdl= (Bundle) msg.obj;
                    dollarRate=bdl.getFloat("dollar-rate");
                    euroRate=bdl.getFloat("euro-rate");
                    wonRate=bdl.getFloat("won-rate");

                    Log.i(TAG,"onCreate:dollar:"+dollarRate);
                    Log.i(TAG,"onCreate:euro:"+euroRate);
                    Log.i(TAG,"onCreate:won:"+wonRate);

                    //保存更新的日期
                    //获得对象,一个app可以有多个文件
                    SharedPreferences sharedPreferences=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
                    //获得editor对象,将新的数据保存在配置文件中
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("update_date",todayStr);
                    editor.putFloat("dollar_rate",dollarRate);
                    editor.putFloat("euro_rate",euroRate);
                    editor.putFloat("won_rate",wonRate);
                    editor.apply();

                    Toast.makeText(RateActivity.this,"汇率已更新",Toast.LENGTH_LONG).show();
                }
                super.handleMessage(msg);
            }
        };

    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");

    }

    //处理输入金额及显示
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
    //跳转到config页面
    public void openOne(View btn){
//        //打开一个页面activity
////        Log.i("open","openOne");
////        Intent hello=new Intent(this,ScoreActivity.class);
////        Intent web=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jd.com"));
////        Intent dial=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:15928985495"));
////        startActivity(dial);
//
          openConfig();
//
     }

    private void openConfig() {
        //通常用intent方法来传递对象（提取成方法）
        Intent config = new Intent(this, ConfigActivity.class);
        //以附加值的方式携带参数到下个页面
        config.putExtra("dollar_rate_key", dollarRate);
        config.putExtra("euro_rate_key", euroRate);
        config.putExtra("won_rate_key", wonRate);
        //还可以以bundle的方式来传递参数，打包
        //config.putExtras()；
        Log.i(TAG, "openConfig:dollarRate" + dollarRate);
        Log.i(TAG, "openConfig:euroRate" + euroRate);
        Log.i(TAG, "openConfig:wonRate" + wonRate);
        //打开这个窗口是为了带回数据(for result)（Intent:打开的对象,requestCode)
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
        }else if(item.getItemId()==R.id.open_list){
            //打开列表窗口
            Intent list = new Intent(this, RateListActivity.class);
            startActivity(list);
            //测试数据库
//            RateItem item1=new RateItem("aaa","123");
//            RateManager manager = new RateManager(this);
//            manager.add(item1);
//            manager.add(new RateItem("bbb","234"));
//            Log.i(TAG,"onOptionsItemSelected:写入数据库");
//
//            //查询所有数据
//            List<RateItem> testList=manager.listAll();
//            for(RateItem i:testList){
//                Log.i(TAG,"onOptionsItemSelected:取出数据[ids:"+i.getId()+"]names:"+i.getCurName()+"rate:"+i.getCurRate());
        }
        return super.onOptionsItemSelected(item);
    }
        @Override   //打开一个窗口分配一个requestCode，区分哪一个窗口返回的数据，resultCode区分是什么类型的数据,怎么去拆分,
    //系统会自己调用这个方法
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //将新设置的汇率写到sp里
        if(requestCode==1&&resultCode==2){
            Bundle bdl=data.getExtras();
            dollarRate=bdl.getFloat("key_dollar",0.1f);
            euroRate=bdl.getFloat("key_euro",0.1f);
            wonRate=bdl.getFloat("key_won",0.1f);
            Log.i(TAG,"onActivityResult:"+dollarRate);
            Log.i(TAG,"onActivityResult:"+euroRate);
            Log.i(TAG,"onActivityResult:"+wonRate);
            //获得对象,一个app可以有多个文件
            SharedPreferences sharedPreferences=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
            //获得editor对象,将新的数据保存在配置文件中
            SharedPreferences.Editor editor=sharedPreferences.edit();
            //放进数据，和之前获得的key一样
            editor.putFloat("dollar_rate",dollarRate);
            editor.putFloat("euro_rate",euroRate);
            editor.putFloat("won_rate",wonRate);
            //保存数据
            editor.commit();
            Log.i(TAG,"onActivityResult:数据已保存到sp里");
            //日志输出
            Log.i(TAG,"onActivityResult:editor dollar"+dollarRate);
            Log.i(TAG,"onActivityResult:editor euro"+euroRate);
            Log.i(TAG,"onActivityResult:editor won"+wonRate);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    //子线程中需要实现的代码，runnable接口指定的方法
    public void run() {
            Log.i(TAG, "run:run()....");
            //演示运行，当前停止几秒钟
            for (int i = 1; i < 3; i++) {
                Log.i(TAG, "run:i=" + i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
          Bundle bundle;
//        msg.obj="Hello from run()";
//        handler.sendMessage(msg);
            //获取网络数据
//        try {
//            URL url = new URL("http://www.usd-cny.com/bankofchina.htm");
//            HttpURLConnection http= (HttpURLConnection) url.openConnection();
//            InputStream in=http.getInputStream();
//
//            String html=inputStream2String(in);
//            Log.i(TAG,"run:html="+html);
//            Document doc=Jsoup.parse(html);
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        bundle=getFromBoc();
        //取出一个msg对象，用于返回主线程，还需填内容，what标记当前message的属性，接受时进行比对
        Message msg = handler.obtainMessage(5);
        //填写msg对象的内容
        msg.obj = bundle;
        //传回主线程
        handler.sendMessage(msg);
    }

    private Bundle getFromBoc() {
        Bundle bundle=new Bundle();
        Document doc = null;
        //用于保存获取的汇率
        try {
            doc = Jsoup.connect("http://www.usd-cny.com/bankofchina.htm").get();
            //Log.i(TAG, "run:" + doc.title());
            Elements tables = doc.getElementsByTag("table");
//            int i=1;
//            for(Element table:tables) {
//                Log.i(TAG, "run:table["+i+"]" + table);
//                i++;
//            }
            Element table1 = tables.get(0);
//            Log.i(TAG,"run:tables"+table1);
            //获取TD中的元素
            Elements tds = table1.getElementsByTag("td");
            for (int i = 0; i < tds.size(); i += 6) {
                Element td1 = tds.get(i);
                Element td2 = tds.get(i + 5);
                Log.i(TAG, "run:" + td1.text() + "==>" + td2.text());
                String str1 = td1.text();
                String val = td2.text();
                if ("美元".equals(str1)) {
                    bundle.putFloat("dollar-rate", 100f / Float.parseFloat(val));
                } else if ("欧元".equals(str1)) {
                    bundle.putFloat("euro-rate", 100f / Float.parseFloat(val));
                } else if ("韩元".equals(str1)) {
                    bundle.putFloat("won-rate", 100f / Float.parseFloat(val));
                }
            }
//            for(Element td:tds){
//                Log.i(TAG, "run:td:" + td);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bundle;
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
