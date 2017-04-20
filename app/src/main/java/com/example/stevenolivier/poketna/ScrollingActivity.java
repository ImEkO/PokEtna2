package com.example.stevenolivier.poketna;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScrollingActivity extends AppCompatActivity {

    protected ImageView image;
    protected LinearLayout layout;
    protected String urlImagePokemon;
    protected Button button;
    protected int pokemon = 1;
    protected String urlPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScrollingActivity.this, Mainvue.class));
            }
        });
        layout = (LinearLayout) findViewById(R.id.layout);
        getInfoPokemon();
    }

    protected void getInfoPokemon() {
        RequestQueue queue = Volley.newRequestQueue(this);
        urlPokemon = "http://pokeapi.co/api/v2/pokemon/" + pokemon;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlPokemon,
                new Response.Listener < String > () {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject arraySprites = (JSONObject) jsonObject.get("sprites");
                            JSONArray arrayForms = (JSONArray) jsonObject.get("forms");
                            //Debug
                            System.out.println("POKEMON n°" + pokemon + " : " + arrayForms.getJSONObject(0).getString("name") + " -> " + arraySprites.getString("front_default"));

                            final String namePokemon = arrayForms.getJSONObject(0).getString("name");
                            final String urlImagepokemon = arraySprites.getString("front_default");
                            urlImagePokemon = arraySprites.getString("front_default");
                            button = new Button(ScrollingActivity.this);
                            image = new ImageView(ScrollingActivity.this);
                            //Creation de l'image
                            Picasso.with(getApplicationContext()).load(urlImagePokemon).resize(500, 500).into(image);
                            button.setText(namePokemon);
                            button.setTextSize(20);
                            button.setGravity(Gravity.CENTER);
                            button.setId(R.id.pokemon);
                            //Action du bouton pour info du pokemon specifier
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(ScrollingActivity.this, testintent.class);
                                    i.putExtra("nom", namePokemon);
                                    i.putExtra("image", urlImagepokemon);
                                    startActivity(i);
                                }

                            });
                            //Envoi des boutons et images sur le view
                            layout.addView(image);
                            layout.addView(button);
                            //Recursivité car requêtes Async, afin que la requête boucle
                            if (pokemon < 10) {
                                pokemon++;
                                getInfoPokemon();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR GET DATA : " + urlPokemon);
            }
        });
        queue.add(stringRequest);
    }
}