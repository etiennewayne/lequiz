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
import java.util.List;

public class StudentQuizzesActivity extends AppCompatActivity {


    GlobalClass g;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    List<StudentQuizzes> stdQuizList = new ArrayList<StudentQuizzes>();

    int room_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_quizzes);

        g = (GlobalClass) getApplicationContext();

        recyclerView = findViewById(R.id.recycleView_studentQuizzes);
        Intent intent = getIntent();
        room_id = intent.getIntExtra("room_id", 0);

        LoadData();
    }

    void bindRecyclerViewer(){
        adapter = new StudentQuizzesAdapter(stdQuizList);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    StudentQuizzesAdapter adapter;


    private void LoadData(){
        try{

            RequestQueue queue = Volley.newRequestQueue(this);
            String url = g.getIPAddress() + "/android/room/student-list/" + room_id;

            StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("studentlist", response);
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            JSONObject obj;

                            if(jsonArray.length() > 0){
                                for(int i=0;i < jsonArray.length(); i++){
                                    obj  = jsonArray.getJSONObject(i);

                                    stdQuizList.add(new StudentQuizzes(obj.getString("username"),
                                        obj.getString("lname"),
                                        obj.getString("fname"),
                                        obj.getString("mname"),
                                        obj.getString("quiz_title"),
                                        obj.getString("quiz_desc"),
                                        obj.getString("room"),
                                        obj.getString("room_desc"),
                                        obj.getString("access_code"),
                                        obj.getInt("user_id"),
                                        obj.getInt("room_id"),
                                        obj.getInt("quiz_id"),
                                        obj.getInt("total_score")));
                                }

                                bindRecyclerViewer();
                            }else{
                                Toast.makeText(getApplicationContext(), "No students found.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            Log.d("jsonresponse", e.getMessage());
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





}
