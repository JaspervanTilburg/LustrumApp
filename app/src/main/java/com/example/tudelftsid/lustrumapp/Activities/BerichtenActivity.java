package com.example.tudelftsid.lustrumapp.Activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tudelftsid.lustrumapp.R;

public class BerichtenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berichten);

        TextView title = (TextView) findViewById(R.id.nogGeenBerichten);
        Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        title.setTypeface(font);
    }
}
