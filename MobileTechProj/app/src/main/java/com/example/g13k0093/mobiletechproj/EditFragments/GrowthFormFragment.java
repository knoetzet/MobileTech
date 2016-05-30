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
import android.widget.Button;

import com.example.g13k0093.mobiletechproj.R;

public class GrowthFormFragment extends Fragment /*implements View.OnClickListener*/{

    // incomplete add spinner list!

    public static GrowthFormFragment newInstance() {
        return new GrowthFormFragment();
    }

   /* public interface OnButtonClickedListener {
        public void onButtonClicked(String text);
    }
    OnButtonClickedListener mCallback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnButtonClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnButtonClickedListener");
        }
    }*/

    public GrowthFormFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_growth,container,false);
       /* Button clicked = (Button) view.findViewById(R.id.button4);
        clicked.setOnClickListener(this);*/
        return view;
    }

   /* @Override
    public void onClick(View v) {

    }*/
}
