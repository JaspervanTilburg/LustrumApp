package com.virgiel.lustrumapp.Notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.virgiel.lustrumapp.Activities.BerichtenActivity;
import com.virgiel.lustrumapp.R;

/**
 * Created by TUDelft SID on 27-6-2018.
 */

public class NotificationService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        System.out.println("Notification received: " + remoteMessage.getNotification().getBody());
        Intent intent = new Intent(this, BerichtenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle("LUSTRUM VIRGIEL XXIV");
        notificationBuilder.setContentText(remoteMessage.getNotification().getBody());
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setSmallIcon(R.drawable.icon_logo_4d);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());

        DatabaseReference notificationRef = FirebaseDatabase.getInstance().getReference("notifications");
        Notification notification = new Notification("LUSTRUM VIRGIEL XXIV", remoteMessage.getNotification().getBody(), remoteMessage.getSentTime());
        notificationRef.push().setValue(notification);
    }
}
