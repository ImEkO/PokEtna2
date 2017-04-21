package com.example.stevenolivier.poketna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Affitems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affitems);

        //Elements from layout
        TextView nom = (TextView) findViewById(R.id.name);
        ImageView image = (ImageView) findViewById(R.id.image);

        //Get data from the preview page
        Intent intent = getIntent();
        String message = intent.getStringExtra("nom");
        String urlImagePokemon = intent.getStringExtra("image");
        String taillePokemon = intent.getStringExtra("taille");

        //Set data in the current page
        nom.setText(message);
        Picasso.with(getApplicationContext()).load(urlImagePokemon).into(image);
    }
}
