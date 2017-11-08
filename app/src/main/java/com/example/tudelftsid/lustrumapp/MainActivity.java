package com.example.tudelftsid.lustrumapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private PagerAdapter pagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(0);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.tijdlijn_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.profiel_icon);
        tabLayout.getTabAt(2).setIcon(R.drawable.features_icon);
    }

    public void onTinderClick(View view) {
        Intent intent = new Intent(this, TinderActivity.class);
        startActivity(intent);
    }

    public void onInfoClick(View view) {
        Intent intent = new Intent(this, GalaInfoActivity.class);
        startActivity(intent);
    }

    public void onBerichtenClick(View view) {
        Intent intent = new Intent(this, BerichtenActivity.class);
        startActivity(intent);
    }

    public void onDatespelClick(View view) {
        Intent intent = new Intent(this, DatespelActivity.class);
        startActivity(intent);
    }

}
