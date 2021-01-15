package net.kerul.callsimple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ui
        txtphone=(EditText)findViewById(R.id.txtphonenumber);
    }

    //simply open the dialer
    public void dialnumber(View v){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + txtphone.getText().toString()));// Initiates the Intent
        startActivity(intent);
    }

    private static final int REQUEST_PHONE_CALL = 1;
    Intent callintent;
    //call direct
    public void callnumber(View v){

        callintent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+918511812660"));

        //if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
        }
        else
        {
            startActivity(callintent);
        }

    }

    //override onrequestpermissionresult
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_CALL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+918511812660"));
                    startActivity(callintent);
                }
                else
                {
                    Toast.makeText(this,"Call permission denied",Toast.LENGTH_SHORT);
                }
                return;
            }
        }
    }

}