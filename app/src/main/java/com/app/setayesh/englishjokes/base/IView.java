package com.app.setayesh.englishjokes.base;

import android.support.annotation.StringRes;

public interface IView {

    void showLoading();

    void hideLoading();

    void openActivityOnTokenExpire();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    void hideKeyboard();

}
