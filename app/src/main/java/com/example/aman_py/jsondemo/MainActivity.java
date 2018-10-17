package com.example.aman_py.jsondemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;


public class MainActivity extends Activity {
    Button click;
    public static EditText city_name;
    public static TextView weather,city,wind,temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click = findViewById(R.id.click);
        temp = findViewById(R.id.temp);
        weather =findViewById(R.id.weather);
        wind = findViewById(R.id.wind);
        city=findViewById(R.id.city);
        city_name = findViewById(R.id.city_name);


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String get = city_name.getText().toString();

                if (TextUtils.isEmpty(get)){

                    city_name.setError("User Input Empty");
                }
                else {

                    fetch process = new fetch();

                    process.execute(get);
                }
            }
        });


    }
}