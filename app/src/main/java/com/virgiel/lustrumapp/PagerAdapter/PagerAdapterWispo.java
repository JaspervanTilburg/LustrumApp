package com.virgiel.lustrumapp.PagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.virgiel.lustrumapp.Fragments.TinderFragments.MatchTabFragment;
import com.virgiel.lustrumapp.Fragments.TinderFragments.SwipeTabFragment;
import com.virgiel.lustrumapp.Fragments.TinderFragments.TinderProfileTabFragment;
import com.virgiel.lustrumapp.Fragments.WispoFragments.DinsdagFragment;
import com.virgiel.lustrumapp.Fragments.WispoFragments.MaandagFragment;
import com.virgiel.lustrumapp.Fragments.WispoFragments.WoensdagFragment;

public class PagerAdapterWispo extends FragmentPagerAdapter {

    public PagerAdapterWispo(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MaandagFragment tab1 = new MaandagFragment();
                return tab1;
            case 1:
                DinsdagFragment tab2 = new DinsdagFragment();
                return tab2;
            case 2:
                WoensdagFragment tab3 = new WoensdagFragment();
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
                return "Ma";
            case 1:
                return "Di";
            case 2:
                return "Wo";
        }
        return null;
    }
}
