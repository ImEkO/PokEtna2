package com.example.stevenolivier.poketna;

import android.app.DownloadManager;
import android.os.Bundle;
import android.os.StrictMode;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
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
        setSupportActionBar(toolbar);
        URL url;
        HttpURLConnection urlConnection = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        LinearLayout layout = (LinearLayout) findViewById(R.id.test);
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
                layout.addView(button);
                System.out.println("Sortie : " + array.getJSONObject(i).getString("name"));
                Img(array.getJSONObject(i).getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    protected void Img(String nom) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://pokeapi.co/api/v2/pokemon/" + nom + "/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONObject array = (JSONObject) obj.get("sprites");
                            Log.d("Seulement les ", "Images" + array.getString("front_default"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error");
            }
        });
        queue.add(stringRequest);

        /*URL url;
        HttpURLConnection urlConnection = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String currents;
        LinearLayout layout = (LinearLayout) findViewById(R.id.test);
        String urlBerry = "http://pokeapi.co/api/v2/pokemon/" + nom + "/";
        String urlBerryImg = " ";
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
                urlBerryImg = array.getString("front_default");
                URL urlimg = new URL(urlBerryImg);
                ImageView img = new ImageView(this);
                Bitmap image = BitmapFactory.decodeStream(urlimg.openConnection().getInputStream());
                img.setImageBitmap(image);
                Button button = new Button(this);
                Bitmap resized = Bitmap.createScaledBitmap(image, (int) (image.getWidth() * 0.8), (int) (image.getHeight() * 0.8), true);
                button.setText(urlBerryImg);
                img.setBackground(new BitmapDrawable(getResources(), resized));
                button.setTextSize(20);
                button.setGravity(Gravity.CENTER);
                layout.addView(img);
            }
        } catch (Exception e) {
            Log.d("ERROR sur l’url -> " + urlBerry, "Problème subvenu lors de la récupération de l’image du pokemon !");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }*/
    }
}