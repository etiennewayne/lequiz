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

import java.util.ArrayList;
import java.util.List;

public class QuizGameActivity extends AppCompatActivity {

    WebSocket webSocket;
    OkHttpClient client;
    SocketListener socketListener;
    Request request;

    TextView txtTimer, txtQuestion, txtSCore;
    Button btnA, btnB, btnC, btnD;


    //public declaration
    public String user_answer="", ans="";
    public int question_id, totalScore=0, equiv_score;



    String user, position, access_code;
    int user_id;
    String webSocketAddress;


    List<StudentAnswer> list;

    StudentAnswer stdans;


    public QuizGameActivity(){
        socketListener = new SocketListener(this);
        list = new ArrayList<StudentAnswer>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game);

        txtTimer = findViewById(R.id.txtTimer);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtSCore = findViewById(R.id.txtScore);

        Intent intent = getIntent();
        //user = intent.getStringExtra("user");
        final GlobalClass gClass = (GlobalClass) getApplicationContext();

        user = gClass.getUsername();
        user_id = intent.getIntExtra("user_id",0);
        position = intent.getStringExtra("position");
        access_code = intent.getStringExtra("access_code");

        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);


        btnA.setText("WAITING...");
        btnB.setText("WAITING...");
        btnC.setText("WAITING...");
        btnD.setText("WAITING...");


        webSocketAddress = gClass.getWebSocketAddress();

        //score = 0;

        //list = new ArrayList<StudentAnswer>();
        instantiateWebSocket();




    }

    private void instantiateWebSocket() {

        client = new OkHttpClient();
        request = new Request.Builder().url(webSocketAddress).build();
        webSocket = client.newWebSocket(request, socketListener);
    }


    public void addToListArray(){
        stdans = new StudentAnswer(question_id,
            txtQuestion.getText().toString(),
            btnA.getText().toString(),
            btnB.getText().toString(),
            btnC.getText().toString(),
            btnD.getText().toString(),
            "",
            ans,
            equiv_score);

        list.add(stdans);
    }


    @Override
    public void onBackPressed(){
        super.onBackPressed();
        webSocket.send("Byeness");
        webSocket.close(1000,"Disconnected from server.");
    }


    public void clickBtnA(View v){
        user_answer= "";
        socketListener.evaluatAnswer("A");
        stdans.setUser_ans("A");
        //list.add(stdans);
    }

    public void clickBtnB(View v){
        user_answer= "";
        socketListener.evaluatAnswer("B");
        stdans.setUser_ans("B");
        //list.add(stdans);
    }

    public void clickBtnC(View v){
        user_answer= "";
        socketListener.evaluatAnswer("C");
        stdans.setUser_ans("C");
        //list.add(stdans);
    }

    public void clickBtnD(View v){
        user_answer= "";
        socketListener.evaluatAnswer("D");
        stdans.setUser_ans("D");
        //list.add(stdans);
    }


}
