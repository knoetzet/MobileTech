package com.example.g13k0093.mobiletechproj;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    String regUrl = "http://vmus.adu.org.za/dt_registration.php";
    Intent reg = new Intent(Intent.ACTION_VIEW, Uri.parse(regUrl));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onRegClick(View view){
        startActivity(reg);
    }
    



    public void onLoginClick(View view){
    // need to check register validity
        Intent log = new Intent(this, CameraActivity.class);
        startActivity(log);
    }
}