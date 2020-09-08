package com.example.gadsleaderboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gadsleaderboard.interfaces.SplashScreenListener;

public class BaseActivity extends AppCompatActivity implements SplashScreenListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MyApp) getApplication()).registerSessionListener(this);
        ((MyApp) getApplication()).startUserSession();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        ((MyApp) getApplication()).onUserInteracted();
    }

    @Override
    public void onSessionFinished() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
}
