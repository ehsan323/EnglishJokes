package com.app.setayesh.englishjokes.model;

import android.util.Log;

import com.app.setayesh.englishjokes.model.Local.LocalDataSource;
import com.app.setayesh.englishjokes.model.Remote.RemoteDataSource;
import com.app.setayesh.englishjokes.model.pojo.Joke;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class AppRepository implements AppDataContract.Repository {

    private LocalDataSource mLocalDataSource;
    private RemoteDataSource mRemoteDataSource;

    @Inject
    public AppRepository(LocalDataSource localDataSource, RemoteDataSource mRemoteDataSource) {
        this.mLocalDataSource = localDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }

    @Override
    public Flowable<Joke> getAllJokes() {

        Flowable<Joke> remote = mRemoteDataSource.getAllJokes()
                .subscribeOn(Schedulers.io())
                .doOnNext(jokes -> {
                    mLocalDataSource.deleteAllJokes();
                    mLocalDataSource.insertAllJokes(jokes);
                });

        Flowable<Joke> local =mLocalDataSource.getAllJokes().subscribeOn(Schedulers.io());

          return Flowable.mergeDelayError(local, remote).filter(joke -> joke != null);
    }

    @Override
    public void destroyInstance() {
        mLocalDataSource.destroyInstance();
    }
}
