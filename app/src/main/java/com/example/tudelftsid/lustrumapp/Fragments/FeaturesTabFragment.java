package com.example.tudelftsid.lustrumapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tudelftsid.lustrumapp.R;

/**
 * Created by TUDelft SID on 5-10-2017.
 */

public class FeaturesTabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.features_tab_layout, container, false);
    }
}
