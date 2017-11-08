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

import org.w3c.dom.Text;

public class GalaInfoActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("GALA");
        Window galaWindow = this.getWindow();
        galaWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        galaWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        galaWindow.setStatusBarColor(this.getResources().getColor(R.color.lustrumPink_2));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gala_info);

        Typeface head_font = Typeface.createFromAsset(getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        Typeface body_font = Typeface.createFromAsset(getAssets(), "fonts/DIN_Bold.ttf");

        TextView head = (TextView) findViewById(R.id.gala_info_head);
        head.setTypeface(head_font);

        TextView body = (TextView) findViewById(R.id.gala_info_body);
        TextView bullet1 = (TextView) findViewById(R.id.gala_bullet_1);
        TextView bullet2 = (TextView) findViewById(R.id.gala_bullet_2);
        TextView bullet3 = (TextView) findViewById(R.id.gala_bullet_3);
        TextView bullet4 = (TextView) findViewById(R.id.gala_bullet_4);
        TextView button = (TextView) findViewById(R.id.gala_website_button);
        body.setTypeface(body_font);
        bullet1.setTypeface(body_font);
        bullet2.setTypeface(body_font);
        bullet3.setTypeface(body_font);
        bullet4.setTypeface(body_font);
        button.setTypeface(body_font);

        ImageView backgroundImage = (ImageView) findViewById(R.id.galaBackground);
        backgroundImage.setImageResource(R.drawable.gala_info);

        final Button infoButton = (Button) findViewById(R.id.gala_website_button);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.lustrumvirgiel.nl/evenementen/gala/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}
