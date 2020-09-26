package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TeacherPanelActivity extends AppCompatActivity {

    String user, position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_panel);
        Intent intent = getIntent();
        user = intent.getStringExtra("user");
        position = intent.getStringExtra("position");

        //TextView txtvw = findViewById(R.id.textView);
       // txtvw.setText(user);
    }


    public void clickQuizes(View view){
        Intent intent = new Intent(getApplicationContext(), QuizesActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);

        intent.putExtra("position", position);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
