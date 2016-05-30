package com.example.g13k0093.mobiletechproj.EditFragments;

/**
 * Created by G13K0093 on 2016-05-29.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.g13k0093.mobiletechproj.R;

public class EmptyFragment extends Fragment{
    public static EmptyFragment newInstance() {
        return new EmptyFragment();
    }

    public EmptyFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_empty,container,false);
    }
}
