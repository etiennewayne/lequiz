package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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



        gclass = (GlobalClass) getApplicationContext();
        gclass.setIPAddress("http://192.168.254.10");
        gclass.setWebSocketAddress("ws://192.168.254.10:8080");


        ServerIP = gclass.getIPAddress();
    }


    private Map<String, String> getParams()
    {
        Map<String, String>  params = new HashMap<String, String>();
        params.put("username", txtuser.getText().toString());
        params.put("password", txtpwd.getText().toString());

        return params;
    }


    void Auth(String user, String pwd){

        //boolean flag=false;

        String url = ServerIP+"/android/login?username="+user+"&password="+pwd;
        //String url = "http://192.168.254.10/android/login";
     //  String postURL = "http://192.168.15.242/android/login";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("response", "Register Response: " + response.toString());

                        try {
                            JSONArray array = new JSONArray(response);
                            //Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

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
                                    startActivity(intent);
                                    finish();
                                }



                            }else{
                                Toast.makeText(LoginActivity.this, "Username and password error!", Toast.LENGTH_SHORT).show();
                                btnLogin.setClickable(true);
                            }


                        } catch (Exception e) {

                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            btnLogin.setClickable(true);
                           // flag = false;
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    Log.d("errlogin", error.getMessage());
                //flag = fal error.printStackTrace();se;
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                btnLogin.setClickable(true);

            }
        });

        queue.add(stringRequest);
    }

    public void clickLogin(View v){

        if(txtuser.getText().toString().isEmpty() || txtpwd.getText().toString().isEmpty()){
            Toast.makeText(this, "Please input credentials.", Toast.LENGTH_SHORT).show();
            return;
        }

        btnLogin.setClickable(false);
        Auth(txtuser.getText().toString(), txtpwd.getText().toString());


    }

//    @Override
//    public void onBackPressed(){
//       // LoginActivity.this.finish();
//    }

}
