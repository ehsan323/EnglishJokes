package com.app.setayesh.englishjokes.model.Local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.app.setayesh.englishjokes.model.pojo.Joke;

@Database(entities = Joke.class,  version = 1)
@TypeConverters({JokeConverter.class})
public abstract class JokeDB extends RoomDatabase {
    public abstract JokeDao jokeDao();
}
