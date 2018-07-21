package com.app.setayesh.englishjokes.ui.splash;

import android.app.Activity;

import com.app.setayesh.englishjokes.Utils.DeviceInfo;
import com.app.setayesh.englishjokes.base.IPresenter;
import com.app.setayesh.englishjokes.base.IView;
import com.app.setayesh.englishjokes.model.pojo.Joke;
import com.app.setayesh.englishjokes.ui.MainContract;

public interface SplashContract {

    interface View extends IView {
        void findLocation();
        void showError(String message);
        void keepSplash(boolean keep);

    }

    interface Presenter extends IPresenter<SplashContract.View> {
        void loadCurrentLocation(Activity activity);
        void loadSetUpLocation();

    }

}
