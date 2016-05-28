package com.example.g13k0093.mobiletechproj;

import android.content.Intent;
import android.database.Cursor;
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
    TextView display;
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
       // Toast.makeText(this.getApplicationContext(),"FUCK",Toast.LENGTH_LONG).show();

       dbHelper helper = new dbHelper(this.getApplicationContext());
        //helper.deleteRecord(1);
        //helper.insert(1,"titty","phot","proj","tails");
        TextView display = (TextView) findViewById(R.id.textView5);
      //  Toast.makeText(this.getApplicationContext(),"FUCK",Toast.LENGTH_LONG).show();
        Cursor cursor  = helper.getRecord(1);
        cursor.moveToFirst();
       display.setText(cursor.getString(cursor.getColumnIndex(helper.TITLE)));


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
