package com.app.setayesh.englishjokes.ui;

import android.app.Activity;
import android.location.Location;
import android.util.Log;
import android.view.View;

import com.app.setayesh.englishjokes.Utils.CurrentLocationFinder;
import com.app.setayesh.englishjokes.Utils.GenerateDeviceInfo;
import com.app.setayesh.englishjokes.Utils.LocationParams;
import com.app.setayesh.englishjokes.Utils.SharedPrefs;
import com.app.setayesh.englishjokes.base.BasePresenter;
import com.app.setayesh.englishjokes.model.AppRepository;
import com.app.setayesh.englishjokes.model.pojo.Joke;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{

     private MainContract.View mView;
    @Inject
    public AppRepository mRepository;
    @Inject
    public CompositeDisposable compositeDisposable;
    @Inject
    SharedPrefs sharedPrefs;
    @Inject
    GenerateDeviceInfo deviceInfo;

    private CurrentLocationFinder locationFinder;

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
                        sharedPrefs.saveJoke(joke);

                        sharedPrefs.saveAccessToken("123FKKJUY8DE59FFG");
                        sharedPrefs.saveSessionID("2366");
                    }

                    @Override
                    public void onError(Throwable t) {
                        mView.showError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mView.showComplete();
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void loadDeviceInfo() {
        mView.showDeviceInfo(deviceInfo.getDeviceInfo());
    }

    @Override
    public void loadCurrentLocation(Activity activity) {
        locationFinder = new CurrentLocationFinder(activity);
        locationFinder.setupLocationManager();
        locationFinder.setOnLocationUpdateListener(location -> {
            LocationParams locationParams = new LocationParams();
            locationParams.setLatitude(String.valueOf(location.getLatitude()));
            locationParams.setLongitude(String.valueOf(location.getLongitude()));
            sharedPrefs.saveLocation(locationParams);
            Log.i("8852", "onLocationChange: " + location.getLongitude());
        });
    }

    @Override
    public void loadSetUpLocation() {
        locationFinder.setupLocationManager();
    }


    @Override
    public MainContract.View getView() {
        return mView;
    }

    @Override
    public SharedPrefs getSharedPrefs() {
        return sharedPrefs;
    }

    @Override
    public void start() {
        loadJokesList();
        loadDeviceInfo();
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
