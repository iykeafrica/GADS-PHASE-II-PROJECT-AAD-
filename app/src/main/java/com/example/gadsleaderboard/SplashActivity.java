package com.example.gadsleaderboard;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends BaseActivity {

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        mIntent = new Intent(this, MainActivity.class);
//
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                startActivity(mIntent);
//
//            }
//        };
//
//        Timer splash = new Timer();
//        splash.schedule(task,  3000);

    }
}