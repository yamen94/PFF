package com.gebril.yamen.pff.activites.tools;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private static SharedPreferences  sharedPref;

    public SharedPreferenceManager(Context context)
    {
        if(sharedPref == null)
            sharedPref =  context.getSharedPreferences(context.getPackageName(), context.MODE_PRIVATE);
    }

    public static String read(String key, String defValue) {
        return sharedPref.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public static boolean read(String key, boolean defValue) {
        return sharedPref.getBoolean(key, defValue);
    }

    public static void write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = sharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public static Integer read(String key, int defValue) {
        return sharedPref.getInt(key, defValue);
    }

    public static void write(String key, Integer value) {
        SharedPreferences.Editor prefsEditor = sharedPref.edit();
        prefsEditor.putInt(key, value).commit();
    }
}
