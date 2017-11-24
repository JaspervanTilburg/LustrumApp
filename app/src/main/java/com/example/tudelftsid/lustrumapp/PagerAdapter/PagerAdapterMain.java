package com.example.tudelftsid.lustrumapp.PagerAdapter;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.support.v4.view.PagerAdapter;

        import com.example.tudelftsid.lustrumapp.Fragments.FeaturesTabFragment;
        import com.example.tudelftsid.lustrumapp.Fragments.ProfielTabFragment;
        import com.example.tudelftsid.lustrumapp.Fragments.TijdlijnTabFragment;

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
                return new ProfielTabFragment();
            case 2:
                FeaturesTabFragment tab3 = new FeaturesTabFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
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
