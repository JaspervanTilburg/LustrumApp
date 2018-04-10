package com.virgiel.lustrumapp.Tinder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.virgiel.lustrumapp.Activities.TinderMatchActivity;
import com.virgiel.lustrumapp.LustrumRestClient;
import com.virgiel.lustrumapp.R;
import com.virgiel.lustrumapp.Utils;
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

import cz.msebera.android.httpclient.Header;

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

        if (!mProfile.getAvatar().contains("missing")) {
            Glide.with(mContext).load(LustrumRestClient.BASE_URL + mProfile.getAvatar()).into(profileImageView);
        }
        nameAgeTxt.setText(mProfile.getName().toUpperCase() + ", " + mProfile.getAge());
        clubTxt.setText(mProfile.getClub().toUpperCase());
        if (mProfile.getVerticale() != null) {
            clubTxt.append(", " + mProfile.getVerticale().toUpperCase());
        }
        if (mProfile.getClub().toUpperCase().equals("ATHOS")) {
            clubTxt.append(" BRAVO!");
        }
        clubTxt.append(" (" + mProfile.getClubjaar() + ")");
        if (mProfile.getBolletjes() != null) {
            bolletjesTxt.setText(mProfile.getBolletjes() + " BOLLETJES");
        } else {
            bolletjesTxt.setVisibility(TextView.INVISIBLE);
        }
        if (!mProfile.getHuis().equals("") && mProfile.getHuis() != null) {
            huisTxt.setText(mProfile.getHuis().toUpperCase());
            if (mProfile.getHuis().toUpperCase().contains("JACOBA VAN BEIERENLAAN 49") || mProfile.getHuis().toUpperCase().contains("BOTTE BIJL")) {
                huisTxt.append(" BRAVO!");
            }
        } else {
            huisTxt.setVisibility(TextView.INVISIBLE);
        }
    }

    @SwipeOut
    private void onSwipedOut(){
        postDislike();
        addTinderCard();
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
    }

    @SwipeIn
    private void onSwipeIn(){
        postLike();
        addTinderCard();
    }

    @SwipeInState
    private void onSwipeInState(){
    }

    @SwipeOutState
    private void onSwipeOutState(){
    }

    public void addTinderCard() {
        final SwipePlaceHolderView newSwipeView = mSwipeView;
        final Context newContext = mContext;
        final Typeface newFont = mFont;
        LustrumRestClient.getWithHeader("queue", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Profile profile = Utils.loadProfile(response);
                System.out.println("Profile (addTinderCard) " + profile.getId() + " queued: " + response);
                System.out.println("Context: " + newContext + ", swipeview: " + newSwipeView + ", font: " + newFont);
                newSwipeView.addView(new TinderCard(newContext, profile, newSwipeView, newFont));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong " + msg);
            }
        });
    }

    public void postLike() {
        final Context newContext = mContext;
        LustrumRestClient.postLike(mProfile.getId(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Like posted: " + response);
                try {
                    if ((boolean) response.get("is_match")) {
                        Intent intent = new Intent(newContext, TinderMatchActivity.class);
                        intent.putExtra("name", (String) ((JSONObject) response.get("user")).get("name"));
                        intent.putExtra("image", (String) ((JSONObject) response.get("user")).get("avatar"));
                        intent.putExtra("phone", (String) ((JSONObject) response.get("user")).get("phone_number"));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        newContext.startActivity(intent);
                    }
//                    addTinderCard();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong " + msg);
                if (statusCode >= 500) {
                    logout();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject msg) {
                System.out.println("Something went wrong with queue " + msg);
            }
        });
    }

    public void postDislike() {
        LustrumRestClient.postDisLike(mProfile.getId(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Dislike posted: " + response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong " + msg);
                if (statusCode >= 500) {
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
