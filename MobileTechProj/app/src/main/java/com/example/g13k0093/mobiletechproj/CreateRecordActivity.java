package com.example.g13k0093.mobiletechproj;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class CreateRecordActivity extends AppCompatActivity {

    static final int GET_GALLERY_PIC = 1;
    static final int PERMISSIONS_READ_EXTERNAL_STORAGE = 2;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_record);
        id = getIntent().getIntExtra("ID",0);
    }

    public void getImage(){
        final CharSequence[] items = { "Camera", "Library"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        /*builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Camera")) {
                   // Intent cameraIntent = new Intent(getApplicationContext(),CameraActivity.class);
                    Toast.makeText(getApplicationContext(),"ok I'll get right on that",Toast.LENGTH_SHORT);

                }
                else if (items[item].equals("Library")) {
                    Toast.makeText(getApplicationContext(),"ok I'll get right on that",Toast.LENGTH_SHORT);
                   /* if(Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
                        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_READ_EXTERNAL_STORAGE);
                            return;
                        }
                    else{
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent,GET_GALLERY_PIC);
                    }
                }
            }
        });
        builder.show();*/
    }


    public void ImageClick(View view){
        //Toast.makeText(getApplicationContext(),"ok I'll get right on that",Toast.LENGTH_SHORT).show();
        getImage();
    }


    public void onAddInfo(View view){
        Intent addInfo = new Intent(getApplicationContext(),EditRecordActivity.class);
        addInfo.putExtra("ID",id);
        startActivity(addInfo);
    }
}
