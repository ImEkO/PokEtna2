package com.example.stevenolivier.poketna;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageButton;
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
                                button.setText(nameItem);
                                button.setTextSize(20);
                                button.setGravity(Gravity.CENTER);
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