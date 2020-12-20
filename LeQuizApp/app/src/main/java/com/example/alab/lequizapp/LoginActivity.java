package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class LoginActivity extends AppCompatActivity {
    EditText txtuser;
    EditText txtpwd;
    Button btnLogin;


    String position = "";
    String ServerIP="";


    GlobalClass gclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        txtuser = findViewById(R.id.txtUser);
        txtpwd = findViewById(R.id.txtPwd);
        btnLogin = findViewById(R.id.btnLogin);


        //for debugging purpose
       // txtuser.setText("halgadipe");
        txtuser.setText("jrey");
        txtpwd.setText("1234");


       // txtuser.setText("");
       // txtpwd.setText("");

        gclass = (GlobalClass) getApplicationContext();
        gclass.setIPAddress("192.168.0.10");


    }

    void Auth(String user, String pwd){
        btnLogin.setClickable(false);
        //boolean flag=false;

        //String url = ServerIP+"/android/login?username="+user+"&password="+pwd;
        String url = ServerIP + "/android/login";
     //  String postURL = "http://192.168.15.242/android/login";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        btnLogin.setClickable(true);
                        if(response == null){
                            Toast.makeText(getApplicationContext(), "Server response empty.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try {
                            JSONArray array = new JSONArray(response);
                            if(array.length()>0){
                                JSONObject obj =  array.getJSONObject(0);
                                position = obj.getString("classification");
                                gclass.setId(obj.getInt("user_id"));
                                gclass.setUsername(obj.getString("username"));
                                gclass.setLname(obj.getString("lname"));
                                gclass.setFname(obj.getString("fname"));
                                gclass.setMname(obj.getString("mname"));
                                gclass.setPosition(obj.getString("classification"));


                                if(position.equalsIgnoreCase(("faculty"))){
                                    Intent intent = new Intent(getApplicationContext(), TeacherPanelActivity.class);
                                    //EditText editText = (EditText) findViewById(R.id.editText);
                                    intent.putExtra("position", position);
                                    intent.putExtra("user", obj.getString("username"));
                                    startActivity(intent);
                                    finish();

                                }

                                if(position.equalsIgnoreCase(("student"))){
                                    Intent intent = new Intent(getApplicationContext(), StudentMainActivity.class);
                                    //EditText editText = (EditText) findViewById(R.id.editText);

                                    intent.putExtra("position", position);
                                    intent.putExtra("user", obj.getString("username"));
                                    intent.putExtra("user_id", obj.getInt("user_id"));

                                    //Toast.makeText(getApplicationContext(), String.valueOf(gclass.getId()), Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                    finish();
                                }



                            }else{
                                Toast.makeText(LoginActivity.this, "Username and password error!", Toast.LENGTH_SHORT).show();
                                btnLogin.setClickable(true);
                            }


                        } catch (Exception e) {
                            Log.d("errlogin", e.getMessage());
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            btnLogin.setClickable(true);
                           // flag = false;
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                btnLogin.setClickable(true);

                Log.d("error", error.getMessage());

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(),"No response from the server. Time out Error.",Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    //TODO
                } else if (error instanceof ServerError) {
                    Toast.makeText(getApplicationContext(),"No response from the server. Server error.",Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    //TODO
                    Toast.makeText(getApplicationContext(),"No response from the server. Network error.",Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    //TODO
                }

            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", txtuser.getText().toString());
                params.put("password", txtpwd.getText().toString());
                return params;
            }
        };

        queue.add(stringRequest);
    }

    public void clickLogin(View v){
        ServerIP = gclass.getIPAddress();

        //Toast.makeText(this, gclass.getIPAddress(), Toast.LENGTH_SHORT).show();


        if(gclass.getIPAddress() == null){
            Toast.makeText(this, "Please set server ip address.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(txtuser.getText().toString().isEmpty() || txtpwd.getText().toString().isEmpty()){
            Toast.makeText(this, "Please input credentials.", Toast.LENGTH_SHORT).show();
            return;
        }

        btnLogin.setClickable(false);
        try{
            Auth(txtuser.getText().toString(), txtpwd.getText().toString());
        }catch (Exception err){
            Toast.makeText(this, "Cannot contact server.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ip_setting:
                Intent intent = new Intent(this, IPAddressActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
