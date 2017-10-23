package com.example.tudelftsid.lustrumapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private PagerAdapter pagerAdapter;
    private ViewPager mViewPager;

    private int selectedActiviteit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);

        selectedActiviteit = R.id.galaButton;
    }

    public void onTinderClick(View view) {
        Intent intent = new Intent(this, TinderActivity.class);
        startActivity(intent);
    }

    public void onActiviteitClicked(View view) {
        int buttonID = view.getId();
        ImageButton newButton = (ImageButton) findViewById(buttonID);
        ImageButton oldButton = (ImageButton) findViewById(selectedActiviteit);
        selectedActiviteit = buttonID;
        resizeButtons(oldButton, newButton);
        colorBackground(view);
    }

    public void resizeButtons(ImageButton oldButton, ImageButton newButton) {
        ResizeAnimation scaleDown = new ResizeAnimation(oldButton, 0.5);
        ResizeAnimation scaleUp = new ResizeAnimation(newButton, 2);
        scaleDown.setDuration(100);
        scaleUp.setDuration(100);
        oldButton.startAnimation(scaleDown);
        newButton.startAnimation(scaleUp);
    }

    public void colorBackground(View view) {
        int color = 0;
        switch (selectedActiviteit) {
            case R.id.galaButton :
                color = getResources().getColor(R.color.lustrumPink);
                break;
            case R.id.wispoButton :
                color = getResources().getColor(R.color.lustrumLightBlue);
                break;
            case R.id.lustrumWeekButton :
                color = getResources().getColor(R.color.lustrumBlue);
                break;
            case R.id.piekWekenButton :
                color = getResources().getColor(R.color.lustrumGreen);
                break;
        }
        findViewById(R.id.activiteitenBackground).setBackgroundColor(color);
    }

}
