package com.example.tudelftsid.lustrumapp;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment newFrag = new MainFragment();
        transaction.replace(R.id.fragment_container, newFrag);
        transaction.commit();

    }

    private void initSwiper() {
//        SwipeFlingAdapterView swipeFrame = (SwipeFlingAdapterView) findViewById(R.id.swipeFrame);
//        swipeFrame.setAdapter(aa);
//        swipeFrame.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
//            @Override
//            public void removeFirstObjectInAdapter() {
//
//            }
//
//            @Override
//            public void onLeftCardExit(Object o) {
//                Toast.makeText(MainActivity.this, "Left!", Toast.LENGTH_SHORT).show();
//                System.out.println("test");
//            }
//
//            @Override
//            public void onRightCardExit(Object o) {
//                Toast.makeText(MainActivity.this, "Right!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAdapterAboutToEmpty(int i) {
//
//            }
//
//            @Override
//            public void onScroll(float v) {
//
//            }
//        });
    }

    public void onTinderClick(View view) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment tinderFrag = new TinderFragment();
        transaction.replace(R.id.fragment_container, tinderFrag);
        transaction.commit();
    }
}
