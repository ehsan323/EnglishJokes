package com.app.setayesh.englishjokes.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.app.setayesh.englishjokes.Model.Config;
import com.app.setayesh.englishjokes.Model.Local.JokeDB;
import com.app.setayesh.englishjokes.Model.Local.JokeDao;
import com.app.setayesh.englishjokes.Model.Local.LocalDataSource;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    private static final String DATABASE = "database_name";

    @Provides
    @Named(DATABASE)
    String provideDatabaseName() {
        return Config.DATABASE_NAME;
    }

    @Provides
    @Singleton
    JokeDB provideJokeDB(Context context, @Named(DATABASE) String databaseName) {
        return Room.databaseBuilder(context, JokeDB.class, databaseName).build();
    }

    @Provides
    @Singleton
    JokeDao provideJokeDao(JokeDB jokeDB) {
        return jokeDB.jokeDao();
    }

    @Singleton
    @Provides
    LocalDataSource productRepository(JokeDao jokeDao, JokeDB jokeDB) {
        return new LocalDataSource(jokeDao, jokeDB);
    }
}
