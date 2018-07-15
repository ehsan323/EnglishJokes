package com.app.setayesh.englishjokes.base;

public interface BasePresenter<V> {
    void start();
    void attachView(V view);
    void detach();
}
