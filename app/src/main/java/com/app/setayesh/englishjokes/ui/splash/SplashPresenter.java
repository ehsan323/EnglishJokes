package com.app.setayesh.englishjokes.ui.splash;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.app.setayesh.englishjokes.Utils.CurrentLocationFinder;
import com.app.setayesh.englishjokes.Utils.LocationParams;
import com.app.setayesh.englishjokes.Utils.SharedPrefs;
import com.app.setayesh.englishjokes.base.BasePresenter;
import com.app.setayesh.englishjokes.ui.MainActivity;

import java.util.logging.Handler;

import javax.inject.Inject;

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    private SplashContract.View mView;

    CurrentLocationFinder locationFinder;

   @Inject
    public SplashPresenter(SplashContract.View mView) {
        this.mView = mView;
    }


    @Override
    public SplashContract.View getView() {
        return mView;
    }

    @Override
    public SharedPrefs getSharedPrefs() {
        return null;
    }

    @Override
    public void loadCurrentLocation(Activity activity) {
        locationFinder = new CurrentLocationFinder(activity, new CurrentLocationFinder.OnLocationUpdateListener() {
            @Override
            public void onLocationChange(Location location) {

            }

            @Override
            public void onKeepSplash(boolean isKeep) {
              mView.keepSplash(isKeep);
            }
        });

        locationFinder.setupLocationManager();

    //   mView.showError("");
    }

    @Override
    public void loadSetUpLocation() {
        locationFinder.setupLocationManager();
    }

    @Override
    public void start() {

    }



    @Override
    public void attachView(SplashContract.View view) {
        this.mView = view;
    }

    @Override
    public void detach() {

    }
}
