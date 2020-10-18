package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Result extends AppCompatActivity implements ResultAdapter.ListItemClickListener {

    TextView lblTxt1;
    EditText txtbox1;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<StudentAnswer> myList;

    int totalScore=0;

    TextView txtresult_totalScore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        recyclerView = (RecyclerView) findViewById(R.id.result_recycler);
        txtresult_totalScore = (TextView) findViewById(R.id.result_txtvwTotalScore);



        try{
            Intent intent = getIntent();
            myList = intent.getParcelableArrayListExtra("myList");
            totalScore = intent.getIntExtra("totalSCore", totalScore);
            txtresult_totalScore.setText("SCORE: " + String.valueOf(totalScore));


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
        //finish();
    }


    public void btnBackLobby(View v){
        getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();

        Intent i = new Intent(this, StudentMainActivity.class);
        // set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

}
