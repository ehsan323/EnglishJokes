package com.app.setayesh.englishjokes.base;

public interface IPresenter<V> {
    void start();
    void handleApiError();
    void handleLoggedOut();
    void attachView(V view);
    void detach();
}
