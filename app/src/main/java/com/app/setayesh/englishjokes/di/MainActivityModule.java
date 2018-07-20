package com.app.setayesh.englishjokes.di;

import com.app.setayesh.englishjokes.Utils.SharedPrefs;
import com.app.setayesh.englishjokes.ui.MainContract;
import com.app.setayesh.englishjokes.ui.MainPresenter;
import com.app.setayesh.englishjokes.ui.MainActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainActivityModule {

    @Binds
    public abstract MainContract.View provideView(MainActivity mainActivity);


    @Provides
    static MainContract.Presenter  providePresenter(SharedPrefs sharedPrefs) {
        return new MainPresenter(sharedPrefs);
    }
}
