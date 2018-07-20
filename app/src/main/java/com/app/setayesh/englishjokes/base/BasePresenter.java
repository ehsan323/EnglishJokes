package com.app.setayesh.englishjokes.base;

import com.app.setayesh.englishjokes.Utils.SharedPrefs;

import javax.inject.Inject;

public class BasePresenter<V extends IView> implements IPresenter<V> {

    @Inject
    SharedPrefs sharedPrefs;

    private V mIView;

    @Inject
    public BasePresenter(SharedPrefs sharedPrefs) {
        this.sharedPrefs = sharedPrefs;
    }


    @Override
    public void start() {

    }

    @Override
    public void handleApiError() {

    }

    @Override
    public void handleLoggedOut() {
        getSharedPrefs().saveAccessToken(null);
    }

    public boolean isViewAttached() {
        return mIView != null;
    }

    public V getIView() {
        return mIView;
    }

    public SharedPrefs getSharedPrefs() {
        return sharedPrefs;
    }

    @Override
    public void attachView(V view) {
        this.mIView = view;
    }

    @Override
    public void detach() {
      mIView = null;
    }
}
