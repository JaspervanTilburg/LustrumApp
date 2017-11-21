package com.example.tudelftsid.lustrumapp;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.CheckBox;


public class TinderActivity extends AppCompatActivity {

    private PagerAdapterTinder pagerAdapterTinder;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinder);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window dateSpelWindow = this.getWindow();
            dateSpelWindow.setStatusBarColor(this.getResources().getColor(R.color.lustrumPink_Dark));
        }
        setTitle("TOKO TINDER");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(this.getResources().getColor(R.color.lustrumPink)));

        pagerAdapterTinder = new PagerAdapterTinder(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.tinderPager);
        mViewPager.setAdapter(pagerAdapterTinder);
        mViewPager.setCurrentItem(0);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_tinder_layout);
        tabLayout.setupWithViewPager(mViewPager);

        Preferences.initPreferences();
    }

    public void onMannenCheckBoxClick() {
        CheckBox checkBox = findViewById(R.id.mannenCheckBox);
        if (checkBox.isChecked()) {
            Preferences.setPreferMen(false);
        } else {
            Preferences.setPreferMen(true);
        }
    }

    public void onVrouwenCheckBoxClick() {
        CheckBox checkBox = findViewById(R.id.vrouwenCheckBox);
        if (checkBox.isChecked()) {
            Preferences.setPreferWomen(false);
        } else {
            Preferences.setPreferWomen(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
