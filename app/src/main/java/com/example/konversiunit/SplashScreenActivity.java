package com.example.konversiunit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.konversiunit.onboarding.OnboardingActivity;

public class SplashScreenActivity extends AppCompatActivity {
    private int waktu_loading = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent home = new Intent(SplashScreenActivity.this, OnboardingActivity.class);
                startActivity(home);
                finish();

            }
        }, waktu_loading);
    }
}