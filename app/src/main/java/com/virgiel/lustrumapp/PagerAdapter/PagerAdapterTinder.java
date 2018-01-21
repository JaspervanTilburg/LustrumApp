package com.virgiel.lustrumapp.PagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.virgiel.lustrumapp.Fragments.MatchTabFragment;
import com.virgiel.lustrumapp.Fragments.SwipeTabFragment;
import com.virgiel.lustrumapp.Tinder.TinderProfileTabFragment;

public class PagerAdapterTinder extends FragmentPagerAdapter {

    public PagerAdapterTinder(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SwipeTabFragment tab1 = new SwipeTabFragment();
                return tab1;
            case 1:
                MatchTabFragment tab2 = new MatchTabFragment();
                return tab2;
            case 2:
                TinderProfileTabFragment tab3 = new TinderProfileTabFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SWIPEN";
            case 1:
                return "MATCHES";
            case 2:
                return "PROFIEL";
        }
        return null;
    }
}
