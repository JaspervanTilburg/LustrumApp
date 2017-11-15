package com.example.tudelftsid.lustrumapp;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.*;
import com.loopj.android.http.*;
import com.mindorks.placeholderview.annotations.View;

import java.util.List;

import cz.msebera.android.httpclient.Header;


public class DatespelActivity extends AppCompatActivity {

    @View(R.id.testID)
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datespel);

        try {
            getDateCards();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getDateCards() throws JSONException {
        LustrumRestClient.get("/date_questions", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Object " + response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    List<DateQuestion> questions = Utils.loadDateQuestions(getApplicationContext(), response);
                    for (DateQuestion question : questions) {
                        System.out.println(question.getQuestion());
                    }
                    //textView.setText(questions.get(0).getQuestion());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
