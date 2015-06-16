package com.libassist.libraryassist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Notify extends ActionBarActivity {
NotificationManager nm;
    booklist bklist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        start();
    }

    public void start() {nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent in= new Intent(this,booklist.class);
        PendingIntent pi= PendingIntent.getActivity(this,0,in,0);
        NotificationCompat.Builder notify=new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.abc_cab_background_top_material)
                .setContentTitle("Hello!!")
                .setContentInfo("You have Books to return ")
                .setContentIntent(pi)
                .setDefaults(Notification.DEFAULT_ALL);
        nm.notify(111,notify.build());
        finish();
    }


}
