package com.example.tudelftsid.lustrumapp;


import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window dateSpelWindow = this.getWindow();
            dateSpelWindow.setStatusBarColor(this.getResources().getColor(R.color.lustrumPink_Dark));
        }
        setTitle("GALA DATESPEL");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(this.getResources().getColor(R.color.lustrumPink)));

        Context context = getApplicationContext();
        CharSequence text = "Swipe voor een nieuwe vraag/stelling!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

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
                    System.out.println("Date questions retrieved: " + response);
                    List<DateQuestion> questions = Utils.loadDateQuestions(getApplicationContext(), response);
                    showQuestions(questions);
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
            Typeface body_font = Typeface.createFromAsset(getAssets(), "fonts/DIN_Bold.ttf");
            mSwipeView.addView(new DateSpelCard(mContext, mSwipeView, vraag, body_font));
        }
    }

    public void logout() {
        new File(mContext.getFilesDir(), LustrumRestClient.FILE_NAME).delete();
        LustrumRestClient.setToken(null);
        System.out.println("Logged out");
        Toast toast = Toast.makeText(mContext.getApplicationContext(), "LOGGED OUT",Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
