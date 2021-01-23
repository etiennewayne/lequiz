package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizGameActivity extends AppCompatActivity {

    WebSocket webSocket;
    OkHttpClient client;
    SocketListener socketListener;
    Request request;

    TextView txtTimer, txtQuestion, txtSCore, lblequivscore;
    Button btnA, btnB, btnC, btnD;


    //public declaration
    public String user_answer="", user_answer_desc = "", ans="", ans_desc="";
    public int question_id, totalScore=0, equiv_score;


    GlobalClass g;



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
        lblequivscore = findViewById(R.id.lblequivscore);

        g = (GlobalClass) getApplicationContext();

        Intent intent = getIntent();
        //user = intent.getStringExtra("user");

        user = g.getUsername();
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
        lblequivscore.setText("EQUIV SCORE: ");


        webSocketAddress = g.getWebSocketAddress();
        //Toast.makeText(getApplicationContext(), g.getWebSocketAddress(), Toast.LENGTH_SHORT).show();
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
            "",
            ans,
            ans_desc,
            equiv_score);

        list.add(stdans);
    }


    @Override
    public void onBackPressed(){
        super.onBackPressed();
        disconnectWebSocket();
    }

//    @Override
//    public void onDestroy(){
//        super.onDestroy();
//        disconnectWebSocket();
//    }



    public void disconnectWebSocket(){

        JSONObject connectionJSONObject = new JSONObject();
        try {
            connectionJSONObject.put("key", "exit");
            connectionJSONObject.put("player", g.getUsername());

            JSONArray connectionJSONArray = new JSONArray();
            connectionJSONArray.put(connectionJSONObject);

            webSocket.send(connectionJSONArray.toString());
            webSocket.close(1000,"close connection");

        } catch (JSONException e) {
            Log.d("error", e.getMessage());
        }
    }


    public void clickBtnA(View v){
        user_answer= "";
        ans_desc = "";
        socketListener.evaluatAnswer("A");
        stdans.setUser_ans("A");
        stdans.setUser_ans_desc(btnA.getText().toString());
       passCorrectAns();
        //list.add(stdans);
    }

    public void clickBtnB(View v){
        user_answer= "";
        ans_desc = "";
        socketListener.evaluatAnswer("B");
        stdans.setUser_ans("B");
        stdans.setUser_ans_desc(btnB.getText().toString());
        passCorrectAns();
        //list.add(stdans);
    }

    public void clickBtnC(View v){
        user_answer= "";
        ans_desc = "";
        socketListener.evaluatAnswer("C");
        stdans.setUser_ans("C");
        stdans.setUser_ans_desc(btnC.getText().toString());
        passCorrectAns();
        //list.add(stdans);
    }

    public void clickBtnD(View v){
        user_answer= "";
        ans_desc = "";
        socketListener.evaluatAnswer("D");
        stdans.setUser_ans("D");
        stdans.setUser_ans_desc(btnD.getText().toString());
        passCorrectAns();
        //list.add(stdans);
    }

    void passCorrectAns(){
        if(ans.equalsIgnoreCase("A")){
            stdans.setAns_desc(btnA.getText().toString());
        }else if(ans.equalsIgnoreCase("B")){
            stdans.setAns_desc(btnB.getText().toString());
        }else if(ans.equalsIgnoreCase("C")){
            stdans.setAns_desc(btnC.getText().toString());
        }else if(ans.equalsIgnoreCase("D")) {
            stdans.setAns_desc(btnD.getText().toString());
        }
    }


}
