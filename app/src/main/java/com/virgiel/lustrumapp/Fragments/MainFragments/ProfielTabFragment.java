package com.virgiel.lustrumapp.Fragments.MainFragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.virgiel.lustrumapp.LustrumRestClient;
import com.virgiel.lustrumapp.Profile;
import com.virgiel.lustrumapp.PushnotificationSettings;
import com.virgiel.lustrumapp.R;
import com.virgiel.lustrumapp.Utils;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class ProfielTabFragment extends Fragment {

    private View rootview;
    private boolean algemeenNotifications, galaNotifications, wispoNotifications, lustrumweekNotifications,
            piekweekNotifications, tinderNotifications;
    private SwitchCompat algemeenSwitch, galaSwitch, wispoSwitch, lustrumSwitch, piekSwitch, tinderSwitch;

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

            getSwitches();
            loadPushNotificationSettings();
            initSwitches();
            setTextViewFontsAndColors(head_font, body_font);


        } else {
            rootview = inflater.inflate(R.layout.profiel_login_layout, container, false);
            TextView logInButtonTxt = (TextView) rootview.findViewById(R.id.logInButton);
            logInButtonTxt.setTypeface(body_font);
        }
        return rootview;
    }

    private void setTextViewFontsAndColors(Typeface head_font, Typeface body_font) {
        final TextView welcomeTxt = (TextView) rootview.findViewById(R.id.welkomTextView);
        makeWelcomePersonal(welcomeTxt);
        final TextView algemeenTxt = (TextView) rootview.findViewById(R.id.algemeenNotificatieTextView);
        final TextView pushTxt = (TextView) rootview.findViewById(R.id.pushNotificatieTextView);
        final TextView galaTxt = (TextView) rootview.findViewById(R.id.galaNotificatieTextView);
        final TextView wispoTxt = (TextView) rootview.findViewById(R.id.wispoNotificatieTextView);
        final TextView lustrumTxt = (TextView) rootview.findViewById(R.id.lustrumweekNotificatieTextView);
        final TextView piekTxt = (TextView) rootview.findViewById(R.id.piekweekNotificatieTextView);
        final TextView tinderTxt = (TextView) rootview.findViewById(R.id.tinderNotificatieTextView);
        TextView logOutButtonTxt = (TextView) rootview.findViewById(R.id.logOutButton);
        pushTxt.setTypeface(head_font);
        algemeenTxt.setTypeface(body_font);
        galaTxt.setTypeface(body_font);
        wispoTxt.setTypeface(body_font);
        lustrumTxt.setTypeface(body_font);
        piekTxt.setTypeface(body_font);
        tinderTxt.setTypeface(body_font);
        welcomeTxt.setTypeface(head_font);
        logOutButtonTxt.setTypeface(body_font);

        algemeenTxt.setTextColor(Color.WHITE);
        galaTxt.setTextColor(getResources().getColor(R.color.lustrumPink_Light));
        wispoTxt.setTextColor(getResources().getColor(R.color.lustrumLightBlue_Light));
        lustrumTxt.setTextColor(getResources().getColor(R.color.lustrumBlue_Light));
        piekTxt.setTextColor(getResources().getColor(R.color.lustrumGreen_Light));
        tinderTxt.setTextColor(getResources().getColor(R.color.lustrumPink_Light));
    }

    private void getSwitches() {
        algemeenSwitch = rootview.findViewById(R.id.algemeenNotificatieSwitch);
        galaSwitch = rootview.findViewById(R.id.galaNotificatieSwitch);
        wispoSwitch = rootview.findViewById(R.id.wispoNotificatieSwitch);
        lustrumSwitch = rootview.findViewById(R.id.lustrumweekNotificatieSwitch);
        piekSwitch = rootview.findViewById(R.id.piekweekNotificatieSwitch);
        tinderSwitch = rootview.findViewById(R.id.tinderNotificatieSwitch);
    }

    private void initSwitches() {
        algemeenSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PushnotificationSettings.setAlgemeenNotifications(isChecked);
            }
        });
        galaSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    PushnotificationSettings.setGalaNotifications(isChecked);
            }
        });
        wispoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PushnotificationSettings.setWispoNotifications(isChecked);
            }
        });
        lustrumSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PushnotificationSettings.setLustrumweekNotifications(isChecked);
            }
        });
        piekSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PushnotificationSettings.setPiekweekNotifications(isChecked);
            }
        });
        tinderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PushnotificationSettings.setTinderNotifications(isChecked);
            }
        });
    }

    private void loadPushNotificationSettings() {
        algemeenNotifications = PushnotificationSettings.getAlgemeenNotifications();
        galaNotifications = PushnotificationSettings.getGalaNotifications();
        wispoNotifications = PushnotificationSettings.getWispoNotifications();
        lustrumweekNotifications = PushnotificationSettings.getLustrumweekNotifications();
        piekweekNotifications = PushnotificationSettings.getPiekweekNotifications();
        tinderNotifications = PushnotificationSettings.getTinderNotifications();

        algemeenSwitch.setChecked(algemeenNotifications);
        galaSwitch.setChecked(galaNotifications);
        wispoSwitch.setChecked(wispoNotifications);
        lustrumSwitch.setChecked(lustrumweekNotifications);
        piekSwitch.setChecked(piekweekNotifications);
        tinderSwitch.setChecked(tinderNotifications);
    }

    private void makeWelcomePersonal(final TextView welcomeTxt) {
        LustrumRestClient.getWithHeader("profile", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Profile myProfile = Utils.loadProfile(response);
                String welcomeString = welcomeTxt.getText().toString();
                welcomeString += ", " + myProfile.getName().toUpperCase();
                welcomeTxt.setText(welcomeString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong " + msg);
            }
        });
    }

}