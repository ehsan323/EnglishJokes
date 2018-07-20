package com.app.setayesh.englishjokes;

import android.app.Application;


import com.app.setayesh.englishjokes.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends DaggerApplication {


    @Inject
    CalligraphyConfig mCalligraphyConfig;

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
