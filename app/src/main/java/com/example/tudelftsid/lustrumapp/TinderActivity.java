package com.example.tudelftsid.lustrumapp;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.ArrayList;

public class TinderActivity extends AppCompatActivity {

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinder);

        mSwipeView = (SwipePlaceHolderView)findViewById(R.id.swipeView);
        mContext = getApplicationContext();

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_accept_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_reject_view));


        for(Profile profile : Utils.loadProfiles(this.getApplicationContext())){
            mSwipeView.addView(new TinderCard(mContext, profile, mSwipeView));
        }

        findViewById(R.id.dislikeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        findViewById(R.id.likeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });

        TextView name = (TextView) findViewById(R.id.nameAgeTxt);
        TextView club = (TextView) findViewById(R.id.clubTxt);
        TextView bolletjes = (TextView) findViewById(R.id.bolletjesTxt);
        TextView huis = (TextView) findViewById(R.id.huisTxt);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        name.setTypeface(font);
        club.setTypeface(font);
        bolletjes.setTypeface(font);
        huis.setTypeface(font);
    }
}
