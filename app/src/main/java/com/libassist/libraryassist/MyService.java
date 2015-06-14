package com.libassist.libraryassist;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    Database database;
    public MyService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        database=new Database(this);
        Toast.makeText(this,"Service Started",Toast.LENGTH_SHORT).show();
        database.diff();
        stopSelf();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Service Stopped",Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
}
