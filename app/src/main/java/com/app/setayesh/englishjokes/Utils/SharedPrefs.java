package com.app.setayesh.englishjokes.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

import com.app.setayesh.englishjokes.model.pojo.Joke;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

public class SharedPrefs {

    public static final String JOKEKEY = "jokekey";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String SESSION_ID = "sessionID";
    public static final String SHARED_PREF_FILE = "SharedPrefFile";
    public static final String LOCATION = "location";

    SharedPreferences mPreferences;

    @Inject
    public SharedPrefs(SharedPreferences preferences) {
        this.mPreferences = preferences;
    }

    public void saveLocation(LocationParams location) {
        mPreferences.edit().putString(LOCATION, new Gson().toJson(location)).commit();
    }

    public LocationParams loadLocation() {
        return new Gson().fromJson(mPreferences.getString(LOCATION, ""), LocationParams.class);
    }

    public void saveJoke(Joke joke) {
        mPreferences.edit().putString(JOKEKEY, new Gson().toJson(joke)).commit();
    }

    public Joke loadJoke() {
        return new Gson().fromJson(mPreferences.getString(JOKEKEY, ""), Joke.class);
    }

    public void saveAccessToken(String token) {
        mPreferences.edit().putString(ACCESS_TOKEN, token).commit();
    }

    public String  loadAccessToken() {
        return mPreferences.getString(ACCESS_TOKEN, "");
    }

    public void saveSessionID(String sessionID) {
        mPreferences.edit().putString(SESSION_ID, sessionID).commit();
    }

    public String  loadSessionID() {
        return mPreferences.getString(SESSION_ID, "");
    }


    public LinkedHashMap<String, String> getProtectedHeader(){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("AHS_TOKEN", loadAccessToken());
        map.put("AHS_SESSIONID", loadSessionID());
        return map;
    }


}
