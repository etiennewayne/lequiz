package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentMainActivity extends AppCompatActivity {

    TextView lblStudent, output;
    EditText txtRoomName;
    Button btnJoinRoom;
    

    String user, position;
    int user_id;

    WebSocket webSocket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        try {
            lblStudent = findViewById(R.id.lblStudent);
            txtRoomName = findViewById(R.id.txtAccessCode);

            btnJoinRoom = findViewById(R.id.btnjoinroom);

            Intent intent = getIntent();
            user = intent.getStringExtra("user");
            user_id = intent.getIntExtra("user_id",0);
            position = intent.getStringExtra("position");


            txtRoomName.setText("e412ef");
          //  lblStudent.setText("Enter ACCESS CODE to join the room.");
            
           // instantiateWebSocket();

        }catch (Exception e){
            Log.e("errstudent", e.getMessage());
        }

        btnJoinRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizGameActivity.class);

                intent.putExtra("position", position);
                intent.putExtra("user", user);
                intent.putExtra("user_id", user_id);
                intent.putExtra("access_code", txtRoomName.getText().toString());

                startActivity(intent);
            }
        });

    }





    @Override
    public void onBackPressed(){
        if(webSocket != null)
            webSocket.close(1000, "EXIT!");
        StudentMainActivity.this.finish();
    }
    




}
