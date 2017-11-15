package com.example.tudelftsid.lustrumapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rolf on 15-11-2017.
 */

public class UtilsDateSpel {
    private static final String TAG = "UtilsDateSpel";

    public static List<DateSpelVraag> loadCards(Context context){
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JSONArray array = new JSONArray(loadJSONFromAsset(context, "datespel.json"));
            List<DateSpelVraag> vraagList = new ArrayList<>();
            for(int i=0;i<array.length();i++){
                DateSpelVraag card = gson.fromJson(array.getString(i), DateSpelVraag.class);
                vraagList.add(card);
            }
            return vraagList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static String loadJSONFromAsset(Context context, String jsonFileName) {
        String json = null;
        InputStream is=null;
        try {
            AssetManager manager = context.getAssets();
            Log.d(TAG,"path "+jsonFileName);
            is = manager.open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
