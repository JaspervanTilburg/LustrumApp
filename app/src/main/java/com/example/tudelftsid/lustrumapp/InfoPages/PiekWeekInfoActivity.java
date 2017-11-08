package com.example.tudelftsid.lustrumapp.InfoPages;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tudelftsid.lustrumapp.R;

/**
 * Created by Jip on 2-11-2017.
 */

public class PiekWeekInfoActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("PIEKWEEK");
        Window piekWeekWindow = this.getWindow();
        piekWeekWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        piekWeekWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        piekWeekWindow.setStatusBarColor(this.getResources().getColor(R.color.lustrumGreen_1));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piek_week_info);

        Typeface head_font = Typeface.createFromAsset(getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        Typeface body_font = Typeface.createFromAsset(getAssets(), "fonts/DIN_Bold.ttf");

        TextView head = (TextView) findViewById(R.id.piek_week_info_head);
        head.setTypeface(head_font);

        TextView body = (TextView) findViewById(R.id.piek_week_info_body);
        TextView bullet1 = (TextView) findViewById(R.id.piek_week_bullet_1);
        TextView bullet2 = (TextView) findViewById(R.id.piek_week_bullet_2);
        TextView bullet3 = (TextView) findViewById(R.id.piek_week_bullet_3);
        TextView bullet4 = (TextView) findViewById(R.id.piek_week_bullet_4);
        TextView button = (TextView) findViewById(R.id.piek_week_website_button);
        body.setTypeface(body_font);
        bullet1.setTypeface(body_font);
        bullet2.setTypeface(body_font);
        bullet3.setTypeface(body_font);
        bullet4.setTypeface(body_font);
        button.setTypeface(body_font);

        ImageView backgroundImage = (ImageView) findViewById(R.id.piekWeekBackground);
        backgroundImage.setImageResource(R.drawable.piek_week_info);

        final Button infoButton = (Button) findViewById(R.id.piek_week_website_button);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.lustrumvirgiel.nl/evenementen/piekweek/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}
