package com.example.tudelftsid.lustrumapp;

import android.content.Intent;
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

public class GalaInfoActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Gala");
        Window galaWindow = this.getWindow();
        galaWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        galaWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        galaWindow.setStatusBarColor(this.getResources().getColor(R.color.colorAccent));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gala_info);

        ImageView backgroundImage = (ImageView) findViewById(R.id.galaBackground);
        backgroundImage.setImageResource(R.drawable.gala_info);

        final Button infoButton = (Button) findViewById(R.id.gala_info_button);
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
