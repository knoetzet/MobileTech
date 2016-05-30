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
import android.widget.CheckBox;

import com.example.g13k0093.mobiletechproj.R;


public class FlowersFruitScentFragment extends Fragment implements View.OnClickListener {

    public interface OnButtonClickedListener {
        public void onButtonClicked(String text);
    }

    OnButtonClickedListener mCallback;
    CheckBox flowers;
    CheckBox fruit;
    CheckBox scent;

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

    public static FlowersFruitScentFragment newInstance() {
        return new FlowersFruitScentFragment();
    }

    public FlowersFruitScentFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_flowers,container,false);
        Button button = (Button) view.findViewById(R.id.button7);
        button.setOnClickListener(this);

        flowers = (CheckBox) view.findViewById(R.id.checkBox);
        fruit = (CheckBox) view.findViewById(R.id.checkBox2);
        scent = (CheckBox) view.findViewById(R.id.checkBox3);

        return view;
    }

    public void onClick(View view) {
        // Send the event to the host activity
        String text = "";
        if(flowers.isChecked()) text += "flowers present;";
        if(fruit.isChecked()) text += "fruit present;";
        if(scent.isChecked()) text += "scent present;";

        mCallback.onButtonClicked(text);
    }
}
