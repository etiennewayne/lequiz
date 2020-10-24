package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class QuestionAddUpdate extends AppCompatActivity {


    EditText txtquestion, txtopta, txtoptb, txtoptc, txtoptd, txtans, txtsetTime, txtsetEquivSCore;
    Button btnSave;

    GlobalClass g;

    int id = 0, quiz_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_add_update);

        txtquestion = findViewById(R.id.txt_question);
        txtopta = findViewById(R.id.txt_opta);
        txtoptb = findViewById(R.id.txt_optb);
        txtoptc = findViewById(R.id.txt_optc);
        txtoptd = findViewById(R.id.txt_optd);
        txtans = findViewById(R.id.txt_ans);
        txtsetTime = findViewById(R.id.txt_settime);
        txtsetEquivSCore = findViewById(R.id.txt_equivscore);
        btnSave = findViewById(R.id.btnQuestionSave);

        g = (GlobalClass) getApplicationContext();



        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        quiz_id = intent.getIntExtra("quiz_id", 0);

        if(id > 0){
            //getdetails
            btnSave.setEnabled(false);
            getData();
        }


    }



    public void clickQuestionSave(View v){
        btnSave.setEnabled(false);
        save();
    }


    private void getData(){
        String url = g.getIPAddress()+"/android/question/"+id+"/edit/";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Log.d("response", response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        JSONObject obj = jsonArray.getJSONObject(0);

                        txtquestion.setText(obj.getString("question"));
                        txtopta.setText(obj.getString("opt_a"));
                        txtoptb.setText(obj.getString("opt_b"));
                        txtoptc.setText(obj.getString("opt_c"));
                        txtoptd.setText(obj.getString("opt_d"));
                        txtans.setText(obj.getString("ans"));
                        txtsetTime.setText(String.valueOf(obj.getInt("set_time")));
                        txtsetEquivSCore.setText(String.valueOf(obj.getInt("equiv_score")));

                        btnSave.setEnabled(true);

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


    void save(){
        String url = "";
        if(id > 0){
            url = g.getIPAddress()+"/android/question/update";
        }else{
            url = g.getIPAddress()+"/android/question/store";
        }
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    btnSave.setEnabled(true);
                    Log.d("response", response);
                    try {

                        JSONObject obj = new JSONObject(response);
                        if(obj.has("status")){


                            if(obj.getString("status").equalsIgnoreCase("saved")){
                                txtquestion.setText("");
                                txtopta.setText("");
                                txtoptb.setText("");
                                txtoptc.setText("");
                                txtoptd.setText("");
                                txtans.setText("");
                                txtsetTime.setText("");
                                txtsetEquivSCore.setText("");
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
                btnSave.setEnabled(true);
                Log.d("err", "Volley Error"  + error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                if(id > 0){
                    params.put("question_id", String.valueOf(id));
                }
                params.put("question", txtquestion.getText().toString());
                params.put("opt_a", txtopta.getText().toString());
                params.put("opt_b", txtoptb.getText().toString());
                params.put("opt_c", txtoptc.getText().toString());
                params.put("opt_d", txtoptd.getText().toString());
                params.put("ans", txtans.getText().toString());
                params.put("quiz_id", String.valueOf(quiz_id));
                params.put("set_time", txtsetTime.getText().toString());
                params.put("equiv_score", txtsetEquivSCore.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
    }




}
