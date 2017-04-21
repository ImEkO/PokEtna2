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
import android.widget.ImageButton;
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

    protected ImageButton image;
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
        FloatingActionButton returnBtn = (FloatingActionButton) findViewById(R.id.fab);
        returnBtn.setOnClickListener(new View.OnClickListener() {
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
                            //Envoie de données vers la page Description Pokémon
                            final String namePokemon = arrayForms.getJSONObject(0).getString("name");
                            final String urlImagepokemon = arraySprites.getString("front_default");
                            String types = "";
                            JSONArray arrayTypesPokemon = (JSONArray) jsonObject.get("types");
                            for (int type = 0; type < arrayTypesPokemon.length(); type++) {
                                JSONObject typeObject = (JSONObject) arrayTypesPokemon.getJSONObject(type).get("type");
                                types = types + typeObject.getString("name");
                                if (type != arrayTypesPokemon.length() - 1) {
                                    types = types + " / ";
                                }
                            }
                            final String typesPokemons = types;
                            String abilities = "";
                            JSONArray arrayAbilitiesPokemon = (JSONArray) jsonObject.get("abilities");
                            for (int ability = 0; ability < arrayAbilitiesPokemon.length(); ability++) {
                                JSONObject typeObject = (JSONObject) arrayAbilitiesPokemon.getJSONObject(ability).get("ability");
                                System.out.println(typeObject);
                                abilities = abilities + typeObject.getString("name");
                                if (ability != arrayAbilitiesPokemon.length() - 1) {
                                    abilities = abilities + " / ";
                                }
                            }
                            final String abilitiesPokemons = abilities;
                            final String weightPokemon = jsonObject.getString("weight");
                            final String heightPokemon = jsonObject.getString("height");
                            final String expPokemon = jsonObject.getString("base_experience");
                            urlImagePokemon = arraySprites.getString("front_default");
                            button = new Button(ScrollingActivity.this, null, R.style.newbuttonstyle);
                            image = new ImageButton(ScrollingActivity.this, null, R.style.newbuttonstyle);
                            //Création de l'image
                            Picasso.with(getApplicationContext()).load(urlImagePokemon).resize(500, 500).into(image);
                            button.setText(namePokemon);
                            button.setTextSize(20);
                            button.setGravity(Gravity.CENTER);
                            button.setId(R.id.pokemon);
                            //Action du bouton pour info du pokemon specifié
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(ScrollingActivity.this, testintent.class);
                                    intent.putExtra("nom", namePokemon);
                                    intent.putExtra("image", urlImagepokemon);
                                    intent.putExtra("types", typesPokemons);
                                    intent.putExtra("poids", weightPokemon);
                                    intent.putExtra("taille", heightPokemon);
                                    intent.putExtra("exp", expPokemon);
                                    intent.putExtra("abilites", abilitiesPokemons);
                                    startActivity(intent);
                                }

                            });
                            image.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(ScrollingActivity.this, testintent.class);
                                    intent.putExtra("nom", namePokemon);
                                    intent.putExtra("image", urlImagepokemon);
                                    intent.putExtra("types", typesPokemons);
                                    intent.putExtra("poids", weightPokemon);
                                    intent.putExtra("taille", heightPokemon);
                                    intent.putExtra("exp", expPokemon);
                                    intent.putExtra("abilites", abilitiesPokemons);
                                    startActivity(intent);
                                }

                            });
                            //Envoie des boutons et images sur la view
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