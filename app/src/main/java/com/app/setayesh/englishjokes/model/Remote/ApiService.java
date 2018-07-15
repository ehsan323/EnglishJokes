package com.app.setayesh.englishjokes.model.Remote;

import com.app.setayesh.englishjokes.model.pojo.Joke;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("10/")
    Flowable<Joke> getJokesFromServer();
}
