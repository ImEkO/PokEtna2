package com.example.stevenolivier.poketna;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

public class ScrollingActivity1 extends AppCompatActivity {

    protected ImageButton image;
    protected Button button;
    protected LinearLayout layout;
    protected String urlImageBerry;
    protected int item = 130;
    protected String urlItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        layout = (LinearLayout) findViewById(R.id.layout1);
        FloatingActionButton returnBtn = (FloatingActionButton) findViewById(R.id.fab);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScrollingActivity1.this, Mainvue.class));
            }
        });
        getInfoBerry();
    }

    protected void getInfoBerry() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        urlItem = "http://pokeapi.co/api/v2/item/" + item;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlItem,
                new Response.Listener < String > () {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject arraySprites = (JSONObject) jsonObject.get("sprites");
                            String nameItem = jsonObject.getString("name").replace("-", " ");
                            System.out.println("ITEM n°" + item + " : " + nameItem + " -> " + arraySprites.getString("default"));
                            if (jsonObject.getString("name").contains("-berry")) {
                                urlImageBerry = arraySprites.getString("default");
                                button = new Button(ScrollingActivity1.this, null, R.style.newbuttonstyle);
                                image = new ImageButton(ScrollingActivity1.this, null, R.style.newbuttonstyle);
                                Picasso.with(getApplicationContext()).load(urlImageBerry).resize(400, 400).into(image);
                                final String nameitem = nameItem;
                                final String urlimageberry = urlImageBerry;
                                button.setText(nameItem);
                                button.setTextSize(20);
                                button.setGravity(Gravity.CENTER);
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(ScrollingActivity1.this, Affitems.class);
                                        intent.putExtra("nom", nameitem);
                                        intent.putExtra("image", urlimageberry);
                                        startActivity(intent);
                                    }

                                });
                                image.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(ScrollingActivity1.this, Affitems.class);
                                        intent.putExtra("nom", nameitem);
                                        intent.putExtra("image", urlimageberry);
                                        startActivity(intent);
                                    }

                                });
                                layout.addView(image);
                                layout.addView(button);
                            }
                            if (item < 135) {
                                item++;
                                getInfoBerry();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR GET DATA : " + urlItem);
            }
        });
        requestQueue.add(stringRequest);
    }
}