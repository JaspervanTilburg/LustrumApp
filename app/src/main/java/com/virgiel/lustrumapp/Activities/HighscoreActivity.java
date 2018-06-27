package com.virgiel.lustrumapp.Activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.virgiel.lustrumapp.Notifications.Notification;
import com.virgiel.lustrumapp.Notifications.NotificationAdapter;
import com.virgiel.lustrumapp.PintenVangen.HighscoreAdapter;
import com.virgiel.lustrumapp.PintenVangen.Score;
import com.virgiel.lustrumapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rolf on 24-11-2017.
 */

public class HighscoreActivity extends AppCompatActivity {

    private DatabaseReference highscoreRef;
    private Query highscoreQuery;
    private List<Score> highscores;
    private ListView listView;
    private HighscoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        highscoreRef = FirebaseDatabase.getInstance().getReference().child("scores");
        highscoreQuery = highscoreRef.orderByChild("score");
        highscores = new ArrayList<>();
        adapter = new HighscoreAdapter(this, highscores, font);
        listView = findViewById(R.id.highscore_list_view);
    }

    @Override
    public void onStart() {
        super.onStart();
        highscoreQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                highscores.clear();
                for (DataSnapshot highscoreSnap : dataSnapshot.getChildren()) {
                    Score score = highscoreSnap.getValue(Score.class);
                    highscores.add(score);
                }
                Collections.reverse(highscores);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
