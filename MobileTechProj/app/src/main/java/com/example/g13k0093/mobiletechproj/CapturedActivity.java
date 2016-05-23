package com.example.g13k0093.mobiletechproj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CapturedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captured);
    }

    public void onRetakeClick(View view){
        Intent retake = new Intent(this, CameraActivity.class);
        startActivity(retake);
    }
    public void onNextPictureClick(View view){
        Intent next = new Intent(this, CameraActivity.class);
        startActivity(next);
    }
    public void onNowClick(View view){

        Intent now = new Intent(this, EditRecordActivity.class);
        startActivity(now);
    }
    public void onLaterClick(View view){
        Intent later = new Intent(this, RecordsActivity.class);
        startActivity(later);
    }




}
