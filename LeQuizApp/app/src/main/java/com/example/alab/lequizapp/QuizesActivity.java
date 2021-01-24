package com.example.alab.lequizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
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

public class QuizesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String user, position;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    Spinner spinnerAY;


    QuizesAdapter adapter;
    String ServerIP;
    GlobalClass gclass;
    String ay;


    List<Quizes> listQuizzes = new ArrayList<Quizes>();


    ArrayAdapter<String> ayAdapter;
    List<String> arrAY;


    ProgressBar progressSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizes);

        gclass = (GlobalClass) getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.recycleView_Quizzes);
        spinnerAY = findViewById(R.id.spinnerAY);

        ServerIP = gclass.getIPAddress();

        spinnerAY.setOnItemSelectedListener(this);

        progressSpinner = findViewById(R.id.quizzes_spinnerprogress);
        progressSpinner.setVisibility(View.GONE);

    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        //listQuizzes.clear();
        //LoadQuizes();
        getAcademicYear();
    }



    void bindRecyclerView(){
        QuizesAdapter adapter = new QuizesAdapter(listQuizzes, new QuizesAdapter.ButtonsClickListener() {
            @Override
            public void editClick(View v, int position) {
                Intent intent = new  Intent(getApplicationContext(), QuizAddUpdate.class);
                intent.putExtra("id", listQuizzes.get(position).getQuizID());
                startActivity(intent);
            }

            @Override
            public void deleteClick(View v, int position) {
                int itemId = listQuizzes.get(position).getQuizID();
                alertDelete(itemId);
            }

            @Override
            public void questionClick(View v, int position) {
                int quiz_id = listQuizzes.get(position).getQuizID();
                Intent intent = new Intent(getApplicationContext(), QuestionMain.class);
                intent.putExtra("quiz_id", quiz_id);
                startActivity(intent);
            }

            @Override
            public void studentQuizzesClick(View v, int position) {
                Intent intent = new Intent(getBaseContext(), StudentQuizzesActivity.class);
                int quiz_id = listQuizzes.get(position).getQuizID();
                intent.putExtra("quiz_id", quiz_id);
                startActivity(intent);
            }
        });

        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }



    public void clickQuizNew(View v){
        Intent intent = new Intent(this, QuizAddUpdate.class);
        startActivity(intent);
    }

    void LoadQuizes(){
        progressSpinner.setVisibility(View.VISIBLE);
        spinnerAY.setEnabled(false);

        String url = ServerIP+"/android/quiz/" + gclass.getId() + "/" +ay;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressSpinner.setVisibility(View.GONE);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject obj;

                            if(jsonArray.length() > 0){

                                for(int i=0; i < jsonArray.length(); i++){
                                    obj =  jsonArray.getJSONObject(i);
                                    listQuizzes.add(new Quizes(obj.getInt("quiz_id"),
                                        obj.getInt("user_id"),
                                        obj.getInt("category_id"),
                                        obj.getString("category"),
                                        obj.getString("quiz_title"),
                                        obj.getString("quiz_desc"),
                                        obj.getString("access_code")));
                                }

                                bindRecyclerView();

                            }else{
                                Toast.makeText(getApplicationContext(), "No Quizzes found.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Log.d("jsonerror", e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(),"No response from the server. Time out Error.",Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    //TODO
                } else if (error instanceof ServerError) {
                    Toast.makeText(getApplicationContext(),"No response from the server. Server error.",Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    //TODO
                    Toast.makeText(getApplicationContext(),"No response from the server. Network error.",Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    //TODO
                }
            }
        });

        queue.add(stringRequest);
        spinnerAY.setEnabled(true);
    }

    private void delete(final int itemId){
        String url = gclass.getIPAddress()+"/android/quiz/delete";
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
                                listQuizzes.clear();
                                LoadQuizes();
                            }
                        }
                    } catch (JSONException e) {
                        Log.d("json-error", e.getMessage());
                    }
                }


            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(),"No response from the server. Time out Error.",Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    //TODO
                } else if (error instanceof ServerError) {
                    Toast.makeText(getApplicationContext(),"No response from the server. Server error.",Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    //TODO
                    Toast.makeText(getApplicationContext(),"No response from the server. Network error.",Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    //TODO
                }
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("quiz_id", String.valueOf(itemId));
                return params;
            }
        };
        queue.add(stringRequest);
    }

    void alertDelete(final int id){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("DELETE QUIZ?");
        alert.setMessage("Are you sure you want to delete this quiz?");
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



    private void getAcademicYear(){
        try{
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = gclass.getIPAddress() + "/android/ay";

            StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            JSONObject obj;

                            arrAY = new ArrayList<String>();

                            if(jsonArray.length() > 0){
                                for(int i=0;i < jsonArray.length(); i++){
                                    obj  = jsonArray.getJSONObject(i);
                                    arrAY.add(obj.getString("ay_code"));
                                }

                                ayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrAY);
                                spinnerAY.setAdapter(ayAdapter);

                                bindRecyclerView();

                            }else{

                                String[] myarr = {"NO COURSE(S)"};
                                Toast.makeText(getApplicationContext(), "No course(s) found.", Toast.LENGTH_SHORT).show();
                                ayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrAY);
                                spinnerAY.setAdapter(ayAdapter);
                            }

                        } catch (JSONException e) {
                            Log.d("err_category", e.getMessage());
                        }
                    }

                }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(getApplicationContext(),"No response from the server. Time out Error.",Toast.LENGTH_SHORT).show();
                    } else if (error instanceof AuthFailureError) {
                        //TODO
                    } else if (error instanceof ServerError) {
                        Toast.makeText(getApplicationContext(),"No response from the server. Server error.",Toast.LENGTH_SHORT).show();
                    } else if (error instanceof NetworkError) {
                        //TODO
                        Toast.makeText(getApplicationContext(),"No response from the server. Network error.",Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ParseError) {
                        //TODO
                    }
                }
            });

            queue.add(jsonObjectRequest);

        }catch (Exception e){
            Log.d("error", e.getMessage());
        }
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ay = spinnerAY.getSelectedItem().toString();
        listQuizzes.clear();
        recyclerView.setAdapter(null);
        LoadQuizes();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
