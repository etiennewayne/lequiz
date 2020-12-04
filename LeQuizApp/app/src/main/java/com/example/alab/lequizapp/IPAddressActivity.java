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

        txtIPAddress = findViewById(R.id.txtIPAddress);


    }

    public void clickSaveIPAddress(View V){
        g = (GlobalClass) getApplicationContext();
        g.setIPAddress(txtIPAddress.getText().toString());
        finish();
    }


}
