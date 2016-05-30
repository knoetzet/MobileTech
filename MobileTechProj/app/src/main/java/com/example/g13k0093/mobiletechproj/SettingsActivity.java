package com.example.g13k0093.mobiletechproj;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void translateToEnglish(View view){
        translateTo("en");
    }
    public void translateToAfrikaans(View view){
        translateTo("af");
    }

    public void backToRecords(View view){
        Intent back = new Intent(this,RecordsActivity.class);
        startActivity(back);
    }

    public void translateTo(String str) {
        Locale locale = new Locale (str);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getApplicationContext().getResources().updateConfiguration(config,
                getApplicationContext().getResources().getDisplayMetrics());
        recreate();
    }
}