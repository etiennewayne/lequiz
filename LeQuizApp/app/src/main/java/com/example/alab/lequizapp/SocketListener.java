package com.example.alab.lequizapp;

import android.os.CountDownTimer;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class SocketListener extends WebSocketListener {

    public QuizGameActivity  activity;

    int thisScore = 0;


    public  SocketListener(QuizGameActivity activity){
        this.activity = activity;
    }


    @Override
    public void onOpen(final WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, "Connection established", Toast.LENGTH_LONG).show();


                try {
                    JSONObject connectionJSONObject = new JSONObject();
                    connectionJSONObject.put("key", "joining");
                    connectionJSONObject.put("access_code", activity.access_code);
                    connectionJSONObject.put("player", activity.user);

                    JSONArray connectionJSONArray = new JSONArray();
                    connectionJSONArray.put(connectionJSONObject);

                    webSocket.send(connectionJSONArray.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
            }
            }
        });
    }

    @Override
    public void onMessage(WebSocket webSocket, final String text) {
        super.onMessage(webSocket, text);


            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    try {

                        JSONObject obj = new JSONObject(text);
                        String question = obj.getString("question");
                        int timer = obj.getInt("set_time");
                        activity.txtQuestion.setText(question);
                        //activity.txtTimer.setText(timer);
                        activity.btnA.setText(obj.getString("opt_a"));
                        activity.btnB.setText(obj.getString("opt_b"));
                        activity.btnC.setText(obj.getString("opt_c"));
                        activity.btnD.setText(obj.getString("opt_d"));
                        activity.ans = obj.getString("ans");
                        thisScore = obj.getInt("equiv_score");
                        setButton(true); //enable button in every question
                        startTime(timer);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            });


    }


   void setButton(boolean flag){
        activity.btnA.setEnabled(flag);
        activity.btnB.setEnabled(flag);
        activity.btnC.setEnabled(flag);
        activity.btnD.setEnabled(flag);
    }



    private void startTime(int timer){

        setButton(true); //incase of button enabled failure

        new CountDownTimer(timer * 1000, 1000) {

            public void onTick(long millisUntilFinished) {

                activity.txtTimer.setText("Seconds remaining: " + millisUntilFinished / 1000);
                if((millisUntilFinished/1000) == 0){
                    setButton(false);
                }
            }

            public void onFinish() {
                activity.txtTimer.setText("Stop!");
            }
        }.start();
    }


    public void evaluatAnswer(String choice){
        if(activity.ans.equalsIgnoreCase(choice)){
            Toast.makeText(activity, "Your answer is correct.", Toast.LENGTH_SHORT).show();
            activity.totalScore+= thisScore;
            activity.txtSCore.setText(String.valueOf(activity.totalScore));
        }else{
            Toast.makeText(activity, "Your answer is wrong.", Toast.LENGTH_SHORT).show();
        }
        setButton(false);
    }









    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);



    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);

        webSocket.send("exit ko.. babye");
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
    }

}
