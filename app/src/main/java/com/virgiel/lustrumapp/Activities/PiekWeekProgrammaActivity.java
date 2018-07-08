package com.virgiel.lustrumapp.Activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.virgiel.lustrumapp.R;

/**
 * Created by Jip on 8-7-2018.
 */

public class PiekWeekProgrammaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("PROGRAMMA PIEKWEEK");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window piekWeekProgrammaWindow = this.getWindow();
            piekWeekProgrammaWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            piekWeekProgrammaWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            piekWeekProgrammaWindow.setStatusBarColor(this.getResources().getColor(R.color.lustrumGreen));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piekweek_programma);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(this.getResources().getColor(R.color.lustrumGreen_Dark)));
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
