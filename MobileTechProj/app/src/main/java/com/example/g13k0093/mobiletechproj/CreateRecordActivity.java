package com.example.g13k0093.mobiletechproj;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;


public class CreateRecordActivity extends AppCompatActivity {

    static final int GET_GALLERY_PIC = 1;
    static final int PERMISSIONS_READ_EXTERNAL_STORAGE = 2;
    int id;
    int thumbnail;
    dbHelper db;
    Cursor cursor;
    ImageView img1;
    ImageView img2;
    ImageView img3;
    TextView title;
    ListView listView;
    String photo1uri;
    File mediaFile;
    String fromGallery;
    ArrayAdapter adapter;

   String [] dBfields = new String[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_record);

        db = new dbHelper(this.getApplicationContext());
        fromGallery = null;
        thumbnail = getIntent().getIntExtra("thumbnail", -1);
        id = getIntent().getIntExtra("ID", 0);
        cursor = db.getRecord(id);

        img1 = (ImageView) findViewById(R.id.imageView);
        img1.setRotation(90);
        img2 = (ImageView) findViewById(R.id.imageView2);
        img2.setRotation(90);
        img3 = (ImageView) findViewById(R.id.imageView3);
        img3.setRotation(90);

        title = (TextView) findViewById(R.id.textView5);

        populateFields(cursor,dBfields);

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,dBfields);
        listView = (ListView) findViewById(R.id.listView2);
        listView.setAdapter(adapter);

            if (cursor.getString(cursor.getColumnIndex(dbHelper.PHOTO1)) != null) {
                String photo1uri = cursor.getString(cursor.getColumnIndex(dbHelper.PHOTO1));
                getImage1(photo1uri);
            }
       if(cursor.getString(cursor.getColumnIndex(dbHelper.PHOTO2)) != null) {
            String photo1uri = cursor.getString(cursor.getColumnIndex(dbHelper.PHOTO2));
            getImage2(photo1uri);
        }
        if(cursor.getString(cursor.getColumnIndex(dbHelper.PHOTO3)) != null) {
            String photo1uri = cursor.getString(cursor.getColumnIndex(dbHelper.PHOTO3));
            getImage3(photo1uri);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.record_list:
                Intent rec = new Intent(this, RecordsActivity.class);
                startActivity(rec);
                return true;
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;
            case R.id.action_about:
                // User chose the "About" item, show the app about...
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getImage1(String photo1uri)
    {
        File imgFile = new File(photo1uri);

        if (imgFile.exists()) {
            Bitmap bmImg = BitmapFactory.decodeFile(photo1uri);
            Bitmap bitmap = Bitmap.createScaledBitmap(bmImg, 300, 300, true);
            img1.setImageBitmap(bitmap);
        } else {
            //get uri from gallery
        }

    }
    public void getImage2(String photo1uri)
    {
        File imgFile = new File(photo1uri);

        if (imgFile.exists()) {
            Bitmap bmImg = BitmapFactory.decodeFile(photo1uri);
            Bitmap bitmap = Bitmap.createScaledBitmap(bmImg, 300, 300, true);
            img2.setImageBitmap(bitmap);
        } else {
            //get uri from gallery
        }

    }
    public void getImage3(String photo1uri)
    {
        File imgFile = new File(photo1uri);

        if (imgFile.exists()) {
            Bitmap bmImg = BitmapFactory.decodeFile(photo1uri);
            Bitmap bitmap = Bitmap.createScaledBitmap(bmImg, 300, 300, true);
            img3.setImageBitmap(bitmap);
        } else {
            //get uri from gallery
        }

    }




    public void open(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Choose Image");

        alertDialogBuilder.setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);


            }
        });

        alertDialogBuilder.setNegativeButton("Camera",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent cam = new Intent(getBaseContext(), CameraActivity.class);
                cam.putExtra("ID", id);
                cam.putExtra("thumbnail",thumbnail);
                startActivity(cam);
            }
        });
        alertDialogBuilder.setNeutralButton("Delete",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(thumbnail == 0) {
                    db.updateOne(id, "photo1", "null");
                }
                if(thumbnail == 1) {
                    db.updateOne(id, "photo2", "null");
                }
                if(thumbnail == 2) {
                    db.updateOne(id, "photo3", "null");
                }
                Intent cret = new Intent(getBaseContext(), CreateRecordActivity.class);
                cret.putExtra("ID", id);
                startActivity(cret);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            Uri selectedImageUri = data.getData();
            mediaFile =  new File(getRealPathFromURI(selectedImageUri));
            String hope = Uri.fromFile(mediaFile).toString();
            if(hope != null){
                String[] done = hope.split("file://");
                fromGallery = done[1];
                if(thumbnail == 0) {
                    db.updateOne(id, "photo1", fromGallery);
                }
                if(thumbnail == 1) {
                    db.updateOne(id, "photo2", fromGallery);
                }
                if(thumbnail == 2) {
                    db.updateOne(id, "photo3", fromGallery);
                }
                Intent cret = new Intent(getBaseContext(), CreateRecordActivity.class);
                cret.putExtra("ID", id);
                startActivity(cret);
            }
        }
    }
    private String getRealPathFromURI(Uri contentURI) {         // from stack overflow I take no credit "cesards"
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public void ImageClick1(View view){
        thumbnail = 0;
       open(view);
    }
    public void ImageClick2(View view){
        thumbnail = 1;
        open(view);
    }
    public void ImageClick3(View view){
        thumbnail = 2;
        open(view);
    }

    public void deleteRecord(View view){

        db.deleteRecord(id);
        Intent backIntent = new Intent(getApplicationContext(), RecordsActivity.class);
        backIntent.putExtra("ID",id);
        startActivity(backIntent);
    }





    public void onAddInfo(View view){
        Intent addInfo = new Intent(getApplicationContext(),EditRecordActivity.class);
        addInfo.putExtra("ID",id);
        addInfo.putExtra("thumbnail",thumbnail);
        startActivity(addInfo);
    }

    public void onDone(View view){
        Intent recordIntent = new Intent(this.getApplicationContext(),RecordsActivity.class);
        startActivity(recordIntent);
    }

    public void populateFields(Cursor cursor,String[] dBfields){

        dBfields[0] = "PROJECT:";
        dBfields[1] = "DATE:";
        dBfields[2] = "LATITUDE:";
        dBfields[3] = "LONGITUDE:";
        dBfields[4] = "NEAREST TOWN:";
        dBfields[5] = "LOCALITY:";
        dBfields[6] = "SPECIES ID:";
        dBfields[7] = "ADDITIONAL NOTES:";
        dBfields[8] = "NEST COUNT:";
        dBfields[9] = "NEST SITE:";

        if (cursor.moveToFirst()) {
            String titlefromdb = cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.TITLE));
            title.setText(titlefromdb);
            if(cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.PROJECT))!=null)
            {
                dBfields[0] = "PROJECT:  " + cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.PROJECT));
            }
            if(cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.YEAR))!=null)
            {
                dBfields[1] = "DATE:  " + cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.YEAR));
            }
            if(cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.MONTH))!=null)
            {
                dBfields[1] += "-" + cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.MONTH));
            }
            if(cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.DAY))!=null)
            {
                dBfields[1] += "-" + cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.DAY));
            }
            if(cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.LAT))!=null)
            {
                dBfields[2] = "LATITUDE:  " + cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.LAT));
            }
            if(cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.LONG))!=null)
            {
                dBfields[3] = "LONGITUDE:  " + cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.LONG));
            }
            if(cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.NEARESTTOWN))!=null)
            {
                dBfields[4] = "NEAREST TOWN:  " + cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.NEARESTTOWN));
            }
            if(cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.LOCALITY))!=null)
            {
                dBfields[5] = "LOCALITY:  " + cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.LOCALITY));
            }
            if(cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.SPECIES_ID))!=null)
            {
                dBfields[6] = "SPECIES ID:  " + cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.SPECIES_ID));
            }
            if(cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.NOTE))!=null)
            {
                dBfields[7] = "ADDITIONAL NOTES:  " + cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.NOTE));
            }
            if(cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.NESTCOUNT))!=null)
            {
                dBfields[8] = "NEST COUNT:  " + cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.NESTCOUNT));
            }
            if(cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.NESTSITE))!=null)
            {
                dBfields[9] = "NEST SITE:  " + cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.NESTSITE));
            }


        }


    }
}
