package com.example.tudelftsid.lustrumapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by TUDelft SID on 5-10-2017.
 */

public class ActiviteitenTabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activiteiten_tab_layout, container, false);
        initButtons(rootView);
        return rootView;
    }

    public void initButtons(View view) {
        LustrumButton galaButton = (LustrumButton) view.findViewById(R.id.galaButton);
        LustrumButton wispoButton = (LustrumButton) view.findViewById(R.id.wispoButton);
        LustrumButton lustrumWeekButton = (LustrumButton) view.findViewById(R.id.lustrumWeekButton);
        LustrumButton piekWekenButton = (LustrumButton) view.findViewById(R.id.piekWekenButton);

        ((MainActivity)getActivity()).setSelectedButton(galaButton);

        galaButton.addChildButton((LustrumButton) view.findViewById(R.id.galaInfoButton));
        galaButton.addChildButton((LustrumButton) view.findViewById(R.id.galaFotosButton));
        galaButton.addChildButton((LustrumButton) view.findViewById(R.id.galaTinderButton));
        galaButton.setNext(wispoButton);
        galaButton.setColor(Color.parseColor("#e51473"));

        wispoButton.setNext(lustrumWeekButton);
        wispoButton.setPrevious(galaButton);
        wispoButton.setColor(Color.parseColor("#1e9bd7"));

        lustrumWeekButton.setNext(piekWekenButton);
        lustrumWeekButton.setPrevious(wispoButton);
        lustrumWeekButton.setColor(Color.parseColor("#2d2c86"));

        piekWekenButton.setPrevious(lustrumWeekButton);
        piekWekenButton.setColor(Color.parseColor("#2db198"));
    }
}
