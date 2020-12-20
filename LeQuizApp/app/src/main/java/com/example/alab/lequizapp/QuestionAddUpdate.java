package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class QuestionAddUpdate extends AppCompatActivity {


    EditText txtquestion, txtopta, txtoptb, txtoptc, txtoptd,  txtsetTime, txtsetEquivSCore;
    Button btnSave;
    Spinner spinnerAns;

    String[] choices = { "A", "B", "C", "D" };

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
        spinnerAns = findViewById(R.id.spinner_ans);
        txtsetTime = findViewById(R.id.txt_settime);
        txtsetEquivSCore = findViewById(R.id.txt_equivscore);
        btnSave = findViewById(R.id.btnQuestionSave);

        setSpinner();

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

    ArrayAdapter aa;

     void setSpinner(){
         aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,choices);
         aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         //Setting the ArrayAdapter data on the Spinner
         spinnerAns.setAdapter(aa);
     }

    public void clickQuestionSave(View v){

        if(txtquestion.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(), "Please input question", Toast.LENGTH_SHORT).show();
            return;
        }

        if(txtopta.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(), "Please input OPTION A", Toast.LENGTH_SHORT).show();
            return;
        }

        if(txtoptb.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(), "Please input OPTION B", Toast.LENGTH_SHORT).show();
            return;
        }

        if(txtoptc.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(), "Please input OPTION C", Toast.LENGTH_SHORT).show();
            return;
        }

        if(txtoptd.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(), "Please input OPTION D", Toast.LENGTH_SHORT).show();
            return;
        }

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

                        int spinnerPosition = aa.getPosition(obj.getString("ans"));
                        spinnerAns.setSelection(spinnerPosition);

                        //txtans.setText(obj.getString("ans"));
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
                                //txtans.setText("");
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
                params.put("ans", spinnerAns.getSelectedItem().toString());
                params.put("quiz_id", String.valueOf(quiz_id));


                String settime = txtsetTime.getText().toString();
                if(!settime.equalsIgnoreCase("")){
                    try {
                        int setTime = Integer.parseInt(settime);
                        if(setTime > 0){
                            params.put("set_time", settime);
                        }else{
                            params.put("set_time", "10");
                        }
                    }catch (NumberFormatException nfe){
                        //Toast.makeText(getApplicationContext(), nfe.getMessage(), Toast.LENGTH_SHORT).show();
                        params.put("set_time", "10");
                    }
                }else{
                    params.put("set_time", "10");
                }

                String equivscore = txtsetEquivSCore.getText().toString();
                if(!equivscore.equalsIgnoreCase("")){
                    try {
                        int equivScore = Integer.parseInt(equivscore);
                        if(equivScore > 0){
                            params.put("equiv_score", equivscore);
                        }else{
                            params.put("equiv_score", "1");
                        }
                    }catch (NumberFormatException nfe){
                        //Toast.makeText(getApplicationContext(), nfe.getMessage(), Toast.LENGTH_SHORT).show();
                        params.put("equiv_score", "1");
                    }
                }else{
                    params.put("equiv_score", "1");
                }


                return params;
            }
        };
        queue.add(stringRequest);
    }




}
