package com.app.setayesh.englishjokes.base;

import android.view.View;

import com.app.setayesh.englishjokes.Utils.SharedPrefs;

public abstract class BasePresenter<V extends IView> implements IPresenter<V> {

    public abstract View getView();
    public abstract SharedPrefs getSharedPrefs();


    @Override
    public void handleApiError() {

    }

    @Override
    public void handleLoggedOut() {
        getSharedPrefs().saveAccessToken(null);
    }

    public boolean isViewAttached() {
        return getView() != null;
    }


}
