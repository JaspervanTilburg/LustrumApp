package com.virgiel.lustrumapp.Fragments.TinderFragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appyvet.materialrangebar.RangeBar;
import com.bumptech.glide.Glide;
import com.virgiel.lustrumapp.LustrumRestClient;
import com.virgiel.lustrumapp.Preferences;
import com.virgiel.lustrumapp.Tinder.Profile;
import com.virgiel.lustrumapp.R;
import com.virgiel.lustrumapp.Utils;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.File;

import cz.msebera.android.httpclient.Header;

/**
 * Created by TUDelft SID on 5-10-2017.
 */

public class TinderProfileTabFragment extends Fragment {

    private View rootView;
    private RangeBar rangeBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tinder_profile_tab_layout, container, false);

        requestUserData();

        rangeBar = rootView.findViewById(R.id.rangeBar);
        rangeBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                                              int rightPinIndex, String leftPinValue, String rightPinValue) {
                Preferences.setStartYear(Integer.parseInt(leftPinValue));
                Preferences.setEndYear(Integer.parseInt(rightPinValue));
                Preferences.postPreferences();
            }

        });

        return rootView;
    }

    public void requestUserData() {
        LustrumRestClient.getWithHeader("/profile", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("My Profile loaded: " + response);
                Profile myProfile = Utils.loadProfile(response);
                setTextViews(myProfile);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong " + msg);
                if (statusCode >= 500) {
                    logout();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject object) {
                System.out.println("Something went wrong " + throwable);
                if (statusCode >= 500) {
                    logout();
                }
            }
        });
    }

    private void setTextViews(Profile profile) {
        Typeface body_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/DIN_Bold.ttf");
        Typeface head_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/DIN_Alternate_Bold.ttf");

        ImageView imageView = rootView.findViewById(R.id.myProfileImageView);
        if (!profile.getAvatar().contains("missing")) {
            Glide.with(getContext()).load(LustrumRestClient.BASE_URL + profile.getAvatar()).into(imageView);
        }

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

        CheckBox mannenCheckBox = rootView.findViewById(R.id.mannenCheckBox);
        CheckBox vrouwenCheckBox = rootView.findViewById(R.id.vrouwenCheckBox);
//        RadioButton manButton = rootView.findViewById(R.id.manButton);
//        RadioButton womanButton = rootView.findViewById(R.id.womanButton);

        String interested = profile.getInterestedIn();
        switch (interested) {
            case "M" :
                mannenCheckBox.setChecked(true);
                Preferences.setPreferMen(true);
                break;
            case "V" :
                vrouwenCheckBox.setChecked(true);
                Preferences.setPreferWomen(true);
                break;
            case "B" :
                mannenCheckBox.setChecked(true);
                vrouwenCheckBox.setChecked(true);
                Preferences.setPreferWomen(true);
                Preferences.setPreferMen(true);
                break;
        }

//        if (profile.getGender().equals("M")) {
//            manButton.setChecked(true);
//        } else {
//            womanButton.setChecked(true);
//        }

        if (profile.getInterstedYearBegin() < 2010 || profile.getInterstedYearBegin() > 2017) {
            profile.setInterstedYearBegin(2010);
        }
        if (profile.getInterestedYearEnd() < 2010 || profile.getInterestedYearEnd() > 2017) {
            profile.setInterestedYearEnd(2017);
        }
        rangeBar.setRangePinsByValue(profile.getInterstedYearBegin(), profile.getInterestedYearEnd());

        TextView instellingenTxt = rootView.findViewById(R.id.instellingenTitel);
        //TextView ikBenTxt = rootView.findViewById(R.id.ikBenTxt);
        TextView ikWilZienTxt = rootView.findViewById(R.id.ikWilZienTitel);
        TextView alleenTokoJaren = rootView.findViewById(R.id.alleenTokoTitel);

        //ikBenTxt.setTypeface(head_font);
        instellingenTxt.setTypeface(head_font);
        ikWilZienTxt.setTypeface(head_font);
        alleenTokoJaren.setTypeface(head_font);

        TextView mannen1 = rootView.findViewById(R.id.mannenCheckBox);
        TextView vrouwen1 = rootView.findViewById(R.id.vrouwenCheckBox);
        //TextView mannen2 = rootView.findViewById(R.id.manButton);
        //TextView vrouwen2 = rootView.findViewById(R.id.womanButton);
        TextView jarenBody = rootView.findViewById(R.id.jarenBody);

        mannen1.setTypeface(body_font);
        vrouwen1.setTypeface(body_font);
        //mannen2.setTypeface(body_font);
        //vrouwen2.setTypeface(body_font);
        jarenBody.setTypeface(body_font);
    }

    public void logout() {
        new File(getContext().getFilesDir(), LustrumRestClient.FILE_NAME).delete();
        LustrumRestClient.setToken(null);
        System.out.println("Logged out");
        Toast toast = Toast.makeText(getContext().getApplicationContext(), "LOGGED OUT",Toast.LENGTH_SHORT);
        toast.show();
    }

}
