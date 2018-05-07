package com.app.setayesh.englishjokes.di;

import com.app.setayesh.englishjokes.View.MainActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = ModelComponent.class, modules = MainPresenterModule.class)
public interface MainPresenterComponent {
    void inject(MainActivity mainActivity);
}
