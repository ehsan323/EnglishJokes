package com.app.setayesh.englishjokes.ui;

import com.app.setayesh.englishjokes.model.AppRepository;
import com.app.setayesh.englishjokes.model.pojo.Joke;
import com.app.setayesh.englishjokes.ui.MainContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    @Inject
    public AppRepository mRepository;
    @Inject
    public CompositeDisposable compositeDisposable;

    @Inject
    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void loadJokesList() {

        Disposable disposable = mRepository.getAllJokes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<Joke>() {
                    @Override
                    public void onNext(Joke joke) {
                        mView.showJokesList(joke);
                    }

                    @Override
                    public void onError(Throwable t) {
                        mView.showError(t.toString());
                    }

                    @Override
                    public void onComplete() {
                        mView.showComplete();
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void start() {
        loadJokesList();
    }

    @Override
    public void attachView(MainContract.View view) {
        this.mView = view;
    }

    @Override
    public void detach() {
        mView = null;
    }
}
