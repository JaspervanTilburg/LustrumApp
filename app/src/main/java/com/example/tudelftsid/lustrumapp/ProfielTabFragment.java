package com.example.tudelftsid.lustrumapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tudelftsid.lustrumapp.InfoPages.WispoInfoActivity;

public class ProfielTabFragment extends Fragment {

    private View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (LustrumRestClient.hasToken()) {
            rootview = inflater.inflate(R.layout.profiel_tab_layout, container, false);
        } else {
            rootview = inflater.inflate(R.layout.profiel_login_layout, container, false);
        }
        return rootview;
    }

}