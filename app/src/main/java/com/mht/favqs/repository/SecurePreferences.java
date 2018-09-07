package com.mht.favqs.repository;

import android.content.Context;
import android.content.SharedPreferences;


public class SecurePreferences {

    private static final String PREF_NAME = "FavQSPref";
    private static SecurePreferences sInstance;
    private SharedPreferences settings;

    private SecurePreferences(Context context) {
        settings = context.getSharedPreferences(PREF_NAME, 0);
    }

    public static SecurePreferences getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SecurePreferences(context);
        }
        return sInstance;
    }

    public void saveString(Context context, String key, String value) {
        if (context == null)
            return;

        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public String getString(Context context, String key, String defValue) {
        if (context == null)
            return defValue;

        return settings.getString(key, defValue);
    }


    public void saveLong(Context context, String key, long value) {
        if (context == null)
            return;

        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLong(Context context, String key, long defValue) {
        if (context == null)
            return defValue;
        return settings.getLong(key, defValue);
    }

    public void saveInt(Context context, String key, int value) {
        if (context == null)
            return;

        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(Context context, String key, int defValue) {
        if (context == null)
            return defValue;
        return settings.getInt(key, defValue);
    }

    public void saveBoolean(Context context, String key, boolean value) {
        if (context == null)
            return;

        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(Context context, String key, boolean defValue) {
        if (context == null)
            return defValue;
        return settings.getBoolean(key, defValue);
    }


    public SharedPreferences.Editor getEditor() {
        return settings.edit();
    }
}


