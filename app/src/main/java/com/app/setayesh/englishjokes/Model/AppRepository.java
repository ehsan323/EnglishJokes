package com.app.setayesh.englishjokes.Model;

import android.util.Log;

import com.app.setayesh.englishjokes.Model.Local.LocalDataSource;
import com.app.setayesh.englishjokes.Model.Remote.RemoteDataSource;
import com.app.setayesh.englishjokes.Model.pojo.Joke;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class AppRepository implements AppDataContract {

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
                .doOnNext(new Consumer<Joke>() {
                    @Override
                    public void accept(Joke jokes) throws Exception {
                        mLocalDataSource.deleteAllJokes();
                        mLocalDataSource.insertAllJokes(jokes);
                    }
                });

        Flowable<Joke> local =mLocalDataSource.getAllJokes().subscribeOn(Schedulers.io());

          return Flowable.mergeDelayError(local, remote).filter(new Predicate<Joke>() {
            @Override
            public boolean test(Joke joke) throws Exception {
                return joke != null;
            }
        });
    }

    @Override
    public void insertAllJokes(Joke joke) {

    }

    @Override
    public void deleteAllJokes() {

    }

    public void destroyInstance() {
        mLocalDataSource.destroyInstance();
    }



    /*
    @Override
    public void getData() {
        mRemoteDataSource.findItems(new Remote.setOnArrayListener() {
            @Override
            public void addArrayListener(Joke jokes) {
              mLocalDataSource.insertAllJokes(jokes);
                Log.i("625", "j: " + jokes.getValue().size());
            }

            @Override
            public void addBooleanListener(boolean isChecked) {

            }
        });
    }

    @Override
    public Joke getAllJokes() {
        return mLocalDataSource.getAllJokes();
    }
     */
}
