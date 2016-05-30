package com.example.g13k0093.mobiletechproj;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CapturedActivity extends AppCompatActivity  {

    public dbHelper dbhelper;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captured);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.record_list:
                Intent rec = new Intent(this, RecordsActivity.class);
                startActivity(rec);
                return true;
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;
            case R.id.action_about:
                // User chose the "About" item, show the app about...
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Buttons

    public void onDbClick(View view){


        dbHelper helper = new dbHelper(this.getApplicationContext());
       // helper.insert(1,"title1","photo1","project1","status1","details1");
        Toast.makeText(this.getApplicationContext(),"FUCK",Toast.LENGTH_LONG).show();


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
