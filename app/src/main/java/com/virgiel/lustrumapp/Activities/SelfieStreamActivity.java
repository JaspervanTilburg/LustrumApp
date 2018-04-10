package com.virgiel.lustrumapp.Activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.virgiel.lustrumapp.LustrumRestClient;
import com.virgiel.lustrumapp.R;
import com.virgiel.lustrumapp.Selfie;
import com.virgiel.lustrumapp.SelfieStreamAdapter;
import com.virgiel.lustrumapp.Utils;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by TUDelft SID on 10-4-2018.
 */

public class SelfieStreamActivity extends AppCompatActivity {

    private Typeface body_font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfie_stream);
        body_font = Typeface.createFromAsset(getAssets(), "fonts/DIN_Bold.ttf");

        LustrumRestClient.getWithHeader("selfies", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Selfies Retrieved: " + response);

                List<Selfie> selfies = Utils.loadSelfies(response);
                SelfieStreamAdapter adapter = new SelfieStreamAdapter(getApplicationContext(), selfies, body_font);
                ListView itemsListView  = findViewById(R.id.selfieListView);
                itemsListView.setAdapter(adapter);
                //X2e5qc5LsWs
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong, statuscode: " + statusCode + ", " + msg);
            }
        });
    }

}
