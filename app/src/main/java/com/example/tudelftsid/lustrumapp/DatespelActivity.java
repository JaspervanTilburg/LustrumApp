package com.example.tudelftsid.lustrumapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatespelActivity extends AppCompatActivity {

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        setContentView(R.layout.activity_datespel);

        mSwipeView = findViewById(R.id.datespel_swipe);

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f));

        List<DateSpelVraag> vragen = UtilsDateSpel.loadCards(getApplicationContext());
        Collections.shuffle(vragen);

        for(DateSpelVraag vraag: vragen) {
            mSwipeView.addView(new DateSpelCard(mContext, mSwipeView, vraag));
        }

    }
}
