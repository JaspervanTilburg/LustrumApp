package com.virgiel.lustrumapp.Activities;

import android.app.ActionBar;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.virgiel.lustrumapp.PagerAdapter.PagerAdapterWispo;
import com.virgiel.lustrumapp.R;

/**
 * Created by Jip on 23-3-2018.
 */

public class LustrumWeekProgrammaActivity extends AppCompatActivity {
    private PagerAdapterWispo pagerAdapterWispo;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("PROGRAMMA LUSTRUMWEEK");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window lustrumWeekProgrammaWindow = this.getWindow();
            lustrumWeekProgrammaWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            lustrumWeekProgrammaWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            lustrumWeekProgrammaWindow.setStatusBarColor(this.getResources().getColor(R.color.lustrumBlue));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lustrumweek_programma);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(this.getResources().getColor(R.color.lustrumBlue_Dark)));
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
