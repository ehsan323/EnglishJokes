package com.app.setayesh.englishjokes.di;

import com.app.setayesh.englishjokes.Model.AppRepository;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = { ApiServiceModule.class, RepositoryModule.class, DatabaseModule.class, ContextModule.class})
public interface ModelComponent {
    AppRepository getRepository();
}
