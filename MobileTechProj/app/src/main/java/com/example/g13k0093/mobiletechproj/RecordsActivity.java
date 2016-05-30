package com.example.g13k0093.mobiletechproj;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RecordsActivity extends AppCompatActivity {

    ListView lv;
    RecordListCursorAdapter recordAdapter;
    SimpleCursorAdapter dataAdapter;
    dbHelper dbhelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        dbhelper = new dbHelper(this.getApplicationContext());
        cursor = dbhelper.getAllRecords();
        recordAdapter = new RecordListCursorAdapter(this, cursor);
        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(recordAdapter);

      //  dbHelper db = new dbHelper(this);
      //  db.insert("jl","hi");


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent editIntent = new Intent(getApplicationContext(), CreateRecordActivity.class);
                editIntent.putExtra("ID",position+1);
                startActivity(editIntent);
            }
        });


        //displayList();
        //recordAdapter = new RecordListCursorAdapter(this.getApplicationContext(),cursor);
        // lv.setAdapter(recordAdapter);
    }

    public void newRecord(View view){

        int id = dbhelper.getRecordsCount() + 1;
        dbhelper.insert(id,"title" + id,"required fields missing");
        Intent createIntent = new Intent(getApplicationContext(), CreateRecordActivity.class);
        createIntent.putExtra("ID",id);
        startActivity(createIntent);
    }





}
