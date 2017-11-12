package com.example.tudelftsid.lustrumapp;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapterMain extends FragmentPagerAdapter {

    public PagerAdapterMain(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TijdlijnTabFragment tab1 = new TijdlijnTabFragment();
                return tab1;
            case 1:
                ProfielTabFragment tab2 = new ProfielTabFragment();
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
                return "TIJDLIJN";
            case 1:
                return "PROFIEL";
            case 2:
                return "FEATURES";
        }
        return null;
    }
}
