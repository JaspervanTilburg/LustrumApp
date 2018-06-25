package com.virgiel.lustrumapp.Activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;

import com.virgiel.lustrumapp.R;
import com.virgiel.lustrumapp.Soundboard.Sound;
import com.virgiel.lustrumapp.Soundboard.SoundAdapter;
import com.virgiel.lustrumapp.Soundboard.SoundPlayer;
import com.virgiel.lustrumapp.Soundboard.SoundStore;

/**
 * Created by Jip on 24-11-2017.
 */

public class PintenVangenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pintenvangen);

    }
}
