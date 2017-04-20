package com.example.stevenolivier.poketna;

import android.app.DownloadManager;
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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    protected ImageView img;
    protected LinearLayout layout;
    protected String urlimgp;
    protected Button button;
    protected int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        layout = (LinearLayout) findViewById(R.id.test);
        onCreatepb();
    }

    protected void onCreatepb() {
        /*URL url;
        HttpURLConnection urlConnection = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String currents;
        try {
            img = new ImageView(this);
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
                //button.setText(array.getJSONObject(i).getString("name"));
                //button.setTextSize(20);
                //button.setGravity(Gravity.CENTER);*/
        while (i <= 20) {
            Img();
            i++;
        }
                //layout.addView(button);
            /*}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }*/
    }

    protected void Img() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://pokeapi.co/api/v2/pokemon/" + i;
        //System.out.println("Le nom : " + nom + "\n");
        System.out.println("icii");
        System.out.println(i);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("la");
                            JSONObject obej = new JSONObject(response);
                            JSONObject array = (JSONObject) obej.get("sprites");
                            JSONArray aa = (JSONArray) obej.get("forms");

                            System.out.println("Le nom : " + array.getString("front_default") + "\n");
                            System.out.println("Le Prenom : " + aa.getJSONObject(0).getString("name") + "\n");

                            urlimgp = array.getString("front_default");

                            System.out.println("1" + urlimgp);
                            URL urlimg = new URL(urlimgp);
                            //Bitmap image = BitmapFactory.decodeStream(urlimg.openConnection().getInputStream());
                            //img.setImageBitmap(image);
                            //img.setBackground(new BitmapDrawable(getResources(), image));
                            //button.setText(aa.getJSONObject(0).getString("name"));
                            //button.setTextSize(20);
                            //button.setGravity(Gravity.CENTER);
                            //layout.addView(img);
                            //layout.addView(button);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error Listerner");
            }
        });
        queue.add(stringRequest);
    }
}