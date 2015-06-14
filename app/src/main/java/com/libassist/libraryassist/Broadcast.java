package com.libassist.libraryassist;



import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.SystemClock;
import android.support.v4.content.WakefulBroadcastReceiver;

import java.util.Calendar;


public class Broadcast extends WakefulBroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        //use power managemnet here for service.
        Intent service=new Intent(context,MyService.class);
        PowerManager power=(PowerManager)context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock=power.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"TAG");
        wakeLock.acquire();
        startWakefulService(context,service);
        wakeLock.release();


    }

}
