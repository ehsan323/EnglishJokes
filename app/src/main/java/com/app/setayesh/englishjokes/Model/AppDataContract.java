package com.app.setayesh.englishjokes.Model;

import com.app.setayesh.englishjokes.Model.pojo.Joke;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface AppDataContract {

    Flowable<Joke> getAllJokes();
    void insertAllJokes(Joke joke);
    void deleteAllJokes();
}
