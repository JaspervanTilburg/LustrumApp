package com.example.tudelftsid.lustrumapp.Activities;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.provider.ContactsContract.Data;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tudelftsid.lustrumapp.PagerAdapter.PagerAdapterTinder;
import com.example.tudelftsid.lustrumapp.Preferences;
import com.example.tudelftsid.lustrumapp.R;

import java.util.ArrayList;


public class TinderActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 49;
    private PagerAdapterTinder pagerAdapterTinder;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinder);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window dateSpelWindow = this.getWindow();
            dateSpelWindow.setStatusBarColor(this.getResources().getColor(R.color.lustrumPink_Dark));
        }
        setTitle("TOKO TINDER");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(this.getResources().getColor(R.color.lustrumPink)));

        pagerAdapterTinder = new PagerAdapterTinder(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.tinderPager);
        mViewPager.setAdapter(pagerAdapterTinder);
        mViewPager.setCurrentItem(0);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_tinder_layout);
        tabLayout.setupWithViewPager(mViewPager);

        Preferences.initPreferences();
    }

    public void onMannenCheckBoxClick(View view) {
        CheckBox checkBox = findViewById(R.id.mannenCheckBox);
        if (checkBox.isChecked()) {
            Preferences.setPreferMen(true);
        } else {
            Preferences.setPreferMen(false);
        }
        Preferences.postPreferences();
    }

    public void onVrouwenCheckBoxClick(View view) {
        CheckBox checkBox = findViewById(R.id.vrouwenCheckBox);
        if (checkBox.isChecked()) {
            Preferences.setPreferWomen(true);
        } else {
            Preferences.setPreferWomen(false);
        }
        Preferences.postPreferences();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void onAddContactClick(View view) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_CONTACTS)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {
            addContact(view);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private void addContact(View view) {
        ViewGroup parent = (ViewGroup) view.getParent();
        TextView nameTxt = (TextView) parent.getChildAt(0);
        TextView numberTxt = (TextView) parent.getChildAt(2);
        String words = nameTxt.getText().toString();
        String name = words.substring(0, words.length() - 5);
        Intent intent = new Intent(
                ContactsContract.Intents.SHOW_OR_CREATE_CONTACT,
                Uri.parse("tel:" + numberTxt.getText()));
        intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
        intent.putExtra(ContactsContract.Intents.EXTRA_FORCE_CREATE, true);
        startActivity(intent);
    }
}
