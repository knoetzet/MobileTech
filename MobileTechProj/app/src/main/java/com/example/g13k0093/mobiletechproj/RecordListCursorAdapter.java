package com.example.g13k0093.mobiletechproj;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

/**
 * Created by G13K0093 on 2016-05-26.
 */
public class RecordListCursorAdapter extends CursorAdapter{
    public RecordListCursorAdapter (Context context, Cursor cursor){
        super(context,cursor,0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.record_layout,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tvTitle = (TextView) view.findViewById(R.id.recordTitle);
        TextView tvStatus = (TextView) view.findViewById(R.id.recordStatus);
        ImageView imageView = (ImageView) view.findViewById(R.id.rowImage);


        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));

        if (cursor.getString(cursor.getColumnIndex(dbHelper.PHOTO1)) != null) {
            imageView.setRotation(90);
            String photo1uri = cursor.getString(cursor.getColumnIndex(dbHelper.PHOTO1));
            File imgFile = new File(photo1uri);
            if (imgFile.exists()) {
                Bitmap bmImg = BitmapFactory.decodeFile(photo1uri);
                Bitmap bitmap = Bitmap.createScaledBitmap(bmImg, 300, 300, true);
                imageView.setImageBitmap(bitmap);
            }
        }
        tvTitle.setText(title);
        tvStatus.setText(status);
    }

}
