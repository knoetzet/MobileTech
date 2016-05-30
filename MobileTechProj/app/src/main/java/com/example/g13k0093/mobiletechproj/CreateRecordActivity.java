package com.example.g13k0093.mobiletechproj;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;


public class CreateRecordActivity extends AppCompatActivity {

    static final int GET_GALLERY_PIC = 1;
    static final int PERMISSIONS_READ_EXTERNAL_STORAGE = 2;
    int id;
    int thumbnail;
    dbHelper db;
    Cursor cursor;
    int thumnailnum;
    ImageView img1;
    ImageView img2;
    ImageView img3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_record);
        db = new dbHelper(this);
        thumbnail = getIntent().getIntExtra("thumb",0);
        id = getIntent().getIntExtra("ID",0);
        cursor = db.getRecord(id);
<<<<<<< HEAD
=======


        String photo1uri = cursor.getString(cursor.getColumnIndex(dbHelper.PHOTO));
>>>>>>> more


<<<<<<< HEAD
        //    String photo1uri = cursor.getString(cursor.getColumnIndex(dbHelper.PHOTO));

        img1 = (ImageView)findViewById(R.id.imageView);

        Toast.makeText(getApplicationContext(),photo1uri,Toast.LENGTH_SHORT).show();
      /*  if(photo1uri != null){
=======
        if(photo1uri != null){
>>>>>>> more
            File imgFile = new File(photo1uri);

            if (imgFile.exists()) {
                Bitmap bmImg = BitmapFactory.decodeFile(photo1uri);
                Bitmap bitmap = Bitmap.createScaledBitmap(bmImg, 300, 300, true);
                img1.setImageBitmap(bitmap);
            }
        }else{
            //get uri from gallery
        }*/


    }


    public void open(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Choose Image");

        alertDialogBuilder.setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                if(Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_READ_EXTERNAL_STORAGE);
                        return;
                    }
                    else{
                        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                        startActivityForResult(galleryIntent,GET_GALLERY_PIC);
                    }
            }
        });

        alertDialogBuilder.setNeutralButton("Camera",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent cam = new Intent(getBaseContext(), CameraActivity.class);
                cam.putExtra("ID", id);
                cam.putExtra("thumb",thumnailnum);
                startActivity(cam);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


    public void ImageClick1(View view){
        thumnailnum = 0;
       open(view);
        thumbnail =0;
    }
    public void ImageClick2(View view){
        thumnailnum = 1;
        open(view);
    }
    public void ImageClick3(View view){
        thumnailnum = 2;
        open(view);
    }



    public void onAddInfo(View view){
        Intent addInfo = new Intent(getApplicationContext(),EditRecordActivity.class);
        addInfo.putExtra("ID",id);
        addInfo.putExtra("thumbnail",thumbnail);
        startActivity(addInfo);
    }
}
