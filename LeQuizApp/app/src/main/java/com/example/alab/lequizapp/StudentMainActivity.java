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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StudentMainActivity extends AppCompatActivity {

    TextView lblStudent, output;
    EditText txtRoomName;
    Button btnJoinRoom;


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
            txtRoomName = findViewById(R.id.txtAccessCode);

            btnJoinRoom = findViewById(R.id.btnjoinroom);

            Intent intent = getIntent();
            user = intent.getStringExtra("user");
            user_id = intent.getIntExtra("user_id",0);
            position = intent.getStringExtra("position");

            txtRoomName.setText("2e117e");
            g.setAccessCode(txtRoomName.getText().toString());

          //  lblStudent.setText("Enter ACCESS CODE to join the room.");

           // instantiateWebSocket();

        }catch (Exception e){
            Log.e("errstudent", e.getMessage());
        }

        btnJoinRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(g.getUsername() != null){
                    btnJoinRoom.setEnabled(false);
                    validateAccessCode();
                }else{
                    Toast.makeText(getBaseContext(), "App error. Please restart the application.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }





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
        btnJoinRoom.setEnabled(false);
        try {
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = g.getIPAddress() + "/android/validate/code";
            //String url = "";

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("access_code", txtRoomName.getText().toString());


            StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        btnJoinRoom.setEnabled(true);
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            if(jsonArray.length() > 0){
                                JSONObject obj = jsonArray.getJSONObject(0);

                                if(obj.has("room_id")){
                                    int roomid = obj.getInt("room_id");
                                    g.setRoomId(roomid);
                                    Log.d("responseroom", String.valueOf(obj.getInt("room_id")));
                                    Intent intent = new Intent(getApplicationContext(), QuizGameActivity.class);

                                    intent.putExtra("position", position);
                                    intent.putExtra("user", user);
                                    intent.putExtra("user_id", user_id);
                                    intent.putExtra("access_code", txtRoomName.getText().toString());
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(), "Can't connect to the server.", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Code not found.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            btnJoinRoom.setEnabled(true);
                            Log.d("sma_josnresponse", e.getMessage());
                        }

                        btnJoinRoom.setEnabled(true);
//

                    }

                }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Log.d("resultRoomVolleyError", error.getMessage());
                    btnJoinRoom.setEnabled(true);
                }
            }){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("access_code", txtRoomName.getText().toString());
                    return params;
                }
            };

            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            Log.d("submitRoomResult", e.getMessage());
            btnJoinRoom.setEnabled(true);
        }



    }





}
