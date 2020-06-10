package com.example.androidclass.plant_record;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.androidclass.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlantAdapter extends ArrayAdapter {

    private static final String TAG = "PlantAdapter";
    private List<PlantRecordBean> data;


    public PlantAdapter(@NonNull Context context, int resource,List<PlantRecordBean> data) {
        super(context, resource, data);//上下文，布局，数据
        this.data=data;
    }



    public View getView(int position, View convertView, ViewGroup parent) {//view行，parent 控件
        View itemView;
        itemView = convertView;
        if (itemView == null) {
            //获得对象，是否覆盖根布局
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.plantrecord_item_lv, parent, false);
        }
        //获得当前位置的对象
        //Map<String, String> map = (Map<String, String>) getItem(position);
        TextView content = itemView.findViewById(R.id.plantrecord_item_content);
        TextView time = itemView.findViewById(R.id.plantrecord_item_time);

        PlantRecordBean bean=data.get(position);


        content.setText(bean.getContent());
        time.setText(bean.getTime());

        //返回的数据用于填充行的数据
        return  itemView;
    }
}
