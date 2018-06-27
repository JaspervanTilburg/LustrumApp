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

public class SoundboardActivity extends AppCompatActivity {

    private SoundPlayer mSoundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboard);

        setTitle("SOUNDBOARD");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window soundboardWindow = this.getWindow();
            soundboardWindow.setStatusBarColor(this.getResources().getColor(R.color.lustrumBlue_Dark));
        }

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(this.getResources().getColor(R.color.lustrumBlue)));

        mSoundPlayer = new SoundPlayer(this);
        Sound[] soundArray = SoundStore.getSounds(this);

        GridView gridView = (GridView) findViewById(R.id.deSoundboard);

        SoundAdapter adapter = new SoundAdapter(this, R.layout.soundboard_button, soundArray);

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Sound sound = (Sound) parent.getItemAtPosition(position);
                mSoundPlayer.playSound(sound);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mSoundPlayer.release();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
