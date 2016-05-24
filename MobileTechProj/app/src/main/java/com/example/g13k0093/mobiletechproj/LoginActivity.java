package com.example.g13k0093.mobiletechproj;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    String regUrl = "http://vmus.adu.org.za/dt_registration.php";
    Intent reg = new Intent(Intent.ACTION_VIEW, Uri.parse(regUrl));
    private static final int REQUEST_CAMERARESULT = 201;
    final int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        int currentAPIVersion = Build.VERSION.SDK_INT;

        // only call this if  using marhmallow.  otherwise this checkSelfPermission doesn't exist
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            useCameraFor23();
        } else {
            useCamera();
        }

    }

    public void onRegClick(View view) {
        startActivity(reg);
    }





    @TargetApi(23)
    private void useCameraFor23() {
        int hasCameraPermission = checkSelfPermission(Manifest.permission.CAMERA);

        if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.CAMERA}, REQUEST_CODE_ASK_PERMISSIONS);

            return;
        }

        useCamera();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                useCamera();
                return;
            }
        }
        // if it wasn't the request code I wanted, then call the super
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onLoginClick(View view) {



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.getApplicationContext().checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                Intent log = new Intent(this, CameraActivity.class);
                startActivity(log);
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    Toast.makeText(this.getApplicationContext(), "Your Permission is needed to get access the camera", Toast.LENGTH_LONG).show();
                }
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUEST_CAMERARESULT);
            }
        } else {
            Intent log = new Intent(this, CameraActivity.class);
            startActivity(log);
        }

    }

    private void useCamera() {
        Intent log = new Intent(this, CameraActivity.class);
        startActivity(log);
    }
}
