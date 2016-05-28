package com.example.g13k0093.mobiletechproj;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecordsActivity extends AppCompatActivity {

    ListView lv;
    RecordListCursorAdapter recordAdapter;
    dbHelper dbhelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        lv = (ListView) findViewById(R.id.listView);
        dbhelper = new dbHelper(this.getApplicationContext());
        cursor = dbhelper.getAllRecords();
        //recordAdapter = new RecordListCursorAdapter(this.getApplicationContext(),cursor,0);
       // lv.setAdapter(recordAdapter);
    }

}
