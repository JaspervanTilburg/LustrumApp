package com.virgiel.lustrumapp.InfoPages;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.virgiel.lustrumapp.R;

/**
 * Created by Jip on 23-3-2018.
 */

public class PoolPartyInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("POOL PARTY");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window poolPartyInfoWindow = this.getWindow();
            poolPartyInfoWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            poolPartyInfoWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            poolPartyInfoWindow.setStatusBarColor(this.getResources().getColor(R.color.lustrumBlue_Dark));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool_party_info);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(this.getResources().getColor(R.color.lustrumBlue)));

        Typeface head_font = Typeface.createFromAsset(getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        Typeface body_font = Typeface.createFromAsset(getAssets(), "fonts/DIN_Bold.ttf");

        TextView head = (TextView) findViewById(R.id.pool_party_info_head);
        head.setTypeface(head_font);

        TextView body = (TextView) findViewById(R.id.pool_party_info_body);
        TextView bullet1 = (TextView) findViewById(R.id.pool_party_bullet_1);
        TextView bullet2 = (TextView) findViewById(R.id.pool_party_bullet_2);
        TextView bullet3 = (TextView) findViewById(R.id.pool_party_bullet_3);
        TextView bullet4 = (TextView) findViewById(R.id.pool_party_bullet_4);
        body.setTypeface(body_font);
        bullet1.setTypeface(body_font);
        bullet2.setTypeface(body_font);
        bullet3.setTypeface(body_font);
        bullet4.setTypeface(body_font);
        //TODO set these textview on basis of server

//        TODO change the image
        ImageView backgroundImage = (ImageView) findViewById(R.id.wispoBackground);
        backgroundImage.setImageResource(R.drawable.wispo_info);

        //TODO test this with server
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.pool_party_timetable_layout);
//        for( int i = 0; i < textArray.length; i++ )
//        {
//            TextView tijd = new TextView(this);
//            tijd.setText(textArray[i][0]);
//            linearLayout.addView(tijd);
//
//            TextView moment = new TextView(this);
//            moment.setText(textArray[i][1]);
//            linearLayout.addView(moment);
//        }
    }
}
