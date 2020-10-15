package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Result extends AppCompatActivity implements ResultAdapter.ListItemClickListener {

    TextView lblTxt1;
    EditText txtbox1;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<StudentAnswer> myList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        recyclerView = (RecyclerView) findViewById(R.id.result_recycler);


        try{
            Intent intent = getIntent();
            myList = intent.getParcelableArrayListExtra("myList");



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
        Toast.makeText(this, myList.get(position).getQuestion(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}
