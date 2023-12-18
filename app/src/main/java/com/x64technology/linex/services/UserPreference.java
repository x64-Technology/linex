package com.x64technology.linex.services;

import android.content.Context;
import android.content.SharedPreferences;

import com.x64technology.linex.utils.Constants;

public class UserPreference {
    public final SharedPreferences userPref;
    private final SharedPreferences.Editor editor;
    Context context;

    public UserPreference(Context context) {
        this.context = context;
        userPref = context.getSharedPreferences(Constants.USER_PREFERENCE, Context.MODE_PRIVATE);
        editor = userPref.edit();
    }

    public void saveUserData(String username, String uid, String name, String dpLink) {
        editor.putString(Constants.STR_USERNAME, username);
        editor.putString(Constants.STR_USERID, uid);
        editor.putString(Constants.STR_NAME, name);
        editor.putString(Constants.STR_DPLINK, dpLink);
        editor.apply();
    }

    public void saveToken(String token) {
        editor.putString(Constants.STR_TOKEN, token);
        editor.apply();
    }
}