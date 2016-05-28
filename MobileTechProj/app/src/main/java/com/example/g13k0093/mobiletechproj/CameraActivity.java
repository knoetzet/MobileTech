package com.example.g13k0093.mobiletechproj;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    public LocationManager locationManager;
    public LocationListener locationListener1;
    TextView texty;

    Button button;


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
        texty = (TextView)findViewById(R.id.textView6);
        button = (Button)findViewById(R.id.blahbutton);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);

        holder = surfaceView.getHolder();
        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        holder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener1 = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                texty.setText("WHY????");
                String msg = "New Latitude: " + location.getLatitude() + "New Longitude: " + location.getLongitude();
                Toast.makeText(getBaseContext(),  "IN ON LOCATION CHANGE, lat=" + location.getLatitude() + ", lon=" + location.getLongitude(), Toast.LENGTH_LONG).show();
                //Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                texty.setText("WHY????");
            }

            @Override
            public void onProviderEnabled(String provider) {
                Toast.makeText(getBaseContext(), "Gps is turned on!! ",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
                Toast.makeText(getBaseContext(), "Gps is turned off!! ",
                        Toast.LENGTH_SHORT).show();
            }

        };


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, locationListener1);
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Toast.makeText(this.getApplicationContext(), "Your Permission is needed to get access the camera location", Toast.LENGTH_LONG).show();
                    hello();
                } else {
                    hello();
                }
            }

        } else {

            hello();

        }
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


    @TargetApi(23)
    private void useCameraFor23() {
        int hasLocationPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);

        if(hasLocationPermission != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.INTERNET}, REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                hello();
                return;
            }
        }
        // if it wasn't the request code I wanted, then call the super
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

  /*  protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }*/


    public void hello(){

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Toast.makeText(getBaseContext(),  "are we here", Toast.LENGTH_LONG).show();

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener1);
            }
        });
      //  Intent cap = new Intent(this, CapturedActivity.class);
       // startActivity(cap);
    }


    public void location(View view){
        String location = locationManager.getLastKnownLocation();
        Toast.makeText(getBaseContext(),  location, Toast.LENGTH_LONG).show();
    }

}
