package com.example.tudelftsid.lustrumapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by TUDelft SID on 5-10-2017.
 */

public class ActiviteitenTabFragment extends Fragment {

    private LustrumButton selectedButton;
    private ArrayList<LustrumButton> lustrumButtons;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activiteiten_tab_layout, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initButtons();
        initSwipe();
    }

    public void initButtons() {

        lustrumButtons = new ArrayList<>();
        LustrumButton galaButton = new LustrumButton((ImageView) rootView.findViewById(R.id.galaButton), R.color.lustrumPink);
        LustrumButton wispoButton = new LustrumButton((ImageView) rootView.findViewById(R.id.wispoButton), R.color.lustrumLightBlue);
        LustrumButton lustrumWeekButton = new LustrumButton((ImageView) rootView.findViewById(R.id.lustrumWeekButton), R.color.lustrumBlue);
        LustrumButton piekWekenButton = new LustrumButton((ImageView) rootView.findViewById(R.id.piekWekenButton), R.color.lustrumGreen);

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

        galaButton.addChildButton(new LustrumButton((ImageView) rootView.findViewById(R.id.galaInfoButton), R.color.lustrumPink));
        galaButton.addChildButton(new LustrumButton((ImageView) rootView.findViewById(R.id.galaFotosButton), R.color.lustrumPink));
        galaButton.addChildButton(new LustrumButton((ImageView) rootView.findViewById(R.id.galaTinderButton), R.color.lustrumPink));

        selectedButton = lustrumButtons.get(0);
        selectedButton.animateExpand();
        selectedButton.animateScaleUp();
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
        System.out.println(size + ", " + lustrumButtons.get(0).getImage().getLayoutParams().width);
        if (!selectedButton.equals(button)) {
            selectedButton.animateScaleDown();
            selectedButton.animateCollapse();
            button.animateScaleUp();
            button.animateExpand();
            selectedButton = button;
            button.setBackgroundColor(getActivity());
        }
    }

}
