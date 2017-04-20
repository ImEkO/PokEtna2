package com.example.stevenolivier.poketna;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
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

public class ScrollingActivity2 extends AppCompatActivity {

    protected ImageView image;
    protected Button button;
    protected LinearLayout layout;
    protected String urlImageItem;
    protected int item = 1;
    protected String urlItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        layout = (LinearLayout) findViewById(R.id.layout2);
        getInfoItem();
    }

    protected void getInfoItem() {
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
                            if (!jsonObject.getString("name").contains("-berry")) {
                                urlImageItem = arraySprites.getString("default");
                                button = new Button(ScrollingActivity2.this);
                                image = new ImageView(ScrollingActivity2.this);
                                Picasso.with(getApplicationContext()).load(urlImageItem).resize(400, 400).into(image);
                                button.setText(nameItem);
                                button.setTextSize(20);
                                button.setGravity(Gravity.CENTER);
                                layout.addView(image);
                                layout.addView(button);
                            }
                            if (item < 10) {
                                item++;
                                getInfoItem();
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