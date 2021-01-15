package net.kerul.callme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    String phoneme = "+60129034614";
    public void callme(View v){
        Intent dialintent= new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:"+phoneme));
        startActivity(dialintent);

        //CALL_PHONE uses-permission
        //AndroidManifest

    }
}