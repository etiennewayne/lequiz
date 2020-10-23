package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryAddUpdate extends AppCompatActivity {


    Spinner spinnerAY;

    EditText txtCategory, txtCategoryDesc;
    GlobalClass g;

    ArrayAdapter<String> adapter;


    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_add_update);



        g = (GlobalClass) getApplicationContext();

        spinnerAY = (Spinner)findViewById(R.id.spinnerAY);
        txtCategory = (EditText) findViewById(R.id.txtCategory);
        txtCategoryDesc = (EditText) findViewById(R.id.txtCategoryDesc);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        getAY();



        if(id > 0){
            //getdetails
            getData();
        }

    }


    public void clickCategorySave(View v){
       save();
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


                        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arr);
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



    void save(){
        String url = "";
        if(id > 0){
            url = g.getIPAddress()+"/android/category/update";
        }else{
            url = g.getIPAddress()+"/android/category/store";
        }
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.d("response", response);
                    try {

                        JSONObject obj = new JSONObject(response);
                        if(obj.has("status")){
                            if(obj.getString("status").equalsIgnoreCase("saved")){
                                txtCategory.setText("");
                                txtCategoryDesc.setText("");
                                Toast.makeText(getApplicationContext(), "Successfully saved. ", Toast.LENGTH_SHORT).show();
                            }else if(obj.getString("status").equalsIgnoreCase("updated")){
                                Toast.makeText(getApplicationContext(), "Successfully updated.", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "Save failed. Please check your inputs.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Save failed. Please check your network or contact System Administrator.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Log.d("json-error", e.getMessage());
                    }
                }


            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("err", "Volley Error"  + error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                if(id > 0){
                    params.put("category_id", String.valueOf(id));
                }
                params.put("user_id", String.valueOf(g.getId()));
                params.put("aycode", spinnerAY.getSelectedItem().toString());
                params.put("category", txtCategory.getText().toString());
                params.put("category_desc", txtCategoryDesc.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
    }


    private void getData(){
        String url = g.getIPAddress()+"/android/category/"+id+"/edit/";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                     //Log.d("response", response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        JSONObject obj = jsonArray.getJSONObject(0);

                        txtCategory.setText(obj.getString("category"));
                        txtCategoryDesc.setText(obj.getString("category_desc"));

                        if(adapter != null){
                            String ayValue = obj.getString("ay_code");
                            int spinnerPosition = adapter.getPosition(ayValue);
                            spinnerAY.setSelection(spinnerPosition);
                        }

                    } catch (JSONException e) {
                        Log.d("json-error", e.getMessage());
                    }
                }


            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("err", "VolleyError"  + error.getMessage());
            }
        });
        queue.add(stringRequest);
    }


}
