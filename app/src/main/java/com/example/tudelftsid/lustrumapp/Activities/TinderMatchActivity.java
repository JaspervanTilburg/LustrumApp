package com.example.tudelftsid.lustrumapp.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tudelftsid.lustrumapp.LustrumRestClient;
import com.example.tudelftsid.lustrumapp.R;

import org.w3c.dom.Text;

public class TinderMatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Typeface body_font = Typeface.createFromAsset(this.getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        TextView textView = findViewById(R.id.newMatchTxt);
        textView.setText(getIntent().getStringExtra("name").toUpperCase());
        textView.setTypeface(body_font);

        TextView phoneView = findViewById(R.id.newMatchPhoneTxt);
        phoneView.setText("+" + getIntent().getStringExtra("phone"));
        phoneView.setTypeface(body_font);

        ImageView imageView = findViewById(R.id.newMatchView);
        if (!getIntent().getStringExtra("image").contains("missing")) {
            Glide.with(this).load(LustrumRestClient.BASE_URL + getIntent().getStringExtra("image")).into(imageView);
        }
    }

    public void onAddContactClick(View view) {
        if (hasPermission()) {
            ViewGroup parent = (ViewGroup) view.getParent();
            TextView nameTxt = (TextView) parent.getChildAt(4);
            TextView numberTxt = (TextView) parent.getChildAt(5);
            String name = nameTxt.getText().toString();
            openContacts(name, numberTxt.getText().toString());
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_CONTACTS},
                    TinderActivity.MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case TinderActivity.MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
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

    private boolean hasPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_CONTACTS)) {
                return false;
            } else {
                return false;
            }
        }
        return true;
    }

    private void openContacts(String name, String phone) {
        Intent intent = new Intent(
                ContactsContract.Intents.SHOW_OR_CREATE_CONTACT,
                Uri.parse("tel:" + phone));
        intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
        intent.putExtra(ContactsContract.Intents.EXTRA_FORCE_CREATE, true);
        startActivity(intent);
    }
}
