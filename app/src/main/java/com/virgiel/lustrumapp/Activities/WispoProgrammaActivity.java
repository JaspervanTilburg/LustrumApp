package com.virgiel.lustrumapp.Activities;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.virgiel.lustrumapp.PagerAdapter.PagerAdapterMain;
import com.virgiel.lustrumapp.PagerAdapter.PagerAdapterWispo;
import com.virgiel.lustrumapp.R;

/**
 * Created by Rolf on 24-11-2017.
 */

public class WispoProgrammaActivity extends AppCompatActivity {

    private PagerAdapterWispo pagerAdapterWispo;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wispo_programma);

        pagerAdapterWispo = new PagerAdapterWispo(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.wispo_pager);
        mViewPager.setAdapter(pagerAdapterWispo);
        mViewPager.setCurrentItem(0);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.wispo_tab_layout);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.lustrumLightBlue_Dark));
        tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.lustrumLightBlue_Light)));
    }
}
