package com.example.alab.lequizapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class SocketListener extends WebSocketListener {

    public QuizGameActivity  activity;




    int thisScore = 0;

    int question_id;

    String question, opta, optb, optc, optd, user_ans, ans;


  Boolean isSubmitted = false;

    public  SocketListener(QuizGameActivity activity){
        this.activity = activity;


        //nString = new ArrayList<String>();
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

                        //WEB SOCKET HERE========>>
                        //GET THE DATA FROM WEBSOCKET
                        //RECEIVE AND DATA DAUN INVOKE SOME ACTIONS
                        JSONObject obj = new JSONObject(text);

                        if(!obj.has("status")){

                            //!obj.has("status")
                            //means if JSON file has no status property, still there is incoming question,
                            //purpose is to identify if the quiz will still continue or end base sa server nga na fetch nga questions

                            activity.question_id =obj.getInt("question_id");
                            question_id = obj.getInt("question_id");
                            question = obj.getString("question");
                            int timer = obj.getInt("set_time");
                            activity.txtQuestion.setText(question);
                            //activity.txtTimer.setText(timer);
                            activity.btnA.setText(obj.getString("opt_a"));
                            opta = obj.getString("opt_a");

                            activity.btnB.setText(obj.getString("opt_b"));
                            activity.btnC.setText(obj.getString("opt_c"));
                            activity.btnD.setText(obj.getString("opt_d"));
                            activity.ans = obj.getString("ans");

                            activity.equiv_score = obj.getInt("equiv_score");
                            thisScore = obj.getInt("equiv_score");

                            isSubmitted = false;
                            activity.addToListArray();
                            setButton(true); //enable button in every question
                            startTime(timer);

                        }else{
                            //ELSE
                            //IF NAA STATUS THEN THE QUIZ IS OVER
                            //CALL THE RESULT ACTIVITY,
                            //DISPLAY RESULT FROM ListArray<StudentAnswer>

                            Intent intent = new Intent(activity, Result.class);
                            intent.putParcelableArrayListExtra("myList", (ArrayList<? extends Parcelable>) activity.list);
                            intent.putExtra("totalSCore", activity.totalScore);
                            activity.startActivity(intent);

                            //activity finish means, destroy this activity after showing the result
                            //para dili maka back ang user dere...
                            activity.finish();
                        }

                    } catch (Exception e) {
                        Log.d("arrayError", e.getMessage());
                       // e.printStackTrace();
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
                //when button click (naka choose nag answer ang user)
                //after question add these information sa array
                //para sa result sa last intent sa game
                //save as history sa game
//                if(!isSubmitted){
//                    activity.addToListArray();
//                    Log.d("addlistarray", "User Answer : (" + activity.user_answer + ") - Question : " + activity.txtQuestion.getText().toString() + " -  Size of Array : " + String.valueOf(activity.list.size()));
//                }
//
//                activity.user_answer = "";
//                activity.ans = "";
            }
        }.start();
    }


    public void evaluatAnswer(String choice){

        if(activity.ans.equalsIgnoreCase(choice)){
            Toast.makeText(activity, "Your answer is correct.", Toast.LENGTH_SHORT).show();
            activity.totalScore+= thisScore;
            activity.txtSCore.setText("Score : " + String.valueOf(activity.totalScore));
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
