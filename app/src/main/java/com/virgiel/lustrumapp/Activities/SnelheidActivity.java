package com.virgiel.lustrumapp.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.virgiel.lustrumapp.R;

/**
 * Created by Jip on 9-1-2018.
 */

public class SnelheidActivity extends AppCompatActivity implements LocationListener {

    float nMaxSpeed = -1;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("SNELHEIDSMETER");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.lustrumLightBlue_Dark));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snelheid);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(this.getResources().getColor(R.color.lustrumLightBlue)));

        Typeface head_font = Typeface.createFromAsset(getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        Typeface body_font = Typeface.createFromAsset(getAssets(), "fonts/DIN_Bold.ttf");

        TextView echteSnelheid = (TextView) this.findViewById(R.id.echteSnelheid);
        TextView maxSnelheid = (TextView) this.findViewById(R.id.maxSnelheid);
        TextView titel1 = (TextView) this.findViewById(R.id.snelheidTitel1);
        TextView titel2 = (TextView) this.findViewById(R.id.snelheidTitel2);

        echteSnelheid.setTypeface(body_font);
        maxSnelheid.setTypeface(body_font);
        titel1.setTypeface(head_font);
        titel2.setTypeface(head_font);

        checkLocationPermission();
    }

    @Override
    public void onLocationChanged(Location location) {
        TextView echteSnelheid = (TextView) this.findViewById(R.id.echteSnelheid);
        TextView maxSnelheid = (TextView) this.findViewById(R.id.maxSnelheid);

        if(location == null) {
            echteSnelheid.setText("-,- m/s");
        } else {
            float nCurrentSpeed = location.getSpeed();
            if(nCurrentSpeed > nMaxSpeed) {
                maxSnelheid.setText(nCurrentSpeed + "m/s");
                nMaxSpeed = nCurrentSpeed;
            }
            echteSnelheid.setText(nCurrentSpeed + " m/s");
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public void onResetSpeedClick(View view) {
        nMaxSpeed = -1;
        TextView maxSnelheid = (TextView) this.findViewById(R.id.maxSnelheid);
        maxSnelheid.setText(nMaxSpeed + "");
        Toast.makeText(this, "Max snelheid succesvol gereset", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
                        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);
                        this.onLocationChanged(null);
                    }
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                    nMaxSpeed = -1;
                    TextView echteSnelheid = (TextView) this.findViewById(R.id.echteSnelheid);
                    TextView maxSnelheid = (TextView) this.findViewById(R.id.maxSnelheid);
                    echteSnelheid.setText(nMaxSpeed + "");
                    maxSnelheid.setText(nMaxSpeed + "");
                }
                return;
            }
        }
    }

    private boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Snelheidsmeter")
                        .setMessage("Om de snelheid te meten hebben wij je locatie nodig. Ik weet ook niet precies waarom vraag Android dat.")
                        .setPositiveButton("Ja!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions((Activity) SnelheidActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
                return false;
            } else {
                return true;
            }
        }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
