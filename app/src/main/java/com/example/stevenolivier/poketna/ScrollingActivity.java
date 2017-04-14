package com.example.stevenolivier.poketna;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        URL url;
        HttpURLConnection urlConnection = null;
        Log.d("1", "1");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Log.d("2", "2");
            url = new URL("http://pokeapi.co/api/v2/pokemon/");
            urlConnection = (HttpURLConnection) url.openConnection();
            Log.d("3", "3");
            InputStream in = urlConnection.getInputStream();
            Log.d("4", "4");
            InputStreamReader isw = new InputStreamReader(in);
            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                Log.d("test", "valeur : " + current);
                }
            }
            catch (Exception e) {
            e.printStackTrace();
            } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
        }
        }
    }
}
