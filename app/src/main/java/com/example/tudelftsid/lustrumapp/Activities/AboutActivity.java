package com.example.tudelftsid.lustrumapp.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tudelftsid.lustrumapp.R;

/**
 * Created by Rolf on 24-11-2017.
 */

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("ABOUT");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Typeface head_font = Typeface.createFromAsset(getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        Typeface body_font = Typeface.createFromAsset(getAssets(), "fonts/DIN_Bold.ttf");

    }

    @Override
    public boolean onSupportNavigateUp(){
        Toast.makeText(this, "Bravo 47", Toast.LENGTH_SHORT).show();
        return true;
    }
}
