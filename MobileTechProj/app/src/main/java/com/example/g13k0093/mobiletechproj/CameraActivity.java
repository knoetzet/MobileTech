package com.example.g13k0093.mobiletechproj;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;
import java.util.List;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback  {

    static Camera camera = null;
    SurfaceView surfaceView;
    SurfaceHolder holder;
    String tag;
    Camera.PictureCallback rawCallback;

    SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);



        surfaceView = (SurfaceView)findViewById(R.id.surfaceView1);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        startcamera();

    }







    private void startcamera(){

        try {
            camera = Camera.open();
            Camera.Parameters parameters = camera.getParameters();
            //parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        }catch(Exception error){
            Log.e(tag, "start_camera: " + error);
            return;
        }


    }

    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }


    public void onCaptureClick(View view){

        Intent cap = new Intent(this, CapturedActivity.class);
        startActivity(cap);
    }

}
