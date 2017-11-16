package com.example.tudelftsid.lustrumapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class DatespelActivity extends AppCompatActivity {

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        setContentView(R.layout.activity_datespel);

        try {
            getDateCards();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getDateCards() throws JSONException {
        LustrumRestClient.get("date_questions", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    System.out.println("Array " + response);
                    List<DateQuestion> questions = Utils.loadDateQuestions(getApplicationContext(), response);
                    showQuestions(questions);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showQuestions(List<DateQuestion> questions) {
        mSwipeView = findViewById(R.id.datespel_swipe);
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f));
        Collections.shuffle(questions);

        for(DateQuestion vraag: questions) {
            mSwipeView.addView(new DateSpelCard(mContext, mSwipeView, vraag));
        }
    }

}
