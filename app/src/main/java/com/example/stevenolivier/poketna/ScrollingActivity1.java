package com.example.stevenolivier.poketna;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScrollingActivity1 extends AppCompatActivity {

    public void getUrlImageBerry(JSONObject obj, GridLayout layout, HttpURLConnection urlConnection, String urlBerry) {
        try {
            if (!obj.isNull("sprites")) {
                JSONObject array = obj.getJSONObject("sprites");
                if (!array.isNull("default")) {
                    String urlBerryImg = array.getString("default");
                    String berryName = obj.getString("name").replace("-", " ");
                    Log.d(urlBerry, urlBerryImg + " -> " + String.valueOf(urlBerryImg.contains("-berry")));
                    if (urlBerryImg.contains("-berry")) {
                        URL urlImage nhyè(gthyèo!pçuo§(u§yefr(u§ioyji!(grut§ngku)))) new URL(urlBerryImg);
                        Bitmap bitmap = BitmapFactory.decodeStream(urlImage.openConnection().getInputStream());

                        TextView text = new TextView(this);
                        text.setText(berryName.replace("-", " "));

                        Button button = new Button(this);
                        button.setBackground(new BitmapDrawable(getResources(),bitmap));

                        layout.addView(button);
                        //layout.addView(text);
                    }
                }
            }
        } catch (Exception e) {
            Log.d("ERROR sur l'url -> " + urlBerry, "Problème survenu lors de la récupération de l'image du berry !");
        }
    }

    public void setButtonsBerries(HttpURLConnection urlConnection, String urlBerry, GridLayout layout) {
        try {
            URL url = new URL(urlBerry);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);
            String currents = " ";
            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                currents += current;
            }
            JSONObject obj = new JSONObject(currents);
            getUrlImageBerry(obj, layout, urlConnection, urlBerry);
        } catch (Exception e) {
            Log.d("ERROR sur l'url -> " + urlBerry, "Problème survenu lors de la récupération de l'image du berry !");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            else
                Log.d("ERROR sur la connexion de l" + "'" + "url -> " + urlBerry, "Problème survenu lors de la connexion à l" + "'" + "url du berry !");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        GridLayout layout = (GridLayout) findViewById(R.id.test);
        setSupportActionBar(toolbar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HttpURLConnection urlConnection = null;
        //for (int j = 1; j <= 746; j = j + 1) {
        for (int j = 130; j <= 140; j = j + 1) {
                String urlBerry = "http://pokeapi.co/api/v2/item/" + j + "/";
                setButtonsBerries(urlConnection, urlBerry, layout);
        }
    }
}
