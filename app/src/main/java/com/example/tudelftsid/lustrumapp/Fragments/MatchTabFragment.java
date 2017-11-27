package com.example.tudelftsid.lustrumapp.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tudelftsid.lustrumapp.LustrumRestClient;
import com.example.tudelftsid.lustrumapp.Profile;
import com.example.tudelftsid.lustrumapp.R;
import com.example.tudelftsid.lustrumapp.Tinder.TinderMatchAdapter;
import com.example.tudelftsid.lustrumapp.Utils;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.File;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by TUDelft SID on 5-10-2017.
 */

public class MatchTabFragment extends Fragment {

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.match_tab_layout, container, false);

        LustrumRestClient.getWithHeader("matches", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Matches Retrieved: " + response);
                List<Profile> profiles = Utils.loadProfiles(response);
                Typeface body_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/DIN_Bold.ttf");
                TinderMatchAdapter adapter = new TinderMatchAdapter(getContext(), profiles, body_font);
                ListView itemsListView  = (ListView) rootView.findViewById(R.id.matchListView);
                itemsListView.setAdapter(adapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong, statuscode: " + statusCode + ", " + msg);
                if (statusCode >= 400 || statusCode <500) {
                    logout();
                }
            }
        });

        return rootView;
    }

    public void logout() {
        new File(getContext().getFilesDir(), LustrumRestClient.FILE_NAME).delete();
        LustrumRestClient.setToken(null);
        System.out.println("Logged out");
        Toast toast = Toast.makeText(getContext().getApplicationContext(), "LOGGED OUT",Toast.LENGTH_SHORT);
        toast.show();
    }
}
