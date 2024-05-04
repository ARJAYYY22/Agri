package com.example.agricare;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.agricare.commons.PreferenceHelper;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.internal.ContextUtils;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "SettingsActivityss";
    private ImageView ivEnglish, ivTagalog;
    private LinearLayout llEnglish, llTagalog;
    private PreferenceHelper preferenceHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        preferenceHelper = PreferenceHelper.getInstance(this);
        initViews();
    }

    private void initViews(){
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        toolbar.setNavigationOnClickListener(v -> finish());
        ivEnglish = findViewById(R.id.iv_english);
        ivTagalog = findViewById(R.id.iv_tagalog);
        llEnglish = findViewById(R.id.ll_english);
        llTagalog = findViewById(R.id.ll_tagalog);
        if(preferenceHelper.contains(PreferenceHelper.LANGUAGE)) {
            String language = preferenceHelper.getString(PreferenceHelper.LANGUAGE);
            Log.d(TAG, "Lang: " + language);
            if (language.equals(PreferenceHelper.EN)) {
                ivEnglish.setVisibility(ImageView.VISIBLE);
                ivTagalog.setVisibility(ImageView.GONE);
            } else {
                ivEnglish.setVisibility(ImageView.GONE);
                ivTagalog.setVisibility(ImageView.VISIBLE);
            }
        }else{
            ivEnglish.setVisibility(ImageView.VISIBLE);
            ivTagalog.setVisibility(ImageView.GONE);
        }
        llEnglish.setOnClickListener(v -> {
            preferenceHelper.save(PreferenceHelper.LANGUAGE, PreferenceHelper.EN);
            changeLanguage();
            restart();
        });
        llTagalog.setOnClickListener(v -> {
            preferenceHelper.save(PreferenceHelper.LANGUAGE, PreferenceHelper.FIL);
            changeLanguage();
            restart();
        });

    }


    private void changeLanguage(){
        getResources().getConfiguration().setLocale(preferenceHelper.getString(PreferenceHelper.LANGUAGE).equals(PreferenceHelper.EN) ? new Locale("en") : new Locale("fil"));
        Context updatedContext = createConfigurationContext(getResources().getConfiguration());
    }
    private void restart(){
        Intent i = new Intent(this, SplashScreenActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
