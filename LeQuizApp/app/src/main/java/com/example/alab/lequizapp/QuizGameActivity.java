package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizGameActivity extends AppCompatActivity {



    WebSocket webSocket;
    OkHttpClient client;
    SocketListener socketListener;
    Request request;

    TextView txtTimer, txtQuestion, txtSCore;
    Button btnA, btnB, btnC, btnD;

    public int totalScore=0;

    public String ans="";

    int score=0;

    String user, position, access_code;
    int user_id;

    public QuizGameActivity(){
        socketListener = new SocketListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game);

        txtTimer = findViewById(R.id.txtTimer);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtSCore = findViewById(R.id.txtScore);

        Intent intent = getIntent();
        user = intent.getStringExtra("user");
        user_id = intent.getIntExtra("user_id",0);
        position = intent.getStringExtra("position");
        access_code = intent.getStringExtra("access_code");

        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);


        score = 0;
        instantiateWebSocket();
    }

    private void instantiateWebSocket() {

        client = new OkHttpClient();
        request = new Request.Builder().url("ws://192.168.88.242:8080").build();
        webSocket = client.newWebSocket(request, socketListener);

    }


    @Override
    public void onBackPressed(){
        super.onBackPressed();
        webSocket.send("Byeness");
        webSocket.close(1000,"Disconnected from server.");

    }


    public void clickBtnA(View v){
        socketListener.evaluatAnswer("A");
    }

    public void clickBtnB(View v){
        socketListener.evaluatAnswer("B");
    }

    public void clickBtnC(View v){
        socketListener.evaluatAnswer("C");
    }

    public void clickBtnD(View v){
        socketListener.evaluatAnswer("D");
    }




}
