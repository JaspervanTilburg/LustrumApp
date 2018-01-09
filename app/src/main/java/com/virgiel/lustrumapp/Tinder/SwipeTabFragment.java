package com.virgiel.lustrumapp.Tinder;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.virgiel.lustrumapp.LustrumRestClient;
import com.virgiel.lustrumapp.Profile;
import com.virgiel.lustrumapp.R;
import com.virgiel.lustrumapp.Tinder.TinderCard;
import com.virgiel.lustrumapp.Utils;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import org.json.JSONObject;

import java.io.File;

import cz.msebera.android.httpclient.Header;

/**
 * Created by TUDelft SID on 5-10-2017.
 */

public class SwipeTabFragment extends Fragment {

    public static final int INITIAL_TINDER_CARDS = 3;

    private View rootView;
    private SwipePlaceHolderView mSwipeView;
    private Context mContext;
    private Typeface body_font;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.swipe_tab_layout, container, false);

        mSwipeView = rootView.findViewById(R.id.swipeView);
        mContext = getActivity().getApplicationContext();
        body_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/DIN_Bold.ttf");

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_accept_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_reject_view));

        for (int i = 0; i < INITIAL_TINDER_CARDS; i++) {
            getRandomUser();
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

    public void logout() {
        new File(mContext.getFilesDir(), LustrumRestClient.FILE_NAME).delete();
        LustrumRestClient.setToken(null);
        System.out.println("Logged out");
        Toast toast = Toast.makeText(mContext.getApplicationContext(), "LOGGED OUT",Toast.LENGTH_SHORT);
        toast.show();
    }

    public void getRandomUser() {
        LustrumRestClient.getWithHeader("queue", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Profile profile = Utils.loadProfile(response);
                System.out.println("Profile " + profile.getId() + " queued: " + response);
                mSwipeView.addView(new TinderCard(mContext, profile, mSwipeView, body_font));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong " + msg);
                if (statusCode >= 400 || statusCode <500) {
                    logout();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable msg, JSONObject object) {
                System.out.println("Something went wrong with queueu" + msg);
                if (statusCode >= 400 || statusCode <500) {
                    logout();
                }
            }
        });
    }
}
