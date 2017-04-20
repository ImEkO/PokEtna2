package com.example.stevenolivier.poketna;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;

import android.provider.ContactsContract;
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
        Img();
    }

    protected void Img() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://pokeapi.co/api/v2/pokemon/" + i;
        System.out.println(url);
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

                                System.out.println("L'image " + array.getString("front_default") + "\n");
                                System.out.println("Le Nom : " + aa.getJSONObject(0).getString("name") + "\n");

                                urlimgp = array.getString("front_default");
                                System.out.println("re L'image" + urlimgp);
                                URL urlimg = new URL(urlimgp);
                                //Bitmap image = BitmapFactory.decodeStream(urlimg.openConnection().getInputStream());
                                //img.setImageBitmap(image);
                                //img.setBackground(new BitmapDrawable(getResources(), image));
                                //button.setText(aa.getJSONObject(0).getString("name"));
                                //button.setTextSize(20);
                                //button.setGravity(Gravity.CENTER);
                                //layout.addView(img);
                                //layout.addView(button);
                                if (i <= 20 ) {
                                    i++;
                                    Img();
                                }
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
        //}
    }

}