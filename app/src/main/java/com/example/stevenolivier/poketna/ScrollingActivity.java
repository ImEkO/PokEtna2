package com.example.stevenolivier.poketna;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        NestedScrollView views = (NestedScrollView) findViewById(R.id.views);
        setSupportActionBar(toolbar);
        URL url;
        HttpURLConnection urlConnection = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String currents;
        try {
            url = new URL("http://pokeapi.co/api/v2/pokemon/");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);
            currents = " ";
            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                currents += current;
            }
            JSONObject obj = new JSONObject(currents);
            List<String> list = new ArrayList<String>();
            JSONArray array = obj.getJSONArray("results");
            for (int i = 0; i < array.length(); i++) {
                list.add(array.getJSONObject(i).getString("name"));
                Button button = new Button(this);
                button.setText(array.getJSONObject(i).getString("name"));
                button.setTextSize(20);
                button.setGravity(Gravity.CENTER);
                views.addView(button);
            }
            Log.d("Toute es info", "valeur : " + currents);
            Log.d("Seulement les noms", "valeur : " + list);
            }
            catch (Exception e) {
            e.printStackTrace();
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }
}
