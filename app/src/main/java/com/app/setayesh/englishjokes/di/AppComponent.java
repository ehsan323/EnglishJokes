package com.app.setayesh.englishjokes.di;


import com.app.setayesh.englishjokes.App;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules =
              {AndroidSupportInjectionModule.class,
                AppModule.class,
                ActivityBuilder.class})

public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }
}
