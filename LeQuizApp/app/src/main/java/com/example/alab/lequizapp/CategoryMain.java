package com.example.alab.lequizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class CategoryMain extends AppCompatActivity {



    GlobalClass g;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    List<Category> categoryList = new ArrayList<Category>();


    ProgressBar progressSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_main);

        g = (GlobalClass) getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recycleView_category);

        progressSpinner = findViewById(R.id.courses_spinnerprogress);
        progressSpinner.setVisibility(View.GONE);

    }


    public void clickNewUpdate(View v){
        Intent intent = new Intent(this, CategoryAddUpdate.class);
        intent.putExtra("id", 0);
        startActivity(intent);
    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        categoryList.clear();
        getCategories();
    }

    void bindRecyclerView(){
        CategoryRecyclerAdapter adapter = new CategoryRecyclerAdapter(categoryList, new CategoryRecyclerAdapter.ButtonsClickListener() {
            @Override
            public void editClick(View v, int position) {
                int id = categoryList.get(position).getCategoryId();
                Intent intent = new Intent(getApplicationContext(), CategoryAddUpdate.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }

            @Override
            public void deleteClick(View v, int position) {

                int itemId = categoryList.get(position).getCategoryId();
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



    private void getCategories(){
        progressSpinner.setVisibility(View.VISIBLE);

        try{
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = g.getIPAddress() + "/android/category/" + g.getId();

            StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressSpinner.setVisibility(View.GONE);

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            JSONObject obj;

                            if(jsonArray.length() > 0){
                                for(int i=0;i < jsonArray.length(); i++){
                                    obj  = jsonArray.getJSONObject(i);
                                    Log.d("category", obj.getString("category"));
                                    categoryList.add(new Category(obj.getInt("category_id"),
                                        obj.getInt("user_id"),
                                        obj.getInt("academic_year_id"),
                                        obj.getString("ay_code"),
                                        obj.getString("category"),
                                        obj.getString("category_desc")));
                                }

                                bindRecyclerView();

                            }else{
                                Toast.makeText(getApplicationContext(), "No categories found.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            Log.d("err", e.getMessage());
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



    private void delete(final int itemId){
        String url = g.getIPAddress()+"/android/category/delete";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Log.d("response", response);
                    try {

                        JSONObject obj = new JSONObject(response);

                        if(obj.has("status")){
                            if(obj.getString("status").equalsIgnoreCase("deleted")){

                                Toast.makeText(getApplicationContext(), "Successfully deleted. ", Toast.LENGTH_SHORT).show();
                                categoryList.clear();
                                getCategories();
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
                params.put("category_id", String.valueOf(itemId));
                return params;
            }
        };
        queue.add(stringRequest);
    }


}
