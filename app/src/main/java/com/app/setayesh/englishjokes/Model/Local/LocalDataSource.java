package com.app.setayesh.englishjokes.Model.Local;

import com.app.setayesh.englishjokes.Model.AppDataContract;
import com.app.setayesh.englishjokes.Model.pojo.Joke;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LocalDataSource implements AppDataContract {

    private JokeDao jokeDao;
    private JokeDB jokeDB;

    @Inject
    public LocalDataSource(JokeDao jokeDao,JokeDB jokeDB) {
        this.jokeDao = jokeDao;
        this.jokeDB = jokeDB;
    }

    @Override
    public Flowable<Joke> getAllJokes() {
        return jokeDao.getAllJokes()
                .subscribeOn(Schedulers.io())
                .onBackpressureBuffer()
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void insertAllJokes(final Joke joke) {

        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                jokeDao.insert(joke);
            }
        });


    }

    @Override
    public void deleteAllJokes() {

        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                jokeDao.deleteAllJokes();
            }
        });
    }

    public void destroyInstance()  {
        jokeDB = null;
    }
}
