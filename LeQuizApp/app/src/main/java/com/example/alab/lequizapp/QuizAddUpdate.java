package com.example.alab.lequizapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizAddUpdate extends AppCompatActivity {


    public int id = 0;


    TextView lblQuizTitle, lblQuizDesc;
    EditText txtQuizTitle, txtQuizDEsc, txtAccessCode;
    Spinner spinnerCategory;


    Button btnSave;
    GlobalClass g;

    ArrayAdapter<String> listCategory;




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_add_update);
        g = (GlobalClass) getApplicationContext();

        txtQuizTitle = findViewById(R.id.txtquiz_quiztitle);
        txtQuizDEsc = findViewById(R.id.txtquiz_quizdesc);
        txtAccessCode = findViewById(R.id.txtquiz_accesscode);
        btnSave = findViewById(R.id.btnCategorySave);

        spinnerCategory = findViewById(R.id.spinner_quizCategory);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);


        getCategory();



        if(id > 0){
            getData();
        }else{
            makeMD5();
        }
    }



    public void clickSaveQuiz(View v){
        btnSave.setEnabled(false);
        save();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    void makeMD5(){
        Date currentTime = Calendar.getInstance().getTime();
        //Toast.makeText(this, getMd5(currentTime.toString()).substring(0,6), Toast.LENGTH_SHORT).show();
        txtAccessCode.setText(getMd5(currentTime.toString()).substring(0,6));
    }


    void save(){

        if(txtQuizTitle.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Please input quiz title..", Toast.LENGTH_SHORT).show();
            return;
        }

        if(spinnerCategory.getSelectedItem().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Please select a course.", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "";
        if(id > 0){
            url = g.getIPAddress()+"/android/quiz/update";
        }else{
            url = g.getIPAddress()+"/android/quiz/store";
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
                                txtQuizTitle.setText("");
                                txtQuizDEsc.setText("");
                                Toast.makeText(getApplicationContext(), "Successfully saved. ", Toast.LENGTH_SHORT).show();
                                finish();
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
                    params.put("quiz_id", String.valueOf(id));
                }
                params.put("user_id", String.valueOf(g.getId()));
                params.put("category", spinnerCategory.getSelectedItem().toString());
                params.put("access_code", txtAccessCode.getText().toString());
                params.put("quiz_title", txtQuizTitle.getText().toString());
                params.put("quiz_desc", txtQuizDEsc.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
    }


    private void getData(){
        String url = g.getIPAddress()+"/android/quiz/"+id+"/edit/";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Log.d("response", response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        JSONObject obj = jsonArray.getJSONObject(0);

                        txtQuizTitle.setText(obj.getString("quiz_title"));
                        txtQuizDEsc.setText(obj.getString("quiz_desc"));
                        txtAccessCode.setText(obj.getString("access_code"));

                        if(listCategory != null){
                            String value = obj.getString("category");
                            int spinnerPosition = listCategory.getPosition(value);
                            spinnerCategory.setSelection(spinnerPosition);
                            //setup default value spinner (combobox)
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


    void getCategory(){
        String url = g.getIPAddress()+"/android/category/" + g.getId();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject obj = null;
                        List<String> arr = new ArrayList<String>();
                       arr.clear();

                        for(int i = 0; i < jsonArray.length(); i++){
                            obj = jsonArray.getJSONObject(i);
                            arr.add(obj.getString("category"));
                        }

                        listCategory = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arr);
                        spinnerCategory.setAdapter(listCategory);

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


    public String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }



}
