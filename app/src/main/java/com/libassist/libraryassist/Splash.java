package com.libassist.libraryassist;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Splash extends ActionBarActivity {

    protected boolean _active = true;
    protected int splashTime = 1500;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(splashTime);
                    startActivity(new Intent(Splash.this, MainActivity.class));

                } catch (Exception e) {

                } finally {


                    finish();
                }
            }

        };
        splashTread.start();
    }
}
