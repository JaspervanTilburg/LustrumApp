package com.example.tudelftsid.lustrumapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.tudelftsid.lustrumapp.InfoPages.GalaInfoActivity;
import com.example.tudelftsid.lustrumapp.InfoPages.LustrumWekenInfoActivity;
import com.example.tudelftsid.lustrumapp.InfoPages.PiekWeekInfoActivity;
import com.example.tudelftsid.lustrumapp.InfoPages.WispoInfoActivity;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MainActivity extends AppCompatActivity {

    private PagerAdapterMain pagerAdapterMain;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pagerAdapterMain = new PagerAdapterMain(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(pagerAdapterMain);
        mViewPager.setCurrentItem(0);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.tijdlijn_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.profiel_icon);
        tabLayout.getTabAt(2).setIcon(R.drawable.features_icon);
    }

    public void onTinderClick(View view) {
        Intent intent = new Intent(this, TinderActivity.class);
        startActivity(intent);
    }

    public void onBerichtenClick(View view) {
        Intent intent = new Intent(this, BerichtenActivity.class);
        startActivity(intent);
    }

    public void onDatespelClick(View view) {
        Intent intent = new Intent(this, DatespelActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        EditText username = (EditText) findViewById(R.id.loginEmail);
        EditText password = (EditText) findViewById(R.id.loginPassword);
        RequestParams params = new RequestParams();
        JSONObject auth = new JSONObject();
        JSONObject subParams = new JSONObject();
        StringEntity entity = null;
        try {
            subParams.put("email", "test@example.com");
            subParams.put("password", "123123");
            auth.put("auth", subParams);
            entity = new StringEntity(auth.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        LustrumRestClient.postJSON("auth", entity, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Object " + response);
            }

            @Override
            public void onFailure(int statusCode, Header[] header, String msg, Throwable exception) {
                System.out.println("Something went wrong: " + statusCode + ", " + msg);
            }
        });
    }

    public void onGalaInfoClick(View view) {
        Intent intent = new Intent(this, GalaInfoActivity.class);
        startActivity(intent);
    }

    public void onWispoInfoClick(View view) {
        Intent intent = new Intent(this, WispoInfoActivity.class);
        startActivity(intent);
    }

    public void onLustrumWekenInfoClick(View view) {
        Intent intent = new Intent(this, LustrumWekenInfoActivity.class);
        startActivity(intent);
    }

    public void onPiekWeekInfoClick(View view) {
        Intent intent = new Intent(this, PiekWeekInfoActivity.class);
        startActivity(intent);
    }

}
