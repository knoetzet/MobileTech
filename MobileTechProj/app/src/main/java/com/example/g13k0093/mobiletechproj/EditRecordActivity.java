package com.example.g13k0093.mobiletechproj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EditRecordActivity extends AppCompatActivity {

    Spinner projectList;
    Spinner optionalList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);
        projectList = (Spinner) findViewById(R.id.spinner);
        optionalList = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> projectadapter = ArrayAdapter.createFromResource(this,R.array.project_array,R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> optionaladapter = ArrayAdapter.createFromResource(this,R.array.optional_array,R.layout.support_simple_spinner_dropdown_item);
        projectList.setAdapter(projectadapter);
        optionalList.setAdapter(optionaladapter);
    }
}
