package com.example.alab.lequizapp.myquizzes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.alab.lequizapp.GlobalClass;
import com.example.alab.lequizapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyQuizzesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    GlobalClass g;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    List<MyQuizzes> quizList = new ArrayList<MyQuizzes>();


    String course = "";

    Spinner spinnerCategories;


    ArrayAdapter<String> catAdapter;

    ProgressBar progressSpinner;
    //this is for loading GUI

    List<String> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_quizzes);

        spinnerCategories = findViewById(R.id.spinnerCategory);
        spinnerCategories.setOnItemSelectedListener(this);


        g = (GlobalClass) getApplicationContext();
        recyclerView = findViewById(R.id.recycleView_myquizzes);

        progressSpinner = findViewById(R.id.progressBar);
        progressSpinner.setVisibility(View.GONE);
        //set progress SPINNER to hide progress spinner
    }


    @Override
    protected void onResume() {
        super.onResume();
        getCategories();
        //LoadData();
    }




    void bindRecyclerView(){
        MyQuizzesAdapter adapter = new MyQuizzesAdapter(quizList);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


    private void LoadData(){
        quizList.clear();
        progressSpinner.setVisibility(View.VISIBLE);
        //show progress spinner

        try{
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = g.getIPAddress() + "/android/quiz/my-quizzes/?userid=" + g.getId()+ "&course=" + course;


            StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("myquizzes", response);

                        progressSpinner.setVisibility(View.GONE);
                        //hide spinner progress if response received

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
                                        obj.getString("category"),
                                        obj.getInt("quiz_id"),
                                        obj.getInt("total_score"),
                                        obj.getString("created_at"),
                                        obj.getInt("total_points")));
                                }
                                bindRecyclerView();

                            }else{
                                Toast.makeText(getBaseContext(), "No quizzes found.", Toast.LENGTH_SHORT).show();
                                recyclerView.setLayoutManager(null);
                            }

                        } catch (JSONException e) {
                            Log.d("err", e.getMessage());
                        }
                    }

                }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Log.d("err", error.getMessage());
                }
            });

            queue.add(jsonObjectRequest);

        }catch (Exception e){
            Log.d("error", e.getMessage());
        }
    }


    private void getCategories(){
        ///COURSES and NEW NAME Aning categories
        try{
            RequestQueue queue = Volley.newRequestQueue(this);
            //String url = g.getIPAddress() + "/android/category/" + g.getId();
            String url = g.getIPAddress() + "/android/myquizzes-category/" + g.getId();

            StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            JSONObject obj;

                            arr = new ArrayList<String>();

                            if(jsonArray.length() > 0){
                                for(int i=0;i < jsonArray.length(); i++){

                                    obj  = jsonArray.getJSONObject(i);
                                    arr.add(obj.getString("course"));
                                }

                                catAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arr);
                                spinnerCategories.setAdapter(catAdapter);

                                bindRecyclerView();

                            }else{

                                String[] myarr = {"NO COURSE(S)"};
                                Toast.makeText(getApplicationContext(), "No course(s) found.", Toast.LENGTH_SHORT).show();
                                catAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, myarr);
                                spinnerCategories.setAdapter(catAdapter);
                            }

                        } catch (JSONException e) {
                            Log.d("err_category", e.getMessage());
                        }
                    }

                }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Log.d("err", error.getMessage());
                }
            });

            queue.add(jsonObjectRequest);

        }catch (Exception e){
            Log.d("error", e.getMessage());
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(getApplicationContext(), spinnerCategories.getCount(), Toast.LENGTH_SHORT).show();

        course = spinnerCategories.getSelectedItem().toString();
        LoadData();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
