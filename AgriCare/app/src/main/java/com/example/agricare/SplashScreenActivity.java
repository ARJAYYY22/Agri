package com.example.agricare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.agricare.commons.PreferenceHelper;

import java.util.Locale;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);

        new Handler().postDelayed(() -> {
            Intent intent= new Intent(SplashScreenActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }

    @Override
    protected void attachBaseContext(Context base) {
        String language = "en";
        if(PreferenceHelper.getInstance(base).getString(PreferenceHelper.LANGUAGE) != null){
            language = PreferenceHelper.getInstance(base).getString(PreferenceHelper.LANGUAGE);
        }
        Log.d("language_test", "Lang: " + language);
        Locale lang = new Locale(language);
        base.getResources().getConfiguration().setLocale(lang);
        Context updatedContext = base.createConfigurationContext(base.getResources().getConfiguration());
        super.attachBaseContext(updatedContext);
    }
}