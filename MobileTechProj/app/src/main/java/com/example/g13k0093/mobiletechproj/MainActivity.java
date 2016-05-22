package com.example.g13k0093.mobiletechproj;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent login = new Intent(this.getApplicationContext(),LoginActivity.class);
        startActivity(login);
    }


//roarraor

}

