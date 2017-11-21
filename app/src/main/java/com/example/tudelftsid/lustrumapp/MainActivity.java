package com.example.tudelftsid.lustrumapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tudelftsid.lustrumapp.InfoPages.GalaInfoActivity;
import com.example.tudelftsid.lustrumapp.InfoPages.LustrumWekenInfoActivity;
import com.example.tudelftsid.lustrumapp.InfoPages.PiekWeekInfoActivity;
import com.example.tudelftsid.lustrumapp.InfoPages.WispoInfoActivity;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private PagerAdapterMain pagerAdapterMain;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window mainWindow = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mainWindow.setStatusBarColor(this.getResources().getColor(R.color.lustrumGrey));
        }

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

        //For testing
        //new File(getFilesDir(), LustrumRestClient.FILE_NAME).delete();
        retrieveToken();
    }

    public void onTinderClick(View view) {
        if (!LustrumRestClient.hasToken()) {
            mViewPager.setCurrentItem(1);
        } else {
            Intent intent = new Intent(this, TinderActivity.class);
            startActivity(intent);
        }
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

        LustrumRestClient.authenticate(username.getText().toString(), password.getText().toString(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Token retrieved: " + response);
                try {
                    String token = response.getString("jwt");
                    saveToken(token);
                    LustrumRestClient.setToken(token);
                    System.out.println("Logged in");
                    Toast toast = Toast.makeText(getApplicationContext(), "LOGGED IN",Toast.LENGTH_SHORT);
                    toast.show();
                    pagerAdapterMain.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] header, String msg, Throwable exception) {
                System.out.println("Something went wrong: " + statusCode + ", " + msg);
                Toast toast = Toast.makeText(getApplicationContext(), "LOGIN FAILED",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public void logout(View view) {
        new File(getFilesDir(), LustrumRestClient.FILE_NAME).delete();
        LustrumRestClient.setToken(null);
        pagerAdapterMain.notifyDataSetChanged();
        System.out.println("Logged out");
        Toast toast = Toast.makeText(getApplicationContext(), "LOGGED OUT",Toast.LENGTH_SHORT);
        toast.show();
    }

    public void saveToken(String token) {
        new File(getApplicationContext().getFilesDir(), LustrumRestClient.FILE_NAME);
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(LustrumRestClient.FILE_NAME, Context.MODE_PRIVATE);
            outputStream.write(token.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void retrieveToken() {
        try {
            FileInputStream inputStream = new FileInputStream(new File(getFilesDir(), LustrumRestClient.FILE_NAME));

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                LustrumRestClient.setToken(stringBuilder.toString());
                System.out.println("Token retrieved");
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
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
