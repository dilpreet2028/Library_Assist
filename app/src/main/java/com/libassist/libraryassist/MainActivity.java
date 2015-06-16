package com.libassist.libraryassist;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {
    Database data;
    Intent intent;
    Button b1,b2,b3;
    PendingIntent pi;
    long id;
    String book;
    EditText  et,cd;
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

        b2=(Button) findViewById(R.id.reissue);
        b3=(Button) findViewById(R.id.ret);
        b1=(Button) findViewById(R.id.issue);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //issue
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent i=new Intent(MainActivity.this,booklist.class);
                    startActivity(i);

                }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,booklist.class);
                startActivity(i);
            }
        });

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
            LayoutInflater inflater=getLayoutInflater();
            View view=inflater.inflate(R.layout.change_date,null);
            final Dialog dialog=new Dialog(this);
            dialog.setTitle("Change Date");
            dialog.setContentView(view);
            dialog.show();
            Button add;
            add=(Button)view.findViewById(R.id.change);
            cd=(EditText)view.findViewById(R.id.cd);
            add.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    String date=cd.getText().toString();
                    try{ cd.setText(" ");

                        int newdate=Integer.parseInt(date);
                        if(newdate>0) {
                            data.changert(newdate);
                            dialog.dismiss();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Please enter a valid number ",Toast.LENGTH_SHORT).show();
                             dialog.dismiss();
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(MainActivity.this,"Please enter a valid number ",Toast.LENGTH_SHORT).show();
                    }

                }
            });
            return true;
        }
        if( id == R.id.action_about){
            Intent intent=new Intent(this,About.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void issue(View v){
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.my_dialog,null);
        final Dialog dialog=new Dialog(this);
        dialog.setTitle("Issue");
        dialog.setContentView(view);
        dialog.show();
        Button add;
        add=(Button)view.findViewById(R.id.add);
        et=(EditText)view.findViewById(R.id.editText);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                book=et.getText().toString();
                dialog.dismiss();
                adddata(book);
            }
        });


    }
    public void adddata(String name){
        if(name.length()>0) {
            id = data.add(name); //"new"  ki jagah vo / name aaega jo name of the book he....
            if (id == -1)
                Toast.makeText(this, "Not added", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
            start();
            data.display();
        }
        else{
            Toast.makeText(this,"Please a Book Name",Toast.LENGTH_SHORT).show();
        }
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
