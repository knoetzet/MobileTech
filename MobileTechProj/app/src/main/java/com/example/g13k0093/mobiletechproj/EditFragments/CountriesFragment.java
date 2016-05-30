package com.example.g13k0093.mobiletechproj.EditFragments;

/**
 * Created by G13K0093 on 2016-05-28.
 */
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.g13k0093.mobiletechproj.R;

public class CountriesFragment extends Fragment implements View.OnClickListener{

    public interface OnButtonClickedListener {
        public void onButtonClicked(String text);
    }
    OnButtonClickedListener mCallback;

    Spinner countries;

    public static CountriesFragment newInstance() {
        return new CountriesFragment();
    }

    public CountriesFragment(){}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnButtonClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnButtonClickedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_country,container,false);

        countries = (Spinner) view.findViewById(R.id.spinner3);
        Button button = (Button) view.findViewById(R.id.button7);
        button.setOnClickListener(this);

        ArrayAdapter<CharSequence> countriesadapter = ArrayAdapter.createFromResource(getActivity(),R.array.countries_array,R.layout.support_simple_spinner_dropdown_item);
        countries.setAdapter(countriesadapter);

        return view;
    }
    public void onClick(View view) {
        // Send the event to the host activity
        mCallback.onButtonClicked(countries.getSelectedItem().toString());
    }
}
