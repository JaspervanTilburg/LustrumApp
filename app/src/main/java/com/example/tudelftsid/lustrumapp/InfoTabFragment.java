package com.example.tudelftsid.lustrumapp;

import android.graphics.Typeface;
import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.TextView;

public class InfoTabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View res = inflater.inflate(R.layout.info_tab_layout, container, false);

        TextView titelTxt = (TextView) res.findViewById(R.id.textView);
        Typeface head_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        titelTxt.setTypeface(head_font);

        return res;
    }
}