package com.example.g13k0093.mobiletechproj.EditFragments;

import android.support.v4.app.Fragment;

/**
 * Created by G13K0093 on 2016-05-28.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.g13k0093.mobiletechproj.R;

public class NumberOfIndividualsFragment extends Fragment {
    public static NumberOfIndividualsFragment newInstance() {
        return new NumberOfIndividualsFragment();
    }

    public NumberOfIndividualsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_individuals,container,false);
    }
}

