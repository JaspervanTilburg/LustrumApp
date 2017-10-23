package com.example.tudelftsid.lustrumapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
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

        findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
            }
            public void onSwipeRight() {
                if (mViewPager.getCurrentItem() == 1) {
                    changeActiviteit(previousButton());
                }
            }
            public void onSwipeLeft() {
                if (mViewPager.getCurrentItem() == 1) {
                    changeActiviteit(nextButton());
                }
            }
            public void onSwipeBottom() {
            }

        });
    }

    public void onTinderClick(View view) {
        Intent intent = new Intent(this, TinderActivity.class);
        startActivity(intent);
    }

    public void onActiviteitClicked(View view) {
        if (selectedActiviteit == view.getId()) {
            //TODO
        } else {
            changeActiviteit(view.getId());
        }
    }

    public void changeActiviteit(int buttonID) {
        ImageButton newButton = (ImageButton) findViewById(buttonID);
        ImageButton oldButton = (ImageButton) findViewById(selectedActiviteit);
        selectedActiviteit = buttonID;
        resizeButtons(oldButton, newButton);
        colorBackground();
    }

    public void resizeButtons(ImageButton oldButton, ImageButton newButton) {
        ResizeAnimation scaleDown = new ResizeAnimation(oldButton, 0.5);
        ResizeAnimation scaleUp = new ResizeAnimation(newButton, 2);
        scaleDown.setDuration(100);
        scaleUp.setDuration(100);
        oldButton.startAnimation(scaleDown);
        newButton.startAnimation(scaleUp);
    }

    public int nextButton() {
        switch (selectedActiviteit) {
            case R.id.galaButton :
                return R.id.wispoButton;
            case R.id.wispoButton :
                return R.id.lustrumWeekButton;
            case R.id.lustrumWeekButton :
                return R.id.piekWekenButton;
            case R.id.piekWekenButton :
                return R.id.piekWekenButton;
        }
        return -1;
    }

    public int previousButton() {
        switch (selectedActiviteit) {
            case R.id.galaButton :
                return R.id.galaButton;
            case R.id.wispoButton :
                return R.id.galaButton;
            case R.id.lustrumWeekButton :
                return R.id.wispoButton;
            case R.id.piekWekenButton :
                return R.id.lustrumWeekButton;
        }
        return -1;
    }

    public void colorBackground() {
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
