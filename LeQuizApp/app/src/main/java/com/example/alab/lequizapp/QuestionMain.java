package com.example.alab.lequizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionMain extends AppCompatActivity {

    GlobalClass g;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    List<Question> questionList = new ArrayList<Question>();

    public int quiz_id = 0;

    TextView txtvwQuestionContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_main);


        g = (GlobalClass) getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recycleView_questions);

        Intent intent = getIntent();
        quiz_id = intent.getIntExtra("quiz_id", 0);


    }

    public void clickQuestionNew(View v){
        Intent intent = new Intent(getApplicationContext(), QuestionAddUpdate.class);
        intent.putExtra("id", 0);
        intent.putExtra("quiz_id", quiz_id);
        startActivity(intent);
    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        questionList.clear();
        loadData();
    }


    private void loadData(){
        try{
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = g.getIPAddress() + "/android/question?user_id=" + g.getId()+"&quiz_id="+quiz_id;

            StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            JSONObject obj;

                            if(jsonArray.length() > 0){
                                for(int i=0;i < jsonArray.length(); i++){
                                    obj  = jsonArray.getJSONObject(i);

                                    questionList.add(new Question(obj.getInt("question_id"),
                                        obj.getInt("quiz_id"),
                                        obj.getString("question"),
                                        obj.getString("opt_a"),
                                        obj.getString("opt_b"),
                                        obj.getString("opt_c"),
                                        obj.getString("opt_d"),
                                        obj.getString("ans"),
                                        obj.getInt("set_time"),
                                        obj.getInt("equiv_score")));
                                }

                                bindRecyclerView();

                            }else{
                                Toast.makeText(getApplicationContext(), "No questions found.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            Log.d("sma_josnresponse", e.getMessage());
                        }
                    }

                }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Log.d("resultRoomVolleyError", error.getMessage());
                }
            });

            queue.add(jsonObjectRequest);

        }catch (Exception e){
            Log.d("error", e.getMessage());
        }
    }




    void bindRecyclerView(){
        QuestionRecyclerAdapter adapter = new QuestionRecyclerAdapter(questionList, new QuestionRecyclerAdapter.ButtonsClickListener() {
            @Override
            public void editClick(View v, int position) {
                int id = questionList.get(position).getQuestionId();
                //Log.d("categoryid", String.valueOf(id));
                Intent intent = new Intent(getApplicationContext(), QuestionAddUpdate.class);
                intent.putExtra("id", id);
                intent.putExtra("quiz_id", quiz_id);
                startActivity(intent);
            }

            @Override
            public void deleteClick(View v, int position) {

                int itemId = questionList.get(position).getQuestionId();
                alertDelete(itemId);

            }
        });

        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }

    void alertDelete(final int id){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("DELETE CATEGORY?");
        alert.setMessage("Are you sure you want to delete this category?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                delete(id);
            }
        });

        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    private void delete(final int itemId){
        String url = g.getIPAddress()+"/android/question/delete";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("response", response);
                    try {

                        JSONObject obj = new JSONObject(response);

                        if(obj.has("status")){
                            if(obj.getString("status").equalsIgnoreCase("deleted")){

                                Toast.makeText(getApplicationContext(), "Successfully deleted. ", Toast.LENGTH_SHORT).show();
                                questionList.clear();
                                loadData();
                            }
                        }

                    } catch (JSONException e) {
                        Log.d("json-error", e.getMessage());
                    }
                }


            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("err", "VolleyError"  + error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("question_id", String.valueOf(itemId));
                return params;
            }
        };
        queue.add(stringRequest);
    }














}
