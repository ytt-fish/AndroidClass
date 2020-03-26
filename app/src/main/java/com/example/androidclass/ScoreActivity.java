package com.example.androidclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        score=findViewById(R.id.showScore);
    }

    public void btn_add1(View btn){
        showSc(1);
    }
    public void btn_add2(View btn){
        showSc(2);
    }
    public void btn_add3(View btn){
        showSc(3);
    }
    public void btn_reset(View btn){
        score.setText("0");
    }

    private void showSc(int inp){
        String oldScore= (String) score.getText();
        int newScore=Integer.parseInt(oldScore)+inp;
        score.setText(""+newScore);
    }
}
