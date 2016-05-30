package com.example.g13k0093.mobiletechproj;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;



import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    String email;
    String ADUid;
    String password;
    String API_key;


    String regUrl = "http://vmus.adu.org.za/dt_registration.php";
    Intent reg = new Intent(Intent.ACTION_VIEW, Uri.parse(regUrl));
    private static final int REQUEST_CAMERARESULT = 201;
    final int REQUEST_CODE_ASK_PERMISSIONS = 123;



    String preferencefile = "com.biomapp.useraccount.1";
    private SharedPreferences preferencesuser;
    private SharedPreferences.Editor preferenceEditor;
    public static final String myBioMappPREFERENCES = "BioMappUser";
    private static final int PREFERENCE_MODE_PRIVATE =0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        int currentAPIVersion = Build.VERSION.SDK_INT;

        // only call this if  using marhmallow.  otherwise this checkSelfPermission doesn't exist
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            useCameraFor23();
        } else {

        }
        preferencesuser = getSharedPreferences(myBioMappPREFERENCES,Context.MODE_PRIVATE );
        //check if user has logged in before.
            Check();




    }


    public void Check(){
        if(preferencesuser != null) {
            String name = preferencesuser.getString("userid", "none");
            if (name != "none") {
                Intent rec = new Intent(getApplicationContext(), RecordsActivity.class);
                startActivity(rec);
            }
        }

    }




    public void onRegClick(View view) {
        startActivity(reg);
    }


    @TargetApi(23)
    private void useCameraFor23() {
        int hasCameraPermission = checkSelfPermission(Manifest.permission.CAMERA);


        if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_ASK_PERMISSIONS);

            return;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

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


    public String MD5Hash(String s) {
        StringBuffer MD5Hash = new StringBuffer();
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();


            for (int i = 0; i < messageDigest.length; i++)
            {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                MD5Hash.append(h);
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return MD5Hash.toString();
    }


    public void Camera(View view) {



       email =   "cription211@gmail.com"    ;    //((EditText) findViewById(R.id.editText2)).getText().toString();
        ADUid =  "16869";            //((EditText) findViewById(R.id.editText)).getText().toString();
       password =  "887f113a8e1250020959eda260f7318c";  //MD5Hash(((EditText) findViewById(R.id.editText3)).getText().toString());
       API_key = "f10dd19acf0acb4c0d16e9cfb2ad9d65";

       // "http://api.adu.org.za/validation/user/login?API_KEY=45d8dc528fc799f6d154497a6fe1f272&userid=90000&email=bobbytwoshoes@anemailserver.com&passid=a9c4cef5735770e657b7c25b9dcb807b";

        RequestParams params = new RequestParams();

        params.put("userid",ADUid);
        params.put("email",email);
        params.put("passid",password);
        params.put("API_KEY",API_key);


        //"http://api.adu.org.za/validation/user/login?API_KEY=" + "&userid=" + ADUid + "&email=" + email + "&passid=" + password;
        invokeWS(params);
        Check();
    }


    public void invokeWS(RequestParams params){

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://api.adu.org.za/validation/user/login?",params ,new JsonHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject obj) {
                //  super.onSuccess(statusCode, headers, response);
                try {
                  // Toast.makeText(getApplicationContext(), obj.getJSONObject("registered").getJSONObject("status").get("result").toString(), Toast.LENGTH_LONG).show();
                  boolean permissions = obj.getJSONObject("registered").getJSONObject("status").get("result").equals("success");

                        if(permissions){

                            //get token
                            String token = "" + obj.getJSONObject("registered").getJSONObject("status").get("token");
                            //String email = "" + obj.getJSONObject("registered").getJSONObject("data").get("Email");


                            preferenceEditor = preferencesuser.edit();

                            preferenceEditor.putString("email",email);
                            preferenceEditor.putString("password",password);
                            preferenceEditor.putString("userid",ADUid);
                            preferenceEditor.putString("token",token);
                            preferenceEditor.commit();

                            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();

                        }
                   else{
                            //error not correct details
                           Toast.makeText(getApplicationContext(), "invalid credentials", Toast.LENGTH_LONG).show();
                       }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }
        });
    }

}







