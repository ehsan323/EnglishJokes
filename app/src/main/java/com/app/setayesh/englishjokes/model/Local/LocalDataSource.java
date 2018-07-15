package com.app.setayesh.englishjokes.model.Local;

import com.app.setayesh.englishjokes.model.AppDataContract;
import com.app.setayesh.englishjokes.model.pojo.Joke;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LocalDataSource implements AppDataContract.Local {

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
        myExecutor.execute(() -> jokeDao.insert(joke));
    }

    @Override
    public void deleteAllJokes() {

        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(() -> jokeDao.deleteAllJokes());
    }

    @Override
    public void destroyInstance()  {
        jokeDB = null;
    }
}
