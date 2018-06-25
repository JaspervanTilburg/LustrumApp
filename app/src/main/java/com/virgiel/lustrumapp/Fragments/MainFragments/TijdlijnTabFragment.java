package com.virgiel.lustrumapp.Fragments.MainFragments;

import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.virgiel.lustrumapp.Activities.MainActivity;
import com.virgiel.lustrumapp.LustrumButton;
import com.virgiel.lustrumapp.OnSwipeTouchListener;
import com.virgiel.lustrumapp.R;

import java.util.ArrayList;

/**
 * Created by TUDelft SID on 5-10-2017.
 */

public class TijdlijnTabFragment extends Fragment {

    private LustrumButton selectedButton;
    private ArrayList<LustrumButton> lustrumButtons;
    private View rootView;
    private float width, height;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tijdlijn_tab_layout, container, false);

        this.width = MainActivity.width *0.78f;
        this.height = MainActivity.height *0.69f;

        TextView timeline_txt = (TextView) rootView.findViewById(R.id.timeline_text);
        Typeface head_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        timeline_txt.setTypeface(head_font);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initButtons();
        initSwipe();
        initTitle();
        selectButton(lustrumButtons.get(2));
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
        LustrumButton piekWekenButton = new LustrumButton((ImageView) rootView.findViewById(R.id.piekWekenButton), R.color.lustrumGreen, "PIEKWEEK");

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

        LustrumButton galaInfoButton = new LustrumButton((ImageView) rootView.findViewById(R.id.galaInfoButton), R.color.lustrumPink, width*0.23f, height*0.78f);
        //LustrumButton galaSpelButton = new LustrumButton((ImageView) rootView.findViewById(R.id.galaSpelButton), R.color.lustrumPink, 600, 900);
        LustrumButton galaTinderButton = new LustrumButton((ImageView) rootView.findViewById(R.id.galaTinderButton), R.color.lustrumPink, width*0.47f, height*0.15f);

        LustrumButton wispoInfoButton = new LustrumButton((ImageView) rootView.findViewById(R.id.wispoInfoButton), R.color.lustrumPink, width*0.59f, height*0.78f);
        LustrumButton wispoProgrammaButton = new LustrumButton((ImageView) rootView.findViewById(R.id.wispoProgrammaButton), R.color.lustrumPink, width*0.75f, height*0.1f);
        //LustrumButton wispoSnelheidsmeterButton = new LustrumButton((ImageView) rootView.findViewById(R.id.wispoSnelheidsmeterButton), R.color.lustrumPink, 700, 200);

        LustrumButton lustrumWeekInfoButton = new LustrumButton((ImageView) rootView.findViewById(R.id.lustrumWeekInfoButton), R.color.lustrumPink, width*0.47f, height*0.78f);

        LustrumButton pintenVangenButton = new LustrumButton((ImageView) rootView.findViewById(R.id.pintenVangenButton), R.color.lustrumPink, width*0.2f, height*0.2f);
        LustrumButton piekWekenInfoButton = new LustrumButton((ImageView) rootView.findViewById(R.id.piekWekenInfoButton), R.color.lustrumPink, width*0.47f, height*0.78f);

        galaButton.addChildButton(galaInfoButton);
        //galaButton.addChildButton(galaSpelButton);
        galaButton.addChildButton(galaTinderButton);

        wispoButton.addChildButton(wispoInfoButton);
        wispoButton.addChildButton(wispoProgrammaButton);
        //wispoButton.addChildButton(wispoSnelheidsmeterButton);

        lustrumWeekButton.addChildButton(lustrumWeekInfoButton);

        piekWekenButton.addChildButton(pintenVangenButton);
        piekWekenButton.addChildButton(piekWekenInfoButton);

        lustrumButtons.add(galaInfoButton);
        //lustrumButtons.add(galaSpelButton);
        lustrumButtons.add(galaTinderButton);
        lustrumButtons.add(wispoInfoButton);
        lustrumButtons.add(wispoProgrammaButton);
        //lustrumButtons.add(wispoSnelheidsmeterButton);
        lustrumButtons.add(lustrumWeekInfoButton);
        lustrumButtons.add(pintenVangenButton);
        lustrumButtons.add(piekWekenInfoButton);

        selectedButton = lustrumButtons.get(1);
        selectedButton.animateExpand();
        selectedButton.animateScaleUp();
        selectedButton.setName(getActivity());
    }

    public void initSwipe() {
        View.OnTouchListener listener = new OnSwipeTouchListener(getActivity()) {
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
        };
        rootView.findViewById(R.id.activiteitenBackground).setOnTouchListener(listener);
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
