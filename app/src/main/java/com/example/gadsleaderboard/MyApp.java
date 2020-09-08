package com.example.gadsleaderboard;

import android.app.Application;

import com.example.gadsleaderboard.interfaces.SplashScreenListener;

import java.util.Timer;
import java.util.TimerTask;

public class MyApp extends Application {

    private SplashScreenListener listener;
    private Timer mTimer;

    public void startUserSession() {
        cancelTimer();
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                listener.onSessionFinished();
            }
        }, 2500);
    }

    private void cancelTimer() {
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    public void registerSessionListener(SplashScreenListener listener) {
        this.listener = listener;
    }

    public void onUserInteracted() {
        startUserSession();
    }
}
