package com.example.g13k0093.mobiletechproj;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class EditRecordActivity extends AppCompatActivity {

    CountriesFragment country = CountriesFragment.newInstance();
    FlowersFruitScentFragment flowers = FlowersFruitScentFragment.newInstance();
    GrowthFormFragment growth = GrowthFormFragment.newInstance();
    NaturalCultivatedFragment natural = NaturalCultivatedFragment.newInstance();
    NumberOfIndividualsFragment number = NumberOfIndividualsFragment.newInstance();
    ProvinceFragment province = ProvinceFragment.newInstance();
    EmptyFragment empty = EmptyFragment.newInstance();
    TextBoxFragment textBox;
    Button button;

    View view;
    Spinner projectList;
    Spinner optionalList;
    int pos;
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
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,country).commit();
                         break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,province).commit();
                        break;
                    case 3:
                        textBox = TextBoxFragment.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,textBox).commit();
                        break;
                    case 4:
                        textBox = TextBoxFragment.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,textBox).commit();
                        break;
                    case 5:
                        textBox = TextBoxFragment.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,textBox).commit();
                        break;
                    case 6:
                        textBox = TextBoxFragment.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,textBox).commit();

                        break;
                    case 7:
                        textBox = TextBoxFragment.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,textBox).commit();
                        break;
                    case 8:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,flowers).commit();
                        break;
                    case 9:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,number).commit();
                        break;
                    case 10:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,natural).commit();
                        break;
                    case 11:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,growth).commit();
                        break;
                    case 12:
                        textBox = TextBoxFragment.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,textBox).commit();
                        break;
                    case 13:
                        textBox = TextBoxFragment.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,textBox).commit();
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){

            }
        });
    }


}
