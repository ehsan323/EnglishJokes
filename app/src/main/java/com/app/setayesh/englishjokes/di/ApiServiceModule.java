package com.app.setayesh.englishjokes.di;


import com.app.setayesh.englishjokes.Model.AppDataContract;
import com.app.setayesh.englishjokes.Model.Remote.ApiService;
import com.app.setayesh.englishjokes.Model.Remote.RemoteDataSource;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiServiceModule {

    private static final String BASE_URL = "http://api.icndb.com/jokes/random/";

    @Provides
    String provideBaseUrl() {
        return BASE_URL;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }


    @Provides
    @Singleton
    Converter.Factory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(String baseUrl, OkHttpClient okHttpClient, Converter.Factory converterFactory, RxJava2CallAdapterFactory rxAdapter) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(converterFactory)
                .build();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    AppDataContract provideRemoteDataSource(ApiService myApi) {
        return new RemoteDataSource(myApi);
    }
}
