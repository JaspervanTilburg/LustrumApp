package com.example.tudelftsid.lustrumapp;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                InfoTabFragment tab1 = new InfoTabFragment();
                return tab1;
            case 1:
                ActiviteitenTabFragment tab2 = new ActiviteitenTabFragment();
                return tab2;
            case 2:
                FeaturesTabFragment tab3 = new FeaturesTabFragment();
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
                return "INFO";
            case 1:
                return "ACTIVITEITEN";
            case 2:
                return "FEATURES";
        }
        return null;
    }
}
