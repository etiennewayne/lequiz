package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txtview);

        //RequestQueue queue = Volley.newRequestQueue(this);
       // getDataFormURL();

    }


    public void getDataFormURL(){
        String url = "http://192.168.15.242/android/login/admin/admin";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //Toast.makeText(MainActivity.this, "Pleas response", Toast.LENGTH_SHORT).show();

                        try {
                            JSONArray array = new JSONArray(response);
                            for(int i=0; i < array.length(); i++){
                                JSONObject obj =  array.getJSONObject(i);
                                //

                                textView.setText("Response is: " + obj.getString("username"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

        queue.add(stringRequest);

    }


    public void testClick(View v){
        getDataFormURL();
        Toast.makeText(MainActivity.this, "Button Click", Toast.LENGTH_SHORT).show();
    }
}
