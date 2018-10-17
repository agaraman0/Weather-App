package com.example.aman_py.jsondemo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.aman_py.jsondemo.MainActivity.city_name;

public class fetch extends AsyncTask<String,Void,String> {
    public String result ="";
    public String weather,wind,cityname;
    public String temp;
    Context ctx;
    @Override
    protected String doInBackground(String... s) {
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+s[0].toString()+"&appid=770297e3792414ec205e9282b0ca25cb");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream inputStream = http.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            int data = reader.read();
            while (data != -1){
                char current = (char) data;
                result +=current;
                data = reader.read();
            }
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            String line = "";
//
//            while (line!=null){
//                line=bufferedReader.readLine();
//                data+=line;
//            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }return null;
    }
    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray arr = new JSONArray(jsonObject.getString("weather"));
            JSONObject jsonObject1 = arr.getJSONObject(0);
            String temp = jsonObject.getJSONObject("main").getString("temp");
            String wind = jsonObject.getJSONObject("wind").getString("speed");
            String cityname = jsonObject.getString("name");
            MainActivity.city.setText(null);
            MainActivity.wind.setText(null);
            MainActivity.temp.setText(null);
            MainActivity.weather.setText(null);
            if (result != "") {
                MainActivity.weather.setText(jsonObject1.getString("description"));
                MainActivity.city.setText(cityname);
                MainActivity.wind.setText(wind + "m/s");
                MainActivity.temp.setText(temp);
            }else {
                Log.i("info",result);
                city_name.setError("City Not Found");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
