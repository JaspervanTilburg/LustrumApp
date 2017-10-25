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

    private LustrumButton selectedButton;
    private boolean expanded;


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

        findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
            }
            public void onSwipeRight() {
                if (mViewPager.getCurrentItem() == 1) {
                    onActiviteitClicked(selectedButton.getPrevious());
                }
            }
            public void onSwipeLeft() {
                if (mViewPager.getCurrentItem() == 1) {
                    onActiviteitClicked(selectedButton.getNext());
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
        if (view == null) {
            return;
        }
        LustrumButton button = (LustrumButton) view;
        if (!selectedButton.equals(button)) {
            if (expanded) {
                selectedButton.animateCollapse();
                expanded = false;
            }
            selectedButton.animateScaleDown();
            button.animateScaleUp();
            selectedButton = button;
            button.setBackgroundColor(this);
        } else {
            if (expanded) {
                button.animateCollapse();
                expanded = false;
            } else {
                button.animateExpand();
                expanded = true;
            }
        }
    }

    public void setSelectedButton(LustrumButton button) {
        this.selectedButton = button;
    }
}
