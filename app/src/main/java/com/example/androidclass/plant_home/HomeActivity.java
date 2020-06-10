package com.example.androidclass.plant_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.androidclass.R;
import com.example.androidclass.plant_grid.PlantGridActivity;
import com.example.androidclass.plant_know.PlantKnowActivity;
import com.example.androidclass.plant_list.PlantListActivity;
import com.example.androidclass.plant_record.PlantRecordActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.plant,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_grid){
            Intent grid=new Intent(this, PlantGridActivity.class);
            startActivity(grid);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick_home(View view) {
        switch (view.getId()){
            case R.id.home_btn1:
                Intent intent1=new Intent(this, PlantListActivity.class);
                startActivity(intent1);
                break;
            case R.id.home_btn2:
                Intent intent2=new Intent(this, PlantKnowActivity.class);
                startActivity(intent2);
                break;
            case R.id.home_btn3:
                Intent intent3=new Intent(this, PlantRecordActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
