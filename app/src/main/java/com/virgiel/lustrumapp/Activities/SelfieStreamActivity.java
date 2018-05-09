package com.virgiel.lustrumapp.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.virgiel.lustrumapp.LustrumRestClient;
import com.virgiel.lustrumapp.R;
import com.virgiel.lustrumapp.Selfie;
import com.virgiel.lustrumapp.SelfieStreamAdapter;
import com.virgiel.lustrumapp.Utils;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by TUDelft SID on 10-4-2018.
 */

public class SelfieStreamActivity extends AppCompatActivity {

    private Typeface body_font;
    private List<Selfie> selfies;
    public static final int CAMERA_PERMISSION = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfie_stream);
        body_font = Typeface.createFromAsset(getAssets(), "fonts/DIN_Bold.ttf");

        retrieveSelfies();

        FloatingActionButton photoButton = (FloatingActionButton) findViewById(R.id.photoButton);
        photoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                } else {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePictureIntent, CAMERA_PERMISSION);
                }
            }
        });
    }

    public void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PERMISSION && data != null) {
            LustrumRestClient.postSelfie((Bitmap) data.getExtras().get("data"), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    System.out.println("Selfie posted: " + response);
                    Toast.makeText(getApplicationContext(), "Selfie Posted", Toast.LENGTH_SHORT).show();
                    retrieveSelfies();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                    System.out.println("Something went wrong with selfie post" + statusCode + ", " + msg + ", " + throwable);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject msg) {
                    System.out.println("Something went wrong with selfie post " + msg);
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    public void retrieveSelfies() {
        LustrumRestClient.getWithHeader("/selfies", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Selfies Retrieved: " + response);

                selfies = Utils.loadSelfies(response);
                SelfieStreamAdapter adapter = new SelfieStreamAdapter(getApplicationContext(), selfies, body_font);
                ListView itemsListView  = findViewById(R.id.selfieListView);
                itemsListView.setAdapter(adapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong, statuscode: " + statusCode + ", " + msg);
            }
        });
    }
}
