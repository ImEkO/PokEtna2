package com.example.stevenolivier.poketna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Affpokemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affpokemon);

        //Elements from layout
        TextView nom = (TextView) findViewById(R.id.name);
        ImageView image = (ImageView) findViewById(R.id.image);
        TextView taille = (TextView) findViewById(R.id.taille);
        TextView poids = (TextView) findViewById(R.id.poids);
        TextView types = (TextView) findViewById(R.id.type);
        TextView exp = (TextView) findViewById(R.id.exp);
        TextView abilites = (TextView) findViewById(R.id.utilisation);
        TextView stats = (TextView) findViewById(R.id.stats);

        //Get data from the preview page
        Intent intent = getIntent();
        String message = intent.getStringExtra("nom");
        String urlImagePokemon = intent.getStringExtra("image");
        String taillePokemon = intent.getStringExtra("taille");
        String poidsPokemon = intent.getStringExtra("poids");
        String typesPokemon = intent.getStringExtra("types");
        String expPokemon = intent.getStringExtra("exp");
        String abilitesPokemon = intent.getStringExtra("abilites");
        String statsPokemon = intent.getStringExtra("stats");

        //Set data in the current page
        nom.setText(message);
        Picasso.with(getApplicationContext()).load(urlImagePokemon).into(image);
        taille.setText("Taille : " + taillePokemon);
        poids.setText("Poids : " + poidsPokemon);
        types.setText("Type(s) : " + typesPokemon);
        exp.setText("Expérience : " + expPokemon);
        abilites.setText(abilitesPokemon);
        stats.setText(statsPokemon);
    }
}
