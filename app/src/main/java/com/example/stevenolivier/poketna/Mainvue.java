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

        Button btnPokemons = (Button) findViewById(R.id.pokemon);
        Button btnBerries = (Button) findViewById(R.id.berry);
        Button btnItems = (Button) findViewById(R.id.item);
        //new ScrollingActivity().getInfoPokemon();
        btnPokemons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Mainvue.this, ScrollingActivity.class));
            }

        });
        btnBerries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Mainvue.this, ScrollingActivity1.class));
            }

        });
        btnItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Mainvue.this, ScrollingActivity2.class));
            }

        });
    }
}