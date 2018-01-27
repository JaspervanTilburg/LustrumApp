package com.virgiel.lustrumapp.PagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.virgiel.lustrumapp.Fragments.WispoFragments.DinsdagFragment;
import com.virgiel.lustrumapp.Fragments.WispoFragments.DonderdagFragment;
import com.virgiel.lustrumapp.Fragments.WispoFragments.MaandagFragment;
import com.virgiel.lustrumapp.Fragments.WispoFragments.VrijdagFragment;
import com.virgiel.lustrumapp.Fragments.WispoFragments.ZaterdagFragment;
import com.virgiel.lustrumapp.Fragments.WispoFragments.WoensdagFragment;
import com.virgiel.lustrumapp.Fragments.WispoFragments.ZondagFragment;

public class PagerAdapterWispo extends FragmentPagerAdapter {

    public PagerAdapterWispo(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ZaterdagFragment tab1 = new ZaterdagFragment();
                return tab1;
            case 1:
                ZondagFragment tab2 = new ZondagFragment();
                return tab2;
            case 2:
                MaandagFragment tab3 = new MaandagFragment();
                return tab3;
            case 3:
                DinsdagFragment tab4 = new DinsdagFragment();
                return tab4;
            case 4:
                WoensdagFragment tab5 = new WoensdagFragment();
                return tab5;
            case 5:
                DonderdagFragment tab6 = new DonderdagFragment();
                return tab6;
            case 6:
                VrijdagFragment tab7 = new VrijdagFragment();
                return tab7;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Za";
            case 1:
                return "Zo";
            case 2:
                return "Ma";
            case 3:
                return "Di";
            case 4:
                return "Wo";
            case 5:
                return "Do";
            case 6:
                return "Vr";
        }
        return null;
    }
}
