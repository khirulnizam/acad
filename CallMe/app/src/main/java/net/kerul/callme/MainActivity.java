package net.kerul.callme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private static final int REQUEST_PHONE_CALL = 1;
    String phoneme = "+60129034614";
    //call direct
    public void calldirect(View v){

        //check uses-permission
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED){//to check uses-permission not grnted
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_PHONE_CALL);//pop-up asking for permission
        }else{
            //startActivity(callintent);
        }
    }

    //override onrequestpermissionresult
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if(requestCode==1){
            if(grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Intent callintent= new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:"+phoneme));
                startActivity(callintent);
            }
            else{
                Toast.makeText(this,"Call permission denied", Toast.LENGTH_SHORT);
            }
        }
    }//end onRequestPermissionsResult


    //invoke dial window
    public void callme(View v){
        Intent dialintent= new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:"+phoneme));
        startActivity(dialintent);

        //CALL_PHONE uses-permission
        //AndroidManifest

    }
}