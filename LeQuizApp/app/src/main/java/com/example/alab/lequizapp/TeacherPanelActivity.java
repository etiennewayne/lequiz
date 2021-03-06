package com.example.alab.lequizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.GoalRow;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TeacherPanelActivity extends AppCompatActivity {

    String user, position;
    GlobalClass g;


    TextView txtWelcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_panel);

        txtWelcome = (TextView) findViewById(R.id.txtvw_menu_welcome);

        Intent intent = getIntent();

        g = (GlobalClass) getApplicationContext();

        String lname, fname, mname;


        lname = ((g.getLname() != null) ? g.getLname() : "");
        fname = ((g.getFname() != null) ? g.getFname() : "");
        mname = ((g.getMname() != null) ? g.getMname() : "");


        String fullname = lname + ", " + fname + " " + mname;

        user = intent.getStringExtra("user");
        position = intent.getStringExtra("position");

        txtWelcome.setText("WELCOME : " + fullname);


        g.setUsername(user);
        g.setPosition(position);
        //TextView txtvw = findViewById(R.id.textView);
        // txtvw.setText(user);
    }

    @Override
    public void onBackPressed(){

        AlertDialog.Builder alert = new AlertDialog.Builder(TeacherPanelActivity.this);
        alert.setTitle("LOGOUT.");
        alert.setMessage("You will be logout in the Application. Are you sure you want to logout?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

//                if(webSocket != null)
//                    webSocket.close(1000, "EXIT!");

                dialog.dismiss();
                TeacherPanelActivity.this.finish();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();

    }


    public void clickQuizes(View view) {
        Intent intent = new Intent(getApplicationContext(), QuizesActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);

        intent.putExtra("position", position);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void clickCategory(View v) {
        Intent intent = new Intent(getApplicationContext(), CategoryMain.class);
        startActivity(intent);
    }

    public void clickStudentQuiz(View v) {
        Intent intent = new Intent(getApplicationContext(), RoomMain.class);
        startActivity(intent);
    }


}
