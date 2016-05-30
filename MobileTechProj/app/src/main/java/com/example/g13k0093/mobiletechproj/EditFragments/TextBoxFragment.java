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
import android.widget.EditText;

import com.example.g13k0093.mobiletechproj.R;



public class TextBoxFragment extends Fragment implements View.OnClickListener {

    public interface OnButtonClickedListener {
        public void onButtonClicked(String text);
    }

    OnButtonClickedListener mCallback;

    public static TextBoxFragment newInstance() {
        return new TextBoxFragment();
    }

    public TextBoxFragment() {
    }

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


    EditText theTextBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_textbox, container, false);

        theTextBox = (EditText) view.findViewById(R.id.editText5);

        Button clicked = (Button) view.findViewById(R.id.button4);
        clicked.setOnClickListener(this);
        return view;
    }


    public void onClick(View view) {
        // Send the event to the host activity
       mCallback.onButtonClicked(theTextBox.getText().toString());
    }
}
