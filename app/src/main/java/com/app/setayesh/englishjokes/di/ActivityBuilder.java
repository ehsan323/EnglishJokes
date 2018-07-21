package com.app.setayesh.englishjokes.di;

import com.app.setayesh.englishjokes.ui.MainActivity;
import com.app.setayesh.englishjokes.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

@Module(includes = AndroidInjectionModule.class)
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();


    @ActivityScope
    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();
}
