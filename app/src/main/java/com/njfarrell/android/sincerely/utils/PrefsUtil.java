package com.njfarrell.android.sincerely.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefsUtil {

    private static final String KEY_USER_UUID = "user_uuid";

    private static PrefsUtil instance;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private PrefsUtil(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
    }

    public static PrefsUtil getInstance(Context context) {
        if (instance == null) {
            instance = new PrefsUtil(context);
        }
        return instance;
    }

    public void storeUserUUID(String userUUID) {
        editor.putString(KEY_USER_UUID, userUUID);
        editor.apply();
    }

    public String getUserUUID() {
        return preferences.getString(KEY_USER_UUID, null);
    }
}
