package com.example.tudelftsid.lustrumapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tudelftsid.lustrumapp.R;

import org.w3c.dom.Text;

public class TinderMatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        TextView textView = findViewById(R.id.newMatchTxt);
        textView.setText(getIntent().getStringExtra("name"));
    }

    public void onAddContactClick() {

    }
}
