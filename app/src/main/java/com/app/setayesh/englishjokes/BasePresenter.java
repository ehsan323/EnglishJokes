package com.app.setayesh.englishjokes;

public interface BasePresenter<V> {
    void attachView(V view);
    void detach();
}
