package com.app.setayesh.englishjokes.di;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import com.app.setayesh.englishjokes.App;
import com.app.setayesh.englishjokes.R;
import com.app.setayesh.englishjokes.Utils.CurrentLocationFinder;
import com.app.setayesh.englishjokes.Utils.GenerateDeviceInfo;
import com.app.setayesh.englishjokes.Utils.SharedPrefs;
import com.app.setayesh.englishjokes.model.AppDataContract;
import com.app.setayesh.englishjokes.model.AppRepository;
import com.app.setayesh.englishjokes.model.Config;
import com.app.setayesh.englishjokes.model.Local.JokeDB;
import com.app.setayesh.englishjokes.model.Local.JokeDao;
import com.app.setayesh.englishjokes.model.Local.LocalDataSource;
import com.app.setayesh.englishjokes.model.Remote.ApiService;
import com.app.setayesh.englishjokes.model.Remote.RemoteDataSource;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static com.app.setayesh.englishjokes.Utils.SharedPrefs.SHARED_PREF_FILE;

@Module()
public class AppModule {

    private static final String BASE_URL = "http://api.icndb.com/jokes/random/";
    private static final String DATABASE = "database_name";


    @Provides
    @Singleton
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
     SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    SharedPrefs provideSharedPrefs(SharedPreferences sharedPreferences){
        return new SharedPrefs(sharedPreferences);
    }

    @Provides
    @Singleton
    GenerateDeviceInfo provideDeviceID(Context context){
        return new GenerateDeviceInfo(context);
    }

    @Singleton
    @Provides
     CompositeDisposable getCompositeDisposable() {
        return new CompositeDisposable();
    }

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

    @Singleton
    @Provides
    AppDataContract.Repository getRepositoryData(LocalDataSource localDataSource, RemoteDataSource remoteDataSource){
        return new AppRepository(localDataSource, remoteDataSource);
    }

    @Provides
    @Named(DATABASE)
    String provideDatabaseName() {
        return Config.DATABASE_NAME;
    }

    @Provides
    @Singleton
    JokeDB provideJokeDB(Context context, @Named(DATABASE) String databaseName) {
        return Room.databaseBuilder(context, JokeDB.class, databaseName).build();
    }

    @Provides
    @Singleton
    JokeDao provideJokeDao(JokeDB jokeDB) {
        return jokeDB.jokeDao();
    }

    @Singleton
    @Provides
    AppDataContract.Local productRepository(JokeDao jokeDao, JokeDB jokeDB) {
        return new LocalDataSource(jokeDao, jokeDB);
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
    Retrofit provideRetrofit( OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    AppDataContract.Remote provideRemoteDataSource(ApiService myApi) {
        return new RemoteDataSource(myApi);
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

}
