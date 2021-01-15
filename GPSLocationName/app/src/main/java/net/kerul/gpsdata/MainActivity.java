package net.kerul.gpsdata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements
        FetchAddressTask.OnTaskCompleted {
    private FusedLocationProviderClient fusedLocationClient;
    private double latitude, longitude;
    Location mlocation;
    ProgressDialog progressDialog;
    private FusedLocationProviderClient mFusedLocationClient;
    EditText txtphonenumber;
    Intent callIntent;

    public void callMaps(View v) {
        Intent gmaps = new Intent(this, MapsActivity.class);
        startActivity(gmaps);
    }

    private int locationRequestCode = 1000;
    private double wayLatitude = 3.138675, wayLongitude = 101.6169494;//KL 3.138675,101.6169494
    public TextView tvGPS,tvaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //UI
        tvGPS = (TextView)findViewById(R.id.tvGPS);
        tvaddress = (TextView)findViewById(R.id.tvaddress);
        progressDialog=new ProgressDialog(this);
        //txtphonenumber=(EditText)findViewById(R.id.txtphonenumber);

        //progress dialog
        progressDialog.show();


        //gps
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    locationRequestCode);
        } else {
            //permission already granted
        }


        ((FusedLocationProviderClient) fusedLocationClient).getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        //assign location to mlocation
                        mlocation=location;
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            //latitude = location.getLatitude();
                            wayLatitude = location.getLatitude();
                            wayLongitude = location.getLongitude();
                            tvGPS.setText(String.format(Locale.ENGLISH, " 1GPS Lat:Long %s -- %s", wayLatitude, wayLongitude));
                            //show progress dialog
                            //progressDialog.show();
                            //fetch address
                            new FetchAddressTask(MainActivity.this,MainActivity.this).execute(location);
                        }
                    }
                });

        //Toast.makeText(this,"Latitude : "+latitude,Toast.LENGTH_LONG).show();

        // Start the reverse geocode AsyncTask
        //new FetchAddressTask(MainActivity.this,MainActivity.this).execute(mlocation);
        //update address value

    }//end onCreate

    //update Address
    @Override
    public void onTaskCompleted(String result) {
        if (progressDialog.isShowing())progressDialog.dismiss();
        tvaddress.setText("Address base GPS: "+result);
    }

    //@SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                                locationRequestCode);
                        return;
                    }
                    mFusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                        if (location != null) {
                            wayLatitude = location.getLatitude();
                            wayLongitude = location.getLongitude();
                            tvGPS.setText(String.format(Locale.ENGLISH, " GPS Lat:Long %s -- %s", wayLatitude, wayLongitude));
                            mlocation=location;
                        }
                    });
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }//end onRequestPermissionsResult
}

