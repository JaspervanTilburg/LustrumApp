package com.example.tudelftsid.lustrumapp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.graphics.Typeface;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by TUDelft SID on 10-11-2017.
 */

@NonReusable
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
        postLike();
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
                Profile profile = Utils.loadProfile(response);
                System.out.println("Profile " + profile.getId() + " queued: " + response);
                if (Preferences.match(profile.getGender(), profile.getClubjaar())) {
                    mSwipeView.addView(new TinderCard(mContext, profile, mSwipeView, mFont));
                } else {
                    addTinderCard();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong " + msg);
            }
        });
    }

    public void postLike() {
        LustrumRestClient.postLike(mProfile.getId(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Like posted: " + response);
                try {
                    if ((boolean) response.get("is_match")) {
                        Intent intent = new Intent(mContext, TinderMatchActivity.class);
                        intent.putExtra("name", mProfile.getName());
                        mContext.startActivity(intent);
                    }
                    addTinderCard();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong " + msg);
                if (statusCode >= 400 || statusCode <500) {
                    logout();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject msg) {
                System.out.println("Something went wrong with queue " + msg);
            }
        });
    }

    public void logout() {
        new File(mContext.getFilesDir(), LustrumRestClient.FILE_NAME).delete();
        LustrumRestClient.setToken(null);
        System.out.println("Logged out");
        Toast toast = Toast.makeText(mContext.getApplicationContext(), "LOGGED OUT",Toast.LENGTH_SHORT);
        toast.show();
    }
}
