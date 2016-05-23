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
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback  {

    static Camera camera = null;
    SurfaceView surfaceView;
    SurfaceHolder holder;
    String tag;
    Camera.PictureCallback rawCallback;
    Camera.PictureCallback jpegCallback;
    Camera.ShutterCallback shutterCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);



        surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);

        holder = surfaceView.getHolder();
        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        holder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }


    public void refreshCamera() {
        if (holder.getSurface() == null) {
            // preview surface does not exist
            return;
        }
        try {

            camera.stopPreview();
        } catch (Exception error) {
        }
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (Exception error) {

        }
    }


    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        refreshCamera();
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try{
            camera = Camera.open();

        }catch(RuntimeException error){
            System.err.println(error);
            return;
        }
        Camera.Parameters parameters = camera.getParameters();
      //  parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(parameters);
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        }catch(Exception error){
            System.err.println(error);
            return;
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }


    public void onCaptureClick(View view){

        Intent cap = new Intent(this, CapturedActivity.class);
        startActivity(cap);
    }

}
