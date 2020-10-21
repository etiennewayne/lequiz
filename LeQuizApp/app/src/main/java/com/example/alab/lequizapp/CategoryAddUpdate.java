package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryAddUpdate extends AppCompatActivity {


    Spinner spinnerAY;
    GlobalClass g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_add_update);



        g = (GlobalClass) getApplicationContext();

        spinnerAY = (Spinner)findViewById(R.id.spinnerAY);

        getAY();

    }

    void getAY(){
        String url = g.getIPAddress()+"/android/ay";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject obj = null;
                        List<String> arr = new ArrayList<String>();

                        for(int i = 0; i < jsonArray.length(); i++){
                            obj = jsonArray.getJSONObject(i);
                            arr.add(obj.getString("ay_code"));
                        }


                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arr);
                        spinnerAY.setAdapter(adapter);


                    } catch (JSONException e) {
                       Log.d("err", "JSON ERROR " + e.getMessage());
                    }

                }


            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("err", "Volley Error"  + error.getMessage());
            }
        });

        queue.add(stringRequest);
    }
}
