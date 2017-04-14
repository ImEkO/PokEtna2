package com.example.stevenolivier.poketna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mainvue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainvue);

        Button btn = (Button) findViewById(R.id.pokemon);
        Button btn1 = (Button) findViewById(R.id.berry);
        Button btn2 = (Button) findViewById(R.id.item);
        Button btn3 = (Button) findViewById(R.id.localisation);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Mainvue.this, ScrollingActivity.class));
            }

        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Mainvue.this, ScrollingActivity1.class));
            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Mainvue.this, ScrollingActivity2.class));
            }

        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Mainvue.this, ScrollingActivity3.class));
            }

        });
    }
}