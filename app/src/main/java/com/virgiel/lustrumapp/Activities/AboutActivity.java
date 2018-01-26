package com.virgiel.lustrumapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.virgiel.lustrumapp.R;

/**
 * Created by Rolf on 24-11-2017.
 */

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("'Vo voor ons");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp(){
        Toast.makeText(this, "Bravo 47", Toast.LENGTH_SHORT).show();
        finish();
        return true;
    }
}
