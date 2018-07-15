package com.app.setayesh.englishjokes.model.Local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.app.setayesh.englishjokes.model.Config;
import com.app.setayesh.englishjokes.model.pojo.Joke;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface JokeDao {

    @Query("SELECT * FROM  "+ Config.QUESTION_TABLE_NAME )
    Flowable<Joke> getAllJokes();

    @Query("SELECT COUNT(*) from "+ Config.QUESTION_TABLE_NAME)
    int countJokes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Joke joke);

    @Query("DELETE FROM "+ Config.QUESTION_TABLE_NAME )
    void deleteAllJokes();
}
