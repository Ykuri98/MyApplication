package com.example.kuri.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.kuri.myapplication.R;

public class DashboardActivity extends AppCompatActivity {

    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        button = (Button) findViewById(R.id.button6);
        button2 = (Button) findViewById(R.id.button7);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,TrainingActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,TrainerActivity.class);
                startActivity(intent);
            }
        });

    }
}
