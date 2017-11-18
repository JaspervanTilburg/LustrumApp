package com.example.tudelftsid.lustrumapp;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

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
        });
    }

    private void setTextViews(Profile profile) {
        TextView nameTxt = rootView.findViewById(R.id.myNameTxt);
        nameTxt.setText(profile.getName().toUpperCase() + " (" + profile.getAge() + ")");
        TextView clubTxt = rootView.findViewById(R.id.myClubTxt);
        clubTxt.setText(profile.getClub().toUpperCase() + " (" + profile.getClubjaar() + ", " + profile.getVerticale().toUpperCase() + ")");
        TextView bolletjesTxt = rootView.findViewById(R.id.myBolletjesTxt);
        bolletjesTxt.setText(profile.getBolletjes() + " BOLLETJES");
        TextView huisTxt = rootView.findViewById(R.id.myHuisTxt);
        huisTxt.setText(profile.getHuis().toUpperCase());
        CheckBox mannenCheckBox = rootView.findViewById(R.id.mannenCheckBox);
        CheckBox vrouwenCheckBox = rootView.findViewById(R.id.vrouwenCheckBox);
        if (profile.getGender().equals("M")) {
            vrouwenCheckBox.setChecked(true);
        } else {
            mannenCheckBox.setChecked(true);
        }
    }

    public void logout() {
        new File(getContext().getFilesDir(), LustrumRestClient.FILE_NAME).delete();
        LustrumRestClient.setToken(null);
        System.out.println("Logged out");
        Toast toast = Toast.makeText(getContext().getApplicationContext(), "LOGGED OUT",Toast.LENGTH_SHORT);
        toast.show();
    }

}
