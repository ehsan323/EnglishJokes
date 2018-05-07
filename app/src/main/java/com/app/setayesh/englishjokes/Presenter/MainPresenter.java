package com.app.setayesh.englishjokes.Presenter;

import com.app.setayesh.englishjokes.MainContract;
import com.app.setayesh.englishjokes.Model.AppRepository;
import com.app.setayesh.englishjokes.Model.pojo.Joke;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private AppRepository mRepository;
    private CompositeDisposable compositeDisposable;

    @Inject
    public MainPresenter(MainContract.View mView, AppRepository mRepository, CompositeDisposable compositeDisposable) {
        this.mView = mView;
        this.mRepository = mRepository;
        this.compositeDisposable = compositeDisposable;
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
    public void attachView(MainContract.View view) {
        this.mView = view;
    }

    @Override
    public void detach() {
        if (!compositeDisposable.isDisposed()){
         //   compositeDisposable.dispose();
            compositeDisposable = null;
        }
        mView = null;
        mRepository.destroyInstance();
    }
}
