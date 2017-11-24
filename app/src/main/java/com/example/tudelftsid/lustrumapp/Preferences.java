package com.example.tudelftsid.lustrumapp;

import android.content.Intent;

import com.example.tudelftsid.lustrumapp.Activities.TinderMatchActivity;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by TUDelft SID on 18-11-2017.
 */

public class Preferences {

    public static boolean preferMen;
    public static boolean preferWomen;
    public static int startYear;
    public static int endYear;

    public static void setPreferMen(boolean preferMen) {
        Preferences.preferMen = preferMen;
    }

    public static void setPreferWomen(boolean preferWomen) {
        Preferences.preferWomen = preferWomen;
    }

    public static void setStartYear(int startYear) {
        Preferences.startYear = startYear;
    }

    public static void setEndYear(int endYear) {
        Preferences.endYear = endYear;
    }

    public static boolean match(String gender, int year) {
        if (year >= startYear && year <= endYear) {
            if (gender.equals("M") && preferMen) {
                return true;
            } else if (gender.equals("F") && preferWomen) {
                return  true;
            }
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
        setStartYear(2010);
        setEndYear(2017);
        postPreferences();
    }

    public static void postPreferences() {
        LustrumRestClient.postPreferences(getInterestString(), startYear, endYear, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Preferences posted: " + response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong " + throwable);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject msg) {
                System.out.println("Something went wrong with preferences " + msg);
            }
        });
    }

    private static String getInterestString() {
        if (preferWomen && preferMen) {
            return "B";
        } else if (preferWomen) {
            return "V";
        } else if (preferMen) {
            return "M";
        }
        return "B";
    }
}
