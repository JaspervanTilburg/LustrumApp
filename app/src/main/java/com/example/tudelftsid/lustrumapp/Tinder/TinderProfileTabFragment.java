package com.example.tudelftsid.lustrumapp.Tinder;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.appyvet.materialrangebar.RangeBar;
import com.example.tudelftsid.lustrumapp.LustrumRestClient;
import com.example.tudelftsid.lustrumapp.Preferences;
import com.example.tudelftsid.lustrumapp.Profile;
import com.example.tudelftsid.lustrumapp.R;
import com.example.tudelftsid.lustrumapp.Utils;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.File;

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

        RangeBar rangeBar = rootView.findViewById(R.id.rangeBar);
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
        LustrumRestClient.getWithHeader("profile", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("My Profile loaded: " + response);
                Profile myProfile = Utils.loadProfile(response);
                setTextViews(myProfile);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong " + msg);
                if (statusCode >= 400 || statusCode <500) {
                    logout();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject object) {
                System.out.println("Something went wrong " + throwable);
                if (statusCode >= 400 || statusCode <500) {
                    logout();
                }
            }
        });
    }

    private void setTextViews(Profile profile) {
        Typeface body_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/DIN_Bold.ttf");
        Typeface head_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/DIN_Alternate_Bold.ttf");

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
        RadioButton manButton = rootView.findViewById(R.id.manButton);
        RadioButton womanButton = rootView.findViewById(R.id.womanButton);
        if (profile.getGender().equals("M")) {
            vrouwenCheckBox.setChecked(true);
            manButton.setChecked(true);
        } else {
            mannenCheckBox.setChecked(true);
            womanButton.setChecked(true);
        }

        TextView instellingenTxt = rootView.findViewById(R.id.instellingenTitel);
        TextView ikBenTxt = rootView.findViewById(R.id.ikBenTxt);
        TextView ikWilZienTxt = rootView.findViewById(R.id.ikWilZienTitel);
        TextView alleenTokoJaren = rootView.findViewById(R.id.alleenTokoTitel);

        ikBenTxt.setTypeface(head_font);
        instellingenTxt.setTypeface(head_font);
        ikWilZienTxt.setTypeface(head_font);
        alleenTokoJaren.setTypeface(head_font);

        TextView mannen1 = rootView.findViewById(R.id.mannenCheckBox);
        TextView vrouwen1 = rootView.findViewById(R.id.vrouwenCheckBox);
        TextView mannen2 = rootView.findViewById(R.id.manButton);
        TextView vrouwen2 = rootView.findViewById(R.id.womanButton);
        TextView jarenBody = rootView.findViewById(R.id.jarenBody);

        mannen1.setTypeface(body_font);
        vrouwen1.setTypeface(body_font);
        mannen2.setTypeface(body_font);
        vrouwen2.setTypeface(body_font);
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
