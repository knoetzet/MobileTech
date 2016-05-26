package com.example.g13k0093.mobiletechproj;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by G13K0093 on 2016-05-26.
 */
public class RecordListCursorAdapter extends CursorAdapter{
    public RecordListCursorAdapter (Context context, Cursor cursor, int flags){
        super(context,cursor,0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.record_layout,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvTitle = (TextView) view.findViewById(R.id.recordTitle);
        TextView tvStatus = (TextView) view.findViewById(R.id.recordStatus);
        ImageView imageView = (ImageView) view.findViewById(R.id.rowImage);
        // Extract properties from cursor
        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        int status = cursor.getInt(cursor.getColumnIndexOrThrow(""));
        // Populate fields with extracted properties
        tvTitle.setText(title);
        tvStatus.setText(status);
    }

}
