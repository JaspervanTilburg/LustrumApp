package com.example.tudelftsid.lustrumapp;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by TUDelft SID on 10-11-2017.
 */


@Layout(R.layout.tinder_card_view)
public class TinderCard {

    @View(R.id.profileImageView)
    private ImageView profileImageView;

    @View(R.id.nameAgeTxt)
    private TextView nameAgeTxt;

    @View(R.id.clubTxt)
    private TextView clubTxt;

    @View(R.id.bolletjesTxt)
    private TextView bolletjesTxt;

    @View(R.id.huisTxt)
    private TextView huisTxt;

    private Profile mProfile;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;
    private Typeface mFont;

    public TinderCard(Context context, Profile profile, SwipePlaceHolderView swipeView, Typeface font) {
        mContext = context;
        mProfile = profile;
        mSwipeView = swipeView;
        mFont = font;
    }

    @Resolve
    private void onResolved(){
        nameAgeTxt.setTypeface(mFont);
        clubTxt.setTypeface(mFont);
        bolletjesTxt.setTypeface(mFont);
        huisTxt.setTypeface(mFont);

        Glide.with(mContext).load(mProfile.getImageUrl()).into(profileImageView);
        nameAgeTxt.setText(mProfile.getName().toUpperCase() + ", " + mProfile.getAge());
        clubTxt.setText(mProfile.getClub().toUpperCase());
        bolletjesTxt.setText(mProfile.getBolletjes() + " BOLLETJES");
        huisTxt.setText(mProfile.getHuis().toUpperCase());
    }

    @SwipeOut
    private void onSwipedOut(){
        Log.d("EVENT", "onSwipedOut");
        addTinderCard();
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn(){
        Log.d("EVENT", "onSwipedIn");
        addTinderCard();
    }

    @SwipeInState
    private void onSwipeInState(){
        Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState(){
        Log.d("EVENT", "onSwipeOutState");
    }

    public void addTinderCard() {
        LustrumRestClient.getWithHeader("queue", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Object " + response);
                Profile profile = Utils.loadProfile(response);
                mSwipeView.addView(new TinderCard(mContext, profile, mSwipeView, mFont));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong " + msg);
            }
        });
    }
}
