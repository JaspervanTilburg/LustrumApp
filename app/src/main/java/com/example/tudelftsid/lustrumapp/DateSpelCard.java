package com.example.tudelftsid.lustrumapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.TextView;

import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

/**
 * Created by Rolf on 15-11-2017.
 */

@Layout(R.layout.datespel_card_layout)
public class DateSpelCard {

    @View(R.id.datespel_uppertext)
    private TextView upperText;

    private Context mContext;
    private SwipePlaceHolderView mSwipeView;
    private DateQuestion mVraag;
    private Typeface mFont;

    public DateSpelCard(Context context, SwipePlaceHolderView swipeView, DateQuestion vraag, Typeface font) {
        mContext = context;
        mSwipeView = swipeView;
        mVraag = vraag;
        mFont = font;
    }

    @Resolve
    private void onResolved(){
        upperText.setTypeface(mFont);
        upperText.setText(mVraag.getQuestion());
    }

    @SwipeOut
    private void onSwipedOut(){
        Log.d("EVENT", "onSwipedOut");
        mSwipeView.addView(this);
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn(){
        Log.d("EVENT", "onSwipedIn");
    }

    @SwipeInState
    private void onSwipeInState(){
        Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState(){
        Log.d("EVENT", "onSwipeOutState");
    }


}
