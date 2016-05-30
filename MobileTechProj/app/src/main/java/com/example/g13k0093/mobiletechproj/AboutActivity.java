package com.example.g13k0093.mobiletechproj;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        dbHelper db =  new dbHelper(this);
        SQLiteDatabase ddbb = db.getWritableDatabase();
        db.onUpgrade(ddbb, 1,2);
    }
}
