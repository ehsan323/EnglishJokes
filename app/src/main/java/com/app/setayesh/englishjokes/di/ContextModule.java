package com.app.setayesh.englishjokes.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context application) {
        this.context = application;
    }

    @Provides
    @Singleton
    public Context providesApplication() {
        return context;
    }
}
