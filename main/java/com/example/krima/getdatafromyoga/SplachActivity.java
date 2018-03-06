package com.example.krima.getdatafromyoga;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplachActivity extends Activity {

    final static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                Intent i = new Intent(SplachActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
               /* Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();*/

    },SPLASH_TIME_OUT);
}
}

