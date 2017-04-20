package com.example.stevenolivier.poketna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class testintent extends AppCompatActivity {

    protected LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testintent);
        Intent intent = getIntent();
        layout = (LinearLayout) findViewById(R.id.layout);
        String message = intent.getStringExtra("nom");
        String urlImagePokemon = intent.getStringExtra("image");
        TextView textView = (TextView) findViewById(R.id.test);
        textView.setText(message);
        ImageView image = new ImageView(this);
        Picasso.with(getApplicationContext()).load(urlImagePokemon).resize(1000, 1000).into(image);
        layout.addView(image);
    }
}
