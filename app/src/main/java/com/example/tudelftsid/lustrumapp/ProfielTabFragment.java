package com.example.tudelftsid.lustrumapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.tudelftsid.lustrumapp.InfoPages.WispoInfoActivity;

import org.w3c.dom.Text;

public class ProfielTabFragment extends Fragment {

    private View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Typeface head_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        Typeface body_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/DIN_Bold.ttf");

        Window profileWindow = this.getActivity().getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            profileWindow.setStatusBarColor(this.getResources().getColor(R.color.lustrumGrey));
        }

        if (LustrumRestClient.hasToken()) {
            rootview = inflater.inflate(R.layout.profiel_tab_layout, container, false);

            TextView welkomTxt = (TextView) rootview.findViewById(R.id.welkomTextView);
            TextView logOutButtonTxt = (TextView) rootview.findViewById(R.id.logOutButton);

            welkomTxt.setTypeface(head_font);
            logOutButtonTxt.setTypeface(body_font);
        } else {
            rootview = inflater.inflate(R.layout.profiel_login_layout, container, false);
            TextView logInButtonTxt = (TextView) rootview.findViewById(R.id.logInButton);
            logInButtonTxt.setTypeface(body_font);
        }
        return rootview;
    }

}