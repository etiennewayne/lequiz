package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class QuizAddUpdate extends AppCompatActivity {


    public int id = 0;


    TextView lblQuizTitle, lblQuizDesc;
    EditText txtQuizTitle, txtQuizDEsc;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_add_update);

        lblQuizTitle = findViewById(R.id.lblquiz_addtitle);
        lblQuizDesc = findViewById(R.id.lblquiz_adddesc);



    }


    void getData(){

    }
}
