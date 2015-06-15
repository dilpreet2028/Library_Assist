package com.libassist.libraryassist;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {
    Database data;
    Intent intent;
    PendingIntent pi;
    long id;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //actionBar=getActionBar();
        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#006600")));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data= new Database(this);
        intent=new Intent(this,Broadcast.class);
        pi=PendingIntent.getBroadcast(this,0,intent,0);
     id=data.add("New"); //"new"  ki jagah vo / name aaega jo name of the book he....
        if(id==-1)
            Toast.makeText(this, "Not added", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show();
        start();
        data.display();

       // data.diff();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void start(){


        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        Calendar cal= Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.HOUR_OF_DAY,9);
        cal.set(Calendar.MINUTE,30);
        cal.set(Calendar.AM_PM,Calendar.PM);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pi);

        ComponentName cn= new ComponentName(this,MainActivity.class);
        PackageManager packageManager=this.getPackageManager();
        packageManager.setComponentEnabledSetting(cn,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}
