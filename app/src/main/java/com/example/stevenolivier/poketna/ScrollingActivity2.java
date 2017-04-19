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
import android.widget.ImageView;
import android.widget.LinearLayout;
import org.json.JSONObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScrollingActivity2 extends AppCompatActivity {

    public void getUrlImageItem(JSONObject obj, LinearLayout layout, HttpURLConnection urlConnection, String urlItem) {
        try {
            if (!obj.isNull("sprites")) {
                JSONObject array = obj.getJSONObject("sprites");
                if (!array.isNull("default")) {
                    String urlItemImg = array.getString("default");
                    String itemName = obj.getString("name");
                    Log.d(urlItem, urlItemImg);
                    if (!urlItemImg.contains("-berry")) {
                        URL urlImage = new URL(urlItemImg);
                        Bitmap bitmap = BitmapFactory.decodeStream(urlImage.openConnection().getInputStream());
                        ImageView imageView = new ImageView(this);
                        Button button = new Button(this);
                        imageView.setBackground(new BitmapDrawable(getResources(),bitmap));
                        button.setText(itemName.replace("-", " "));
                        button.setTextSize(20);
                        button.setGravity(Gravity.CENTER);
                        layout.addView(imageView);
                        layout.addView(button);
                    }
                }
            }
        } catch (Exception e) {
            Log.d("ERROR sur l'url -> " + urlItem, "Problème survenu lors de la récupération de l'image de l'item !");
        }
    }

    public void setButtonsItems(HttpURLConnection urlConnection, String urlItem, LinearLayout layout) {
        try {
            URL url = new URL(urlItem);
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
            getUrlImageItem(obj, layout, urlConnection, urlItem);
        } catch (Exception e) {
            Log.d("ERROR sur l'url -> " + urlItem, "Problème survenu lors de la récupération de l'image de l'item !");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            else
                Log.d("ERROR sur la connexion de l" + "'" + "url -> " + urlItem, "Problème survenu lors de la connexion à l" + "'" + "url de l'item !");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        LinearLayout layout = (LinearLayout) findViewById(R.id.test);
        setSupportActionBar(toolbar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HttpURLConnection urlConnection = null;
        //for (int j = 1; j <= 746; j = j + 1) {
        for (int j = 1; j <= 20; j = j + 1) {
            String urlItem = "http://pokeapi.co/api/v2/item/" + j + "/";
            setButtonsItems(urlConnection, urlItem, layout);
        }
    }
}
