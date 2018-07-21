package com.app.setayesh.englishjokes.di;

import com.app.setayesh.englishjokes.ui.MainActivity;
import com.app.setayesh.englishjokes.ui.MainContract;
import com.app.setayesh.englishjokes.ui.MainPresenter;
import com.app.setayesh.englishjokes.ui.splash.SplashActivity;
import com.app.setayesh.englishjokes.ui.splash.SplashContract;
import com.app.setayesh.englishjokes.ui.splash.SplashPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public  abstract class SplashActivityModule {

    @Binds
    public abstract SplashContract.View provideView(SplashActivity splashActivity);


    @Provides
    static SplashContract.Presenter  providePresenter( SplashContract.View view) {
        return new SplashPresenter(view);
    }
}
