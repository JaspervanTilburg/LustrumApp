package com.example.tudelftsid.lustrumapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by TUDelft SID on 5-10-2017.
 */

public class TijdlijnTabFragment extends Fragment {

    private LustrumButton selectedButton;
    private ArrayList<LustrumButton> lustrumButtons;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tijdlijn_tab_layout, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initButtons();
        initSwipe();
        initTitle();
    }

    public void initTitle() {
        TextView title = (TextView) rootView.findViewById(R.id.timeline_text);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        title.setTypeface(font);
    }

    public void initButtons() {

        lustrumButtons = new ArrayList<>();
        LustrumButton galaButton = new LustrumButton((ImageView) rootView.findViewById(R.id.galaButton), R.color.lustrumPink, "GALA");
        LustrumButton wispoButton = new LustrumButton((ImageView) rootView.findViewById(R.id.wispoButton), R.color.lustrumLightBlue, "WISPO");
        LustrumButton lustrumWeekButton = new LustrumButton((ImageView) rootView.findViewById(R.id.lustrumWeekButton), R.color.lustrumBlue, "LUSTRUMWEEK");
        LustrumButton piekWekenButton = new LustrumButton((ImageView) rootView.findViewById(R.id.piekWekenButton), R.color.lustrumGreen, "PIEKWEKEN");

        galaButton.getImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LustrumButton button = lustrumButtons.get(0);
                selectButton(button);
            }
        });
        wispoButton.getImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LustrumButton button = lustrumButtons.get(1);
                selectButton(button);
            }
        });
        lustrumWeekButton.getImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LustrumButton button = lustrumButtons.get(2);
                selectButton(button);
            }
        });
        piekWekenButton.getImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LustrumButton button = lustrumButtons.get(3);
                selectButton(button);
            }
        });

        lustrumButtons.add(galaButton);
        lustrumButtons.add(wispoButton);
        lustrumButtons.add(lustrumWeekButton);
        lustrumButtons.add(piekWekenButton);

        LustrumButton galaInfoButton = new LustrumButton((ImageView) rootView.findViewById(R.id.galaInfoButton), R.color.lustrumPink, 200, 1100);
        LustrumButton galaSpelButton = new LustrumButton((ImageView) rootView.findViewById(R.id.galaSpelButton), R.color.lustrumPink, 600, 900);
        LustrumButton galaTinderButton = new LustrumButton((ImageView) rootView.findViewById(R.id.galaTinderButton), R.color.lustrumPink, 400, 200);
        LustrumButton wispoInfoButton = new LustrumButton((ImageView) rootView.findViewById(R.id.wispoInfoButton), R.color.lustrumPink, 500, 1000);
        LustrumButton lustrumWeekInfoButton = new LustrumButton((ImageView) rootView.findViewById(R.id.lustrumWeekInfoButton), R.color.lustrumPink, 400, 1000);
        LustrumButton piekWekenInfoButton = new LustrumButton((ImageView) rootView.findViewById(R.id.piekWekenInfoButton), R.color.lustrumPink, 500, 1000);

        galaButton.addChildButton(galaInfoButton);
        galaButton.addChildButton(galaSpelButton);
        galaButton.addChildButton(galaTinderButton);

        wispoButton.addChildButton(wispoInfoButton);
        lustrumWeekButton.addChildButton(lustrumWeekInfoButton);
        piekWekenButton.addChildButton(piekWekenInfoButton);

        selectedButton = lustrumButtons.get(0);
        selectedButton.animateExpand();
        selectedButton.animateScaleUp();
        selectedButton.setName(getActivity());
    }

    public void initSwipe() {
        rootView.findViewById(R.id.activiteitenBackground).setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            public void onSwipeTop() {
            }
            public void onSwipeRight() {
                int index = lustrumButtons.indexOf(selectedButton);
                if (index != 0) {
                    selectButton(lustrumButtons.get(index - 1));
                }
            }
            public void onSwipeLeft() {
                int index = lustrumButtons.indexOf(selectedButton);
                if (index != 3) {
                    selectButton(lustrumButtons.get(index + 1));
                }
            }
            public void onSwipeBottom() {
            }
        });
    }

    public void selectButton(LustrumButton button) {
        int size = lustrumButtons.get(0).getImage().getWidth();
        if (!selectedButton.equals(button)) {
            selectedButton.animateCollapse();
            selectedButton.animateScaleDown();
            button.animateExpand();
            button.animateScaleUp();
            selectedButton = button;
            button.setBackgroundColor(getActivity());
            button.setName(getActivity());
        }
    }

}
