package com.example.alab.lequizapp.myquizzes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.alab.lequizapp.Category;
import com.example.alab.lequizapp.CategoryAddUpdate;
import com.example.alab.lequizapp.CategoryRecyclerAdapter;
import com.example.alab.lequizapp.GlobalClass;
import com.example.alab.lequizapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyQuizzesActivity extends AppCompatActivity {

    GlobalClass g;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    List<MyQuizzes> quizList = new ArrayList<MyQuizzes>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_quizzes);

        g = (GlobalClass) getApplicationContext();

        recyclerView = findViewById(R.id.recycleView_myquizzes);



        LoadData();
    }

    void bindRecyclerView(){
        MyQuizzesAdapter adapter = new MyQuizzesAdapter(quizList);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


    private void LoadData(){
        try{
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = g.getIPAddress() + "/android/room/my-quizzes/" + g.getId();

            StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("category", response);
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            JSONObject obj;

                            if(jsonArray.length() > 0){
                                for(int i=0;i < jsonArray.length(); i++){
                                    obj  = jsonArray.getJSONObject(i);

                                    quizList.add(new MyQuizzes(obj.getString("quiz_title"),
                                        obj.getString("quiz_desc"),
                                        obj.getString("access_code"),
                                        obj.getInt("room_id"),
                                        obj.getInt("quiz_id"),
                                        obj.getInt("total_score")));
                                }

                                bindRecyclerView();

                            }else{
                                Toast.makeText(getApplicationContext(), "No categories found.", Toast.LENGTH_SHORT).show();
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








}
