package com.example.tudelftsid.lustrumapp.InfoPages;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
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

public class LustrumWekenInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("LUSTRUMWEEK");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window lustrumWeekWindow = this.getWindow();
            lustrumWeekWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            lustrumWeekWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            lustrumWeekWindow.setStatusBarColor(this.getResources().getColor(R.color.lustrumBlue_Dark));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lustrum_week_info);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(this.getResources().getColor(R.color.lustrumBlue)));

        Typeface head_font = Typeface.createFromAsset(getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        Typeface body_font = Typeface.createFromAsset(getAssets(), "fonts/DIN_Bold.ttf");

        TextView head = (TextView) findViewById(R.id.lustrum_week_info_head);
        head.setTypeface(head_font);

        TextView body = (TextView) findViewById(R.id.lustrum_week_info_body);
        TextView bullet1 = (TextView) findViewById(R.id.lustrum_week_bullet_1);
        TextView bullet2 = (TextView) findViewById(R.id.lustrum_week_bullet_2);
        TextView bullet3 = (TextView) findViewById(R.id.lustrum_week_bullet_3);
        TextView bullet4 = (TextView) findViewById(R.id.lustrum_week_bullet_4);
        TextView button = (TextView) findViewById(R.id.lustrum_week_website_button);
        body.setTypeface(body_font);
        bullet1.setTypeface(body_font);
        bullet2.setTypeface(body_font);
        bullet3.setTypeface(body_font);
        bullet4.setTypeface(body_font);
        button.setTypeface(body_font);

        ImageView backgroundImage = (ImageView) findViewById(R.id.lustrumWeekBackground);
        backgroundImage.setImageResource(R.drawable.lustrum_week_info);

        final Button infoButton = (Button) findViewById(R.id.lustrum_week_website_button);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.lustrumvirgiel.nl/evenementen/lustrumweek/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
