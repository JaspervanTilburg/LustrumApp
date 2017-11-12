package com.example.tudelftsid.lustrumapp;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.ArrayList;

public class TinderActivity extends AppCompatActivity {

    private PagerAdapterTinder pagerAdapterTinder;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinder);

        pagerAdapterTinder = new PagerAdapterTinder(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.tinderPager);
        mViewPager.setAdapter(pagerAdapterTinder);
        mViewPager.setCurrentItem(0);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_tinder_layout);
        tabLayout.setupWithViewPager(mViewPager);
    }
}
