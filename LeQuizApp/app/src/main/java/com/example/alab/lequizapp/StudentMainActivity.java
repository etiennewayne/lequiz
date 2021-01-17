package com.example.alab.lequizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;


import android.content.DialogInterface;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alab.lequizapp.myquizzes.MyQuizzesActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StudentMainActivity extends AppCompatActivity {

    TextView lblStudent, output;
    EditText txtAccessCode;
    Button btnJoinQuiz;


    GlobalClass g;

    String user, position;
    int user_id;

    WebSocket webSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        g = (GlobalClass) getApplicationContext();

        try {
            lblStudent = findViewById(R.id.lblStudent);
            txtAccessCode = findViewById(R.id.txtAccessCode);

            btnJoinQuiz = findViewById(R.id.btnJoinQuiz);

            Intent intent = getIntent();
            user = intent.getStringExtra("user");
            user_id = intent.getIntExtra("user_id",0);
            position = intent.getStringExtra("position");

            //for debugging purpose only==============
            //txtAccessCode.setText("ec1d17");
            //=============================
            txtAccessCode.setText("");



            g.setAccessCode(txtAccessCode.getText().toString());

          //  lblStudent.setText("Enter ACCESS CODE to join the room.");
           // instantiateWebSocket();

        }catch (Exception e){
            Log.e("errstudent", e.getMessage());
        }

        btnJoinQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtAccessCode.getText().toString() == ""){
                    Toast.makeText(getBaseContext(), "Access code is required.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(g.getUsername() != null){
                    btnJoinQuiz.setEnabled(false);
                    Log.d("debug", g.getIPAddress());
                    validateAccessCode();
                }else{
                    Toast.makeText(getBaseContext(), "App error. Please restart the application.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    //-------MENU ITEMS----------//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.my_quizzes:
                Intent intent = new Intent(this, MyQuizzesActivity.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//-------MENU ITEMS----------//



    @Override
    public void onBackPressed(){

        AlertDialog.Builder alert = new AlertDialog.Builder(StudentMainActivity.this);
        alert.setTitle("LOGOUT.");
        alert.setMessage("You will be logout in the Application. Are you sure you want to logout?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(webSocket != null)
                    webSocket.close(1000, "EXIT!");

                dialog.dismiss();
                StudentMainActivity.this.finish();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();

    }

    void validateAccessCode(){
        btnJoinQuiz.setEnabled(false);
        try {
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = g.getIPAddress() + "/android/validate/code";
            //String url = "";

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("access_code", txtAccessCode.getText().toString());


            StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        btnJoinQuiz.setEnabled(true);
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            if(jsonArray.length() > 0){
                                JSONObject obj = jsonArray.getJSONObject(0);

                                if(obj.has("quiz_id")){
                                    int quiz_id = obj.getInt("quiz_id");
                                    //g.setQui(roomid);

                                    Log.d("responseroom", String.valueOf(obj.getInt("quiz_id")));
                                    Intent intent = new Intent(getApplicationContext(), QuizGameActivity.class);
                                    intent.putExtra("position", position);
                                    intent.putExtra("user", user);
                                    intent.putExtra("user_id", user_id);
                                    g.setQuizId(obj.getInt("quiz_id"));

                                    g.setCategory(obj.getString("category"));
                                    g.setAyCode(obj.getString("ay_code"));
                                    g.setAy(obj.getString("ay"));

                                    intent.putExtra("access_code", txtAccessCode.getText().toString());
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(), "Can't connect to the server.", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Code not found.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            btnJoinQuiz.setEnabled(true);
                            Log.d("sma_josnresponse", e.getMessage());
                        }

                        btnJoinQuiz.setEnabled(true);

                    }

                }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Log.d("resultRoomVolleyError", error.getMessage());
                    btnJoinQuiz.setEnabled(true);
                }
            }){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("access_code", txtAccessCode.getText().toString());
                    return params;
                }
            };

            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            Log.d("submitRoomResult", e.getMessage());
            btnJoinQuiz.setEnabled(true);
        }

    }





}
