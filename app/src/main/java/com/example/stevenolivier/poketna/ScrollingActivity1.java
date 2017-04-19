package com.example.stevenolivier.poketna;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity1 extends AppCompatActivity {
/*
    public String getBerryImageUrl(JSONArray array) {
        try {
            for (int i = 0; i < array.length(); i++) {
                return (array.getJSONObject(i).getString("default"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return (null);
    }

    public String getImageOfBerry(String urlBerry) {
        try {
            URL url;
            String currents;
            HttpURLConnection urlConnection = null;
            url = new URL(urlBerry);
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
            return (getBerryImageUrl(obj.getJSONArray("sprites")));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return (null);
    }

    public void setButtonsOfBerry(JSONArray array, LinearLayout layout) {
        try {
            for (int i = 0; i < array.length(); i++) {
                if (array.getJSONObject(i).getString("name").contains("-berry")) {
                    String berryUrl = getImageOfBerry(array.getJSONObject(i).getString("url"));
                    if (berryUrl.equals(null)) {
                        Button button = new Button(this);
                        //button.setText(array.getJSONObject(i).getString("name").replace("-", " "));
                        button.setText(berryUrl);
                        button.setTextSize(20);
                        button.setGravity(Gravity.CENTER);
                        layout.addView(button);
                    }
                    else
                        Log.d("ERROR", "Url Berry: " + berryUrl + " vaut null !");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setListOfBerries(LinearLayout layout) {
        URL url;
        HttpURLConnection urlConnection = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String currents;
        try {
            //for (int j = 0; j <= 746; j = j + 20) {
            for (int j = 130; j <= 200; j = j + 20) {
                url = new URL("http://pokeapi.co/api/v2/item/?offset=" + j);
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
                setButtonsOfBerry(obj.getJSONArray("results"), layout);
            }
        }
        catch(Exception e){
                e.printStackTrace();
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        LinearLayout layout = (LinearLayout) findViewById(R.id.test);
        setSupportActionBar(toolbar);
        URL url;
        HttpURLConnection urlConnection = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String currents;
        for (int j = 1; j <= 746; j = j + 1) {
            String urlBerry = "http://pokeapi.co/api/v2/item/" + j + "/";
            try {
                url = new URL(urlBerry);
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
                if (!obj.isNull("sprites")) {
                    JSONObject array = obj.getJSONObject("sprites");
                    if (!array.isNull("default")) {
                        String urlBerryImg = array.getString("default");
                        Log.d(urlBerryImg, String.valueOf(urlBerryImg.contains("-berry")));
                        if (urlBerryImg.contains("-berry")) {
                            Button button = new Button(this);
                            button.setText(urlBerryImg);
                            button.setTextSize(20);
                            button.setGravity(Gravity.CENTER);
                            layout.addView(button);
                        }
                    }
                }
            } catch (Exception e) {
                Log.d("ERROR sur l'url -> " + urlBerry, "Problème subvenu lors de la récupération de l'image du berry !");
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }
    }
}
