package com.app.setayesh.englishjokes.Model.Local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.app.setayesh.englishjokes.Model.pojo.Joke;

@Database(entities = Joke.class,  version = 1)
@TypeConverters({JokeConverter.class})
public abstract class JokeDB extends RoomDatabase {
    public abstract JokeDao jokeDao();
}
