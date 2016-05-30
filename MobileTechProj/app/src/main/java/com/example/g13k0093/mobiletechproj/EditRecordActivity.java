package com.example.g13k0093.mobiletechproj;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.g13k0093.mobiletechproj.EditFragments.CountriesFragment;
import com.example.g13k0093.mobiletechproj.EditFragments.EmptyFragment;
import com.example.g13k0093.mobiletechproj.EditFragments.FlowersFruitScentFragment;
import com.example.g13k0093.mobiletechproj.EditFragments.GrowthFormFragment;
import com.example.g13k0093.mobiletechproj.EditFragments.NaturalCultivatedFragment;
import com.example.g13k0093.mobiletechproj.EditFragments.NumberOfIndividualsFragment;
import com.example.g13k0093.mobiletechproj.EditFragments.ProvinceFragment;
import com.example.g13k0093.mobiletechproj.EditFragments.TextBoxFragment;

public class EditRecordActivity extends AppCompatActivity implements TextBoxFragment.OnButtonClickedListener{

    dbHelper db;
    int id;



    CountriesFragment country = CountriesFragment.newInstance();
    FlowersFruitScentFragment flowers = FlowersFruitScentFragment.newInstance();
    GrowthFormFragment growth = GrowthFormFragment.newInstance();
    NaturalCultivatedFragment natural = NaturalCultivatedFragment.newInstance();
    NumberOfIndividualsFragment number = NumberOfIndividualsFragment.newInstance();
    ProvinceFragment province = ProvinceFragment.newInstance();
    EmptyFragment empty = EmptyFragment.newInstance();
    TextBoxFragment textBoxTown;
    TextBoxFragment textBoxLocal = TextBoxFragment.newInstance();
    TextBoxFragment textBoxEnviro = TextBoxFragment.newInstance();
    TextBoxFragment textBoxId = TextBoxFragment.newInstance();
    TextBoxFragment textBoxNotes = TextBoxFragment.newInstance();
    TextBoxFragment textBoxNestCount = TextBoxFragment.newInstance();
    TextBoxFragment textBoxNestSite = TextBoxFragment.newInstance();


    Spinner projectList;
    Spinner optionalList;
    EditText title;

    public int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        db = new dbHelper(this);

        id = getIntent().getIntExtra("ID",1);



        projectList = (Spinner) findViewById(R.id.spinner);
        optionalList = (Spinner) findViewById(R.id.spinner2);
        title = (EditText) findViewById(R.id.editText6);

        Cursor cursor = db.getRecord(id);

        if (cursor.moveToFirst()) {
            String titlefromdb = cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.TITLE));
            title.setText(titlefromdb);
        }

        ArrayAdapter<CharSequence> projectadapter = ArrayAdapter.createFromResource(this,R.array.project_array,R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> optionaladapter = ArrayAdapter.createFromResource(this,R.array.optional_array,R.layout.support_simple_spinner_dropdown_item);

        projectList.setAdapter(projectadapter);
        optionalList.setAdapter(optionaladapter);

        textBoxTown = TextBoxFragment.newInstance();


        optionalList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = optionalList.getSelectedItemPosition();
                switch (pos){
                    case 0:
                        if(getFragmentManager().findFragmentById(R.id.fragment_container) != null)
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,empty).commit();
                        else getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,empty).commit();
                        break;
                     case 1:
                         //country
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,country).commit();
                         break;
                    case 2:
                        //province
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,province).commit();
                        break;
                    case 3:
                        //closest town
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,textBoxTown).commit();
                        break;
                    case 4:
                        //locality
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,textBoxLocal).commit();
                        break;
                    case 5:
                        //environmental
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,textBoxEnviro).commit();
                        break;
                    case 6:
                        //specimen ID
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,textBoxId).commit();

                        break;
                    case 7:
                        //notes
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,textBoxNotes).commit();
                        break;
                    case 8:
                        //flowers
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,flowers).commit();
                        break;
                    case 9:
                        //no. individuals
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,number).commit();
                        break;
                    case 10:
                        //natural/cultivated
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,natural).commit();
                        break;
                    case 11:
                        //growth form
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,growth).commit();
                        break;
                    case 12:
                        //nest count
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,textBoxNestCount).commit();
                        break;
                    case 13:
                        //nest site
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,textBoxNestSite).commit();
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){

            }
        });
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

    public void OnButtonClicked(String text){
        switch (pos){
            case 0:
                break;
            case 3:
                //closest town
                db.updateOne(id,dbHelper.NEARESTTOWN,text);
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                break;
            case 4:
                //locality
                db.updateOne(id,dbHelper.LOCALITY,text);
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                break;
            case 5:
                //environmental
                Toast.makeText(getApplicationContext(),"No column to update",Toast.LENGTH_SHORT).show();
                break;
            case 6:
                //specimen ID
                db.updateOne(id,dbHelper.SPECIES_ID,text);
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                break;
            case 7:
                //notes
                db.updateOne(id,dbHelper.NOTE,text);
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                break;

            case 12:
                //nest count
                db.updateOne(id,dbHelper.NESTCOUNT,text);
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                break;
            case 13:
                //nest site
                db.updateOne(id,dbHelper.NESTSITE,text);
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                break;
        }

    }

    public  void onMapClicked(View view){
        Intent mapIntent = new Intent(this.getApplicationContext(),MapsActivity.class);
        mapIntent.putExtra("ID",id);
        startActivity(mapIntent);
    }
    public void onSaveClicked(View view){
        db.updateOne(id,dbHelper.TITLE,title.getText().toString());
        Intent recordIntent = new Intent(this.getApplicationContext(),CreateRecordActivity.class);
        recordIntent.putExtra("ID",id);
        startActivity(recordIntent);
    }


}
