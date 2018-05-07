package com.app.setayesh.englishjokes.Model.Local;

import android.arch.persistence.room.TypeConverter;

import com.app.setayesh.englishjokes.Model.pojo.Value;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JokeConverter {

    @TypeConverter
    public List<Value> changeToList(String joke) {
        if (joke == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Value>>() {
        }.getType();
        return gson.fromJson(joke, type);
    }

    @TypeConverter
    public String changeToString(List<Value> list) {
        if (list == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Value>>() {
        }.getType();
        return gson.toJson(list, type);
    }
}
