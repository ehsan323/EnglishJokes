package com.app.setayesh.englishjokes.di;

import com.app.setayesh.englishjokes.Model.AppDataContract;
import com.app.setayesh.englishjokes.Model.Local.LocalDataSource;
import com.app.setayesh.englishjokes.Model.Remote.RemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    AppDataContract.Remote getRemoteData(RemoteDataSource remoteDataSource){
        return remoteDataSource;
    }

    @Singleton
    @Provides
    AppDataContract.Local getLocalData(LocalDataSource localDataSource){
        return localDataSource;
    }
}
