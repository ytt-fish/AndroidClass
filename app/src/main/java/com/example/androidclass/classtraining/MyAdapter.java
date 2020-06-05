package com.example.androidclass.classtraining;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.androidclass.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapter extends ArrayAdapter {

    private static final String TAG = "MyAdapter";

    public MyAdapter(@NonNull Context context, int resource, ArrayList<HashMap<String, String>> list) {
        super(context, resource, list);//上下文，布局，数据
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {//view行，parent 控件
        View itemView;
        itemView = convertView;
        if (itemView == null) {
            //获得对象，是否覆盖根布局
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        //获得当前位置的对象
        Map<String, String> map = (Map<String, String>) getItem(position);
        TextView title = itemView.findViewById(R.id.itemTitle);
        TextView detail = itemView.findViewById(R.id.itemDetail);

        //每一行数据都是一个map
        title.setText("Title；"+map.get("ItemTitle"));
        title.setText("detail"+map.get("ItemDetail"));

        //返回的数据用于填充行的数据
        return  itemView;
    }


}
