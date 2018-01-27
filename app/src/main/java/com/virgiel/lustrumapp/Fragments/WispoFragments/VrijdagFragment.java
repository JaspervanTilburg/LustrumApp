package com.virgiel.lustrumapp.Fragments.WispoFragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.virgiel.lustrumapp.R;

/**
 * Created by TUDelft SID on 5-10-2017.
 */

public class VrijdagFragment extends Fragment {

    private View rootView;
    private Typeface body_font;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.vrijdag_tab_layout, container, false);
        body_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/DIN_Bold.ttf");

        LinearLayout myLayout = (LinearLayout) rootView.findViewById(R.id.vrijdag_text);
        for (int i = 0; i < myLayout.getChildCount(); i++) {
            if (myLayout.getChildAt(i) instanceof TextView) {
                ((TextView) myLayout.getChildAt(i)).setTypeface(body_font);
            }
        }
        return rootView;
    }
}
