package com.example.agricare.commons;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class PreferenceHelper {

    private static PreferenceHelper instance = null;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    public static final String LANGUAGE = "language_key";
    public static final String EN = "en";
    public static final String FIL = "fil";

    public static PreferenceHelper getInstance(Context context) {
        if (instance == null)
            instance = new PreferenceHelper(context);
        return instance;
    }

    private PreferenceHelper(Context context) {
        String PACKAGE_NAME = context.getPackageName();
        String name = PACKAGE_NAME + ".cache_data";
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public boolean contains(String key){
        return preferences.contains(key);
    }

    public void save(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    public void save(String key, long value) {
        preferences.edit().putLong(key, value).apply();
    }

    public void save(String key, int value) {
        preferences.edit().putInt(key, value).apply();
    }

    public void save(String key, boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    public void save(String key, Set<String> value){
        preferences.edit().putStringSet(key, value).apply();
    }

    public void remove(String key){
        preferences.edit().remove(key).apply();
    }

    public void clearPrefs(){
        editor.clear();
        editor.commit();
    }

    public String getString(String key) {
        return preferences.getString(key, null);
    }

    public Long getLong(String key) {
        return preferences.getLong(key, 0L);
    }

    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }
}
