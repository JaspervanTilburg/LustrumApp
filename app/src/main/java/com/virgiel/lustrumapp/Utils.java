package com.virgiel.lustrumapp;

import android.content.Context;

import com.virgiel.lustrumapp.DateSpel.DateQuestion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.virgiel.lustrumapp.Tinder.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TUDelft SID on 10-11-2017.
 */


public class Utils {

    private static final String TAG = "Utils";

    public static List<DateQuestion> loadDateQuestions(Context context, JSONArray jsonArray) throws JSONException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<DateQuestion> questionsList = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            DateQuestion question = gson.fromJson(jsonArray.getString(i), DateQuestion.class);
            questionsList.add(question);
        }
        return questionsList;
    }

    public static Profile loadProfile(JSONObject object) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Profile profile = gson.fromJson(object.toString(), Profile.class);
        return profile;
    }

    public static List<Profile> loadProfiles(JSONObject response){
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            List<Profile> profileList = new ArrayList<>();
            JSONArray array = (JSONArray) response.get("matches");
            for(int i=0;i<array.length();i++){
                JSONObject match = (JSONObject) array.get(i);
                JSONObject user = (JSONObject) match.get("user");
                Profile profile = gson.fromJson(user.toString(), Profile.class);
                profile.setMatchCreatedAt(match.getString("created_at"));
                profileList.add(profile);
            }
            return profileList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static List<Selfie> loadSelfies(JSONObject response) {
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            List<Selfie> selfieList = new ArrayList<>();
            JSONArray array = (JSONArray) response.get("selfies");
            for(int i=0;i<array.length();i++){
                JSONObject selfieObj = (JSONObject) array.get(i);
                Selfie selfie = gson.fromJson(selfieObj.toString(), Selfie.class);
                selfieList.add(selfie);
            }
            return selfieList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
