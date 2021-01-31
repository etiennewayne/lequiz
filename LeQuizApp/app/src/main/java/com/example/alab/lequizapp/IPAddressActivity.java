package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IPAddressActivity extends AppCompatActivity {


    GlobalClass g;

    EditText txtIPAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_p_address);

        g = (GlobalClass) getApplicationContext();

        txtIPAddress = findViewById(R.id.txtIPAddress);

        txtIPAddress.setText(g.getIPAddressShow());
        //naa nana siay HTTP://
        //kai nag return concat protocol na sia.. protocol + IPAddress
    }

    public void clickSaveIPAddress(View V){

        if(txtIPAddress.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Please input IP Address", Toast.LENGTH_SHORT).show();
        }else{
            g.setIPAddress(txtIPAddress.getText().toString());
            finish();
        }
    }


}
