package com.example.tudelftsid.lustrumapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.tudelftsid.lustrumapp.R;
import com.example.tudelftsid.lustrumapp.Soundboard.Sound;
import com.example.tudelftsid.lustrumapp.Soundboard.SoundPlayer;
import com.example.tudelftsid.lustrumapp.Soundboard.SoundStore;

/**
 * Created by Jip on 24-11-2017.
 */

public class SoundboardActivity extends AppCompatActivity {

    private SoundPlayer mSoundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboard);

        mSoundPlayer = new SoundPlayer(this);
        Sound[] soundArray = SoundStore.getSounds(this);

        GridView gridView = (GridView) findViewById(R.id.gridView);

        final ArrayAdapter<Sound> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, soundArray);

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
}
