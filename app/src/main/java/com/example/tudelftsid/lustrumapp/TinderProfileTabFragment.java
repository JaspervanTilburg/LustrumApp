package com.example.tudelftsid.lustrumapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by TUDelft SID on 5-10-2017.
 */

public class TinderProfileTabFragment extends Fragment {

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tinder_profile_tab_layout, container, false);

        requestUserData();

        return rootView;
    }

    public void requestUserData() {
        LustrumRestClient.getWithHeader("profile", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("My Profile loaded: " + response);
                Profile myProfile = Utils.loadProfile(response);
                setTextViews(myProfile);
            }
        });
    }

    private void setTextViews(Profile profile) {
        Typeface body_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/DIN_Bold.ttf");

        TextView nameTxt = rootView.findViewById(R.id.myNameTxt);
        nameTxt.setText(profile.getName().toUpperCase() + " (" + profile.getAge() + ")");
        nameTxt.setTypeface(body_font);

        TextView clubTxt = rootView.findViewById(R.id.myClubTxt);
        clubTxt.setText(profile.getClub().toUpperCase() + " (" + profile.getClubjaar() + ", " + profile.getVerticale().toUpperCase() + ")");
        clubTxt.setTypeface(body_font);

        TextView bolletjesTxt = rootView.findViewById(R.id.myBolletjesTxt);
        bolletjesTxt.setText(profile.getBolletjes() + " BOLLETJES");
        bolletjesTxt.setTypeface(body_font);

        TextView huisTxt = rootView.findViewById(R.id.myHuisTxt);
        huisTxt.setText(profile.getHuis().toUpperCase());
        huisTxt.setTypeface(body_font);

    }

}
