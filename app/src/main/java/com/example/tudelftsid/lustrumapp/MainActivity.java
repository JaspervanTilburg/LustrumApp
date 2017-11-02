package com.example.tudelftsid.lustrumapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.tudelftsid.lustrumapp.InfoPages.GalaInfoActivity;
import com.example.tudelftsid.lustrumapp.InfoPages.LustrumWekenInfoActivity;
import com.example.tudelftsid.lustrumapp.InfoPages.PiekWeekInfoActivity;
import com.example.tudelftsid.lustrumapp.InfoPages.WispoInfoActivity;

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
        mViewPager.setCurrentItem(1);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    public void onTinderClick(View view) {
        Intent intent = new Intent(this, TinderActivity.class);
        startActivity(intent);
    }

    public void onGalaInfoClick(View view) {
        Intent intent = new Intent(this, GalaInfoActivity.class);
        startActivity(intent);
    }

    public void onWispoInfoClick(View view) {
        Intent intent = new Intent(this, WispoInfoActivity.class);
        startActivity(intent);
    }

    public void onLustrumWekenInfoClick(View view) {
        Intent intent = new Intent(this, LustrumWekenInfoActivity.class);
        startActivity(intent);
    }

    public void onPiekWeekInfoClick(View view) {
        Intent intent = new Intent(this, PiekWeekInfoActivity.class);
        startActivity(intent);
    }
}
