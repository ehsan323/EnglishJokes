package com.app.setayesh.englishjokes.model;

import com.app.setayesh.englishjokes.model.pojo.Joke;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface AppDataContract {

    interface Local{
        Flowable<Joke> getAllJokes();
        void insertAllJokes(Joke joke);
        void deleteAllJokes();
        void destroyInstance();
    }

    interface Remote{
        Flowable<Joke> getAllJokes();
    }

    interface Repository{
        Flowable<Joke> getAllJokes();
        void destroyInstance();
    }


}
