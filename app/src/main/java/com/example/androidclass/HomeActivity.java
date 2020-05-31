package com.example.androidclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClick_home(View view) {
        switch (view.getId()){
            case R.id.home_btn1:

                break;
            case R.id.home_btn2:

                break;
            case R.id.home_btn3:

                break;


        }
    }
}
