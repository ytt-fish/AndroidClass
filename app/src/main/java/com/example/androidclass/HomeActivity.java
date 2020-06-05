package com.example.androidclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.androidclass.R;
import com.example.androidclass.plant_list.PlantListActivity;

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

        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick_home(View view) {
        switch (view.getId()){
            case R.id.home_btn1:
                Intent intent=new Intent(this, PlantListActivity.class);
                startActivity(intent);
                break;
            case R.id.home_btn2:

                break;
            case R.id.home_btn3:

                break;


        }
    }
}
