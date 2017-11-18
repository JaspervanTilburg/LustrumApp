package com.example.tudelftsid.lustrumapp;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by TUDelft SID on 18-11-2017.
 */

public class Preferences {

    public static boolean preferMen;
    public static boolean preferWomen;

    public static boolean isPreferMen() {
        return preferMen;
    }

    public static void setPreferMen(boolean preferMen) {
        Preferences.preferMen = preferMen;
    }

    public static boolean isPreferWomen() {
        return preferWomen;
    }

    public static void setPreferWomen(boolean preferWomen) {
        Preferences.preferWomen = preferWomen;
    }

    public static boolean match(String gender) {
        if (gender.equals("M") && preferMen) {
            return true;
        } else if (gender.equals("F") && preferWomen) {
            return  true;
        }
        return false;
    }

    public static void initPreferences() {
        LustrumRestClient.getWithHeader("profile", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("My Profile loaded: " + response);
                Profile myProfile = Utils.loadProfile(response);
                if (myProfile.getGender().equals("M")) {
                    setPreferMen(false);
                    setPreferWomen(true);
                } else {
                    setPreferMen(true);
                    setPreferWomen(false);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong " + msg);
            }
        });
    }
}
