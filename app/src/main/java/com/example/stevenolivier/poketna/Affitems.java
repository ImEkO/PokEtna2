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
        TextView category = (TextView) findViewById(R.id.category);
        TextView cost = (TextView) findViewById(R.id.cost);
        TextView utilisation = (TextView) findViewById(R.id.utilisation);
        TextView description = (TextView) findViewById(R.id.description);

        //Get data from the preview page
        Intent intent = getIntent();
        String message = intent.getStringExtra("nom");
        String urlImageItem = intent.getStringExtra("image");

        String categoryItem = intent.getStringExtra("category");
        String costItem = intent.getStringExtra("cost");
        String utilisationItem = intent.getStringExtra("utilisation");
        String descriptionItem = intent.getStringExtra("description");

        //Set data in the current page
        nom.setText(message);
        Picasso.with(getApplicationContext()).load(urlImageItem).into(image);
        category.setText("Catégories : " + categoryItem);
        cost.setText("Coût : " + costItem);
        utilisation.setText(utilisationItem);
        description.setText(descriptionItem);
    }
}
