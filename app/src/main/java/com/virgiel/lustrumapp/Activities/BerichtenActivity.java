package com.virgiel.lustrumapp.Activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.virgiel.lustrumapp.Notifications.Notification;
import com.virgiel.lustrumapp.Notifications.NotificationAdapter;
import com.virgiel.lustrumapp.R;

import java.util.ArrayList;
import java.util.List;

public class BerichtenActivity extends AppCompatActivity {

    private DatabaseReference notificationRef;
    private List<Notification> notifications;
    private ListView listView;
    private NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berichten);

        Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        notificationRef = FirebaseDatabase.getInstance().getReference().child("notifications");
        notifications = new ArrayList<>();
        adapter = new NotificationAdapter(this, notifications, font);
        listView = findViewById(R.id.notifications_list_view);
    }

    @Override
    public void onStart() {
        super.onStart();
        notificationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notifications.clear();
                for (DataSnapshot notificationSnap : dataSnapshot.getChildren()) {
                    Notification notification = notificationSnap.getValue(Notification.class);
                    notifications.add(notification);
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
