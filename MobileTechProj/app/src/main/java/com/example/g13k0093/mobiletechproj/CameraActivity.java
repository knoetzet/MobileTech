package com.example.g13k0093.mobiletechproj;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    public LocationManager locationManager;
    public LocationListener locationListener;
    TextView texty;
    int id;


    //variables for database
    Double latitude;
    Double longitude;
    Double timestamp;
    String image;
    int thumbnail;


    File mediaFile;
    String urio;


    // Camera variables
    static Camera camera = null;

    SurfaceView surfaceView;
    SurfaceHolder holder;
    Camera.PictureCallback rawCallback;
    Camera.PictureCallback jpegCallback;
    Camera.ShutterCallback shutterCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        texty = (TextView) findViewById(R.id.textView6);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
        id = getIntent().getIntExtra("ID",0);
        thumbnail = getIntent().getIntExtra("thumb",0);
        holder = surfaceView.getHolder();
        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        holder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                texty.setText("WHY????");
                String msg = "New Latitude: " + location.getLatitude() + "New Longitude: " + location.getLongitude();
                Toast.makeText(getBaseContext(), "IN ON LOCATION CHANGE, lat=" + location.getLatitude() + ", lon=" + location.getLongitude(), Toast.LENGTH_LONG).show();
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
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, locationListener);
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Toast.makeText(this.getApplicationContext(), "Your Permission is needed to get access the camera location", Toast.LENGTH_LONG).show();
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
                } else {
                }
            }

        } else {

        }

        rawCallback = new Camera.PictureCallback() {
            public void onPictureTaken(byte[] data, Camera camera) {
            }
        };

        /** Handles data for jpeg picture */
        shutterCallback = new Camera.ShutterCallback() {
            public void onShutter() {
            }
        };
        jpegCallback = new Camera.PictureCallback() {
            public void onPictureTaken(byte[] data, Camera camera) {
                File pictureFile = getOutputMediaFile();
                if (pictureFile == null) {
                    return;
                }
                try {
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    fos.write(data);
                    fos.close();
                    urio = Uri.fromFile(mediaFile).toString();
                    Intent cap = new Intent(getApplicationContext(),CapturedActivity.class);
                    cap.putExtra("pic",urio);
                    startActivity(cap);

                } catch (FileNotFoundException e) {

                } catch (IOException e) {
                }
            }
        };
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
        setCameraDisplayOrientation(this,0,camera);
        refreshCamera();
    }


    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio=(double)h / w;

        if (sizes == null) return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }


    public void surfaceCreated(SurfaceHolder holder) {
        try {
            camera = Camera.open();

        } catch (RuntimeException error) {
            System.err.println(error);
            return;
        }

        //  parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);

        Camera.Parameters param;
        param = camera.getParameters();
        param.setPreviewSize(352, 288);
        param.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        setCameraDisplayOrientation(this,0,camera);
        camera.setParameters(param);
        int rotation = this.getWindowManager().getDefaultDisplay().getRotation();
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();

        } catch (Exception error) {
            System.err.println(error);
            return;
        }
    }

    public static void setCameraDisplayOrientation(Activity activity, int cameraId, android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }

    private File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "BioMappImages");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("BioMappImages", "failed to create directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");


        return mediaFile;
    }

    public void getLocation(){


        Location location = null;
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetworkEnabled) {
            // no network provider is enabled
        } else {
       //     this.canGetLocation = true;
            if (isNetworkEnabled) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    }
                }
            }
            // if GPS Enabled get lat/long using GPS Services
            if (isGPSEnabled) {
                if (location == null) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
            }
        }
   // insert location and time into squile light and photo uri
}


    @TargetApi(23)
    private void useCameraFor23() {
        int hasLocationPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);

        if (hasLocationPermission != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        // if it wasn't the request code I wanted, then call the super
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    public void Record(View view) {
         Intent rec = new Intent(this, RecordsActivity.class);
         startActivity(rec);
    }


    public void Capture(View view) {
      //  getLocation();
        //here must take photo and get location etc
        // then go to captured page
        camera.takePicture(shutterCallback, rawCallback, jpegCallback);

        Intent cap = new Intent(getApplicationContext(),CapturedActivity.class);
     //  Toast.makeText(getApplicationContext(),urio,Toast.LENGTH_LONG).show();
        cap.putExtra("pic",urio);
        cap.putExtra("ID", id);
        cap.putExtra("thumb", thumbnail);
        startActivity(cap);

     //   camera.stopPreview();
     //   camera.release();
      //   Intent cap = new Intent(this, CapturedActivity.class);
      //   startActivity(cap);
        }
    }


