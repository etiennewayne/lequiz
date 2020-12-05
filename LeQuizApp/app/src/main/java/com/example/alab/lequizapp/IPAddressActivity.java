package com.example.alab.lequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class IPAddressActivity extends AppCompatActivity {


    GlobalClass g;

    EditText txtIPAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_p_address);

        g = (GlobalClass) getApplicationContext();

        txtIPAddress = findViewById(R.id.txtIPAddress);

        if(g.getIPAddress() != null){
            txtIPAddress.setText(g.getIPAddress());
        }else{
            txtIPAddress.setText("192.168.0.10");
        }


    }

    public void clickSaveIPAddress(View V){

        g.setIPAddress(txtIPAddress.getText().toString());
        finish();
    }


}
