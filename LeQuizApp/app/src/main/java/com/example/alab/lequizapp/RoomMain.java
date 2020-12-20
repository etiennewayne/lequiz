package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

public class RoomMain extends AppCompatActivity {


    GlobalClass g;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    List<Room> roomList = new ArrayList<Room>();

    Spinner spinnerAY;


    String aycode;

    ArrayAdapter<String> ayAdapater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_main);

        g = (GlobalClass) getApplicationContext();


        recyclerView = findViewById(R.id.recycleView_Rooms);
        spinnerAY = findViewById(R.id.spinnerAY);

    }


    void LoadSpinner(){
        spinnerAY.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(getApplicationContext(), spinnerAY.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                aycode = spinnerAY.getSelectedItem().toString();
                roomList.clear();
                LoadData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here

        getAY();

    }

    RoomRecyclerAdapter adapter;

    void bindRecyclerView(){
        adapter = new RoomRecyclerAdapter(roomList, new RoomRecyclerAdapter.ButtonsClickListener() {

            @Override
            public void stdQuizzesClick(View v, int position) {
                Room room = roomList.get(position);
                int room_id = room.getRoomId();

                Intent intent = new Intent(getBaseContext(), StudentQuizzesActivity.class);
                intent.putExtra("room_id", room_id);
                startActivity(intent);
            }
        });

        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    private void LoadData(){
        try{
            roomList.clear();
            recyclerView.setAdapter(adapter);

            RequestQueue queue = Volley.newRequestQueue(this);
            String url = g.getIPAddress() + "/android/student-list/?aycode="+aycode+"&userid="+g.getId();

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

                                    roomList.add(new Room(obj.getInt(""),
                                        obj.getInt("quiz_id"),
                                        obj.getString("access_code"),
                                        obj.getString(""),
                                        obj.getString(""),
                                        obj.getString("course"),
                                        obj.getString("quiz_title"),
                                        obj.getString("quiz_desc")));
                                }

                                bindRecyclerView();

                            }else{
                                Toast.makeText(getApplicationContext(), "No rooms found.", Toast.LENGTH_SHORT).show();
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


    void getAY(){
        String url = g.getIPAddress()+"/android/ay";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject obj = null;
                        List<String> arr = new ArrayList<String>();

                        for(int i = 0; i < jsonArray.length(); i++){
                            obj = jsonArray.getJSONObject(i);
                            arr.add(obj.getString("ay_code"));
                        }


                        ayAdapater = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arr);
                        spinnerAY.setAdapter(ayAdapater);

                        LoadSpinner();

                    } catch (JSONException e) {
                        Log.d("err", "JSON ERROR " + e.getMessage());
                    }

                }


            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("err", "Volley Error"  + error.getMessage());
            }
        });
        queue.add(stringRequest);
    }






}
