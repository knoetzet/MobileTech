package com.example.g13k0093.mobiletechproj;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_CONTACT_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view){
        sendSMS();
    }

    public void onPress(View view){
        pickcontact();
    }

    public void pickcontact() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, PICK_CONTACT_REQUEST);
        }
    }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_CONTACT_REQUEST){
            Uri contactData = data.getData();

            Cursor c = getContentResolver().query(contactData, null, null, null, null);

            if(c.moveToFirst()){
                String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Toast.makeText(this,"successfully picked a contact:" + name, Toast.LENGTH_LONG).show();
            }
        }

    }


    public void sendSMS(){
        Intent send = new Intent(Intent.ACTION_VIEW);
        send.setData(Uri.parse("http://www.google.com"));
        send.putExtra("sms_body","Hi there!");

        if(send.resolveActivity(getPackageManager())!=null)
        {
            startActivity(send);
        }
    }
}

