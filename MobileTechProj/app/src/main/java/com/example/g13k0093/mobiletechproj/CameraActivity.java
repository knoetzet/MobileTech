package com.example.g13k0093.mobiletechproj;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
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

    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder holder;
    String tag;

    SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        startcamera();
    }


    private void startcamera(){
        try{
            camera = Camera.open();
        }catch(RuntimeException error){

            Log.e(tag, "init_camera: " + error);
            return;
            //you have failed or send to place to end camera
        }
        Camera.Parameters params;
        params = camera.getParameters();
        params.setPreviewFrameRate(20);
        params.setPreviewSize(176, 144);
        camera.setParameters(params);

        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        }catch(Exception error){


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


}
