package com.example.tudelftsid.lustrumapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by TUDelft SID on 5-10-2017.
 */

public class SwipeTabFragment extends Fragment {

    public static final int INITIAL_TINDER_CARDS = 10;

    private View rootView;
    private SwipePlaceHolderView mSwipeView;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.swipe_tab_layout, container, false);

        mSwipeView = rootView.findViewById(R.id.swipeView);
        mContext = getActivity().getApplicationContext();

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_accept_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_reject_view));

        for (int i = 0; i < INITIAL_TINDER_CARDS; i++) {
            LustrumRestClient.getWithHeader("queue", null, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    System.out.println("Profile queued: " + response);
                    Profile profile = Utils.loadProfile(response);
                    mSwipeView.addView(new TinderCard(mContext, profile, mSwipeView));
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                    System.out.println("Something went wrong " + msg);
                }
            });
        }

        rootView.findViewById(R.id.dislikeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        rootView.findViewById(R.id.likeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });

        return rootView;
    }

}
