package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class Result extends AppCompatActivity implements ResultAdapter.ListItemClickListener {

    TextView lblTxt1;
    EditText txtbox1;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<StudentAnswer> myList;

    GlobalClass g;

    int totalScore=0;

    TextView txtresult_totalScore;
    Button btn_BackLobby;

    public Result(){
       //
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        recyclerView = (RecyclerView) findViewById(R.id.result_recycler);
        txtresult_totalScore = (TextView) findViewById(R.id.result_txtvwTotalScore);
        btn_BackLobby = findViewById(R.id.btn_BackLobby);
       // btn_BackLobby

        g = (GlobalClass) getApplicationContext();

        try{
            Intent intent = getIntent();
            myList = intent.getParcelableArrayListExtra("myList");
            totalScore = intent.getIntExtra("totalSCore", totalScore);
            txtresult_totalScore.setText("SCORE: " + totalScore);


            ResultAdapter adapter = new ResultAdapter(myList, this);
            //recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);


            // specify an adapter (see also next example)
            mAdapter = new ResultAdapter(myList, this);
            recyclerView.setAdapter(mAdapter);

        }catch(Exception e){
            Log.d("intentParcelable", e.getMessage());
        }


    }

    @Override
    public void onListItemClick(int position) {
        //Toast.makeText(this, myList.get(position).getQuestion(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //finish();
    }


    public void btnBackLobby(View v){
        btn_BackLobby.setEnabled(false);
        SubmitResult();
    }

    void SubmitResult(){

        try {


            RequestQueue queue = Volley.newRequestQueue(this);
            String url = g.getIPAddress() + "/android/quizgame/store";
            //String url = "";

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("user_id", g.getId());
            jsonBody.put("quiz_id", g.getQuizId());
            jsonBody.put("ay_code", g.getAyCode());
            jsonBody.put("course", g.getCategory());
            jsonBody.put("total_score", totalScore);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d("responvolley", response.toString());
                        try {
                            String status = response.getString("status");
                            if(status.equalsIgnoreCase("saved")){
                                btn_BackLobby.setEnabled(true);
                                getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                finish();

                                Intent i = new Intent(getApplicationContext(), StudentMainActivity.class);
                                // set the new task and clear flags
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                            }

                        } catch (JSONException e) {
                            Log.d("resultVolleyError", e.getMessage());
                        }
                    }

                }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Log.d("resultVolleyError", error.getMessage());
                }
            });

            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            Log.d("submitResult", e.getMessage());
        }



    }


}
