package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class CategoryMain extends AppCompatActivity {

    GlobalClass g;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_main);

        g = (GlobalClass) getApplicationContext();


    }
    public void clickNewUpdate(View v){
        Intent intent = new Intent(this, CategoryAddUpdate.class);
        startActivity(intent);
    }



}
