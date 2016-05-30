package com.example.g13k0093.mobiletechproj;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.entity.mime.content.ContentBody;
import cz.msebera.android.httpclient.entity.mime.content.InputStreamBody;

public class CapturedActivity extends AppCompatActivity  {

    public dbHelper dbhelper;
    Cursor cursor;
    ImageView pic;
    String getfilepath;
    dbHelper db;
    int id;
    int thumbnail;
    String time;
    String longitude;
    String latitude;


    Date date;
    Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captured);
        id = getIntent().getIntExtra("ID",0);
        thumbnail = getIntent().getIntExtra("thumbnail",0);
        time = getIntent().getStringExtra("time");
        latitude = getIntent().getStringExtra("lat");
        longitude = getIntent().getStringExtra("lon");
        db = new dbHelper(this);
        getfilepath = getIntent().getStringExtra("pic");
        if(getfilepath != null){
            String[] done = getfilepath.split("file://");
            getfilepath = done[1];
            getImage();
        }

    }



    public void getImage() {
        File imgFile = new File(getfilepath);

        if (imgFile.exists()) {
            pic = (ImageView) findViewById(R.id.captureimage);
            pic.setRotation(90);

            Bitmap bmImg = BitmapFactory.decodeFile(getfilepath);
            Bitmap bitmap = Bitmap.createScaledBitmap(bmImg, 300, 300, true);
            pic.setImageBitmap(bitmap);
        }
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
                Intent set = new Intent(this, SettingsActivity.class);
                startActivity(set);
                return true;
            case R.id.action_about:
                Intent about = new Intent(this, AboutActivity.class);
                startActivity(about);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Buttons

    public void onRetakeClick(View view){
        Intent retake = new Intent(this, CameraActivity.class);
        retake.putExtra("ID", id);
        retake.putExtra("thumbnail", thumbnail);
        startActivity(retake);
    }

    public void onNowClick(View view){

        if(thumbnail == 0) {
            db.updateOne(id,"photo1",getfilepath);
        }else if(thumbnail == 1) {db.updateOne(id,"photo2",getfilepath);}
        else if(thumbnail == 2){db.updateOne(id,"photo3",getfilepath);}

        if(latitude != null) {
            db.updateOne(id, "lat", latitude);
        }
        if(longitude != null) {
            db.updateOne(id, "long", longitude);
        }

        String year = new SimpleDateFormat("yyyy").format(new Date());
        String month = new SimpleDateFormat("MM").format(new Date());
        String day = new SimpleDateFormat("dd").format(new Date());
        db.updateOne(id, "year", year);
        db.updateOne(id, "month", month);
        db.updateOne(id, "day", day);


        Intent now = new Intent(this, CreateRecordActivity.class);
        now.putExtra("ID", id);
        now.putExtra("thumbnail", thumbnail);
        startActivity(now);
    }
    public void onLaterClick(View view){
      //save to database
        //dbHelper db = new dbHelper(this);
       // db.insert(0,"none",null,null,"good job",null);

        Intent later = new Intent(this, CreateRecordActivity.class);
        startActivity(later);
    }

}
