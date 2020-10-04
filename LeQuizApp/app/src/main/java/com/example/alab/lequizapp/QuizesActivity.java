package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuizesActivity extends AppCompatActivity {

    String user, position;
    private RecyclerView rvContacts;
    QuizesAdapter adapter;
    String ServerIP;


    ArrayList<Quizes> arrayQuizes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizes);

        Intent intent = getIntent();
        user = intent.getStringExtra("user");
        position = intent.getStringExtra("position");

        final GlobalClass gclass = (GlobalClass) getApplicationContext();

        ServerIP = gclass.getIPAddress();

        LoadQuizes();
    }

    void LoadRecyclerViewer(){
        rvContacts = (RecyclerView) findViewById(R.id.recyclerView);

        Log.d("Asize", String.valueOf(arrayQuizes.size()));

        adapter = new QuizesAdapter(arrayQuizes);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvContacts.addItemDecoration(itemDecoration);
        rvContacts.setAdapter(adapter);

         rvContacts.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnItemClickListener(new QuizesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String id = String.valueOf(arrayQuizes.get(position).getQuizID());
                Toast.makeText(QuizesActivity.this, id + " was clicked!", Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, "sample", Toast.LENGTH_SHORT).show();
            }


        });
    }

    void LoadQuizes(){
        String url = ServerIP+"/android/quizes/" + user;

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("quiz", "Register Response: " + response.toString());

                        try {
                            JSONArray array = new JSONArray(response);
                            arrayQuizes = new ArrayList<Quizes>();
                            for(int i=0; i < array.length(); i++){
                                JSONObject obj =  array.getJSONObject(i);
                                arrayQuizes.add(new Quizes(obj.getInt("quizID"),
                                       obj.getString("username"),
                                       obj.getString("quizTitle"),
                                       obj.getString("quizDesc")));
                            }
                            //Toast.makeText(QuizesActivity.this, "Array Saved" + String.valueOf(arrayQuizes.size()), Toast.LENGTH_SHORT).show();
                            LoadRecyclerViewer();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // flag = false;
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(stringRequest);
    }
}
