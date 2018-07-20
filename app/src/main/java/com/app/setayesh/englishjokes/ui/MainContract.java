package com.app.setayesh.englishjokes.ui;

import android.app.Activity;

import com.app.setayesh.englishjokes.Utils.DeviceInfo;
import com.app.setayesh.englishjokes.base.IPresenter;
import com.app.setayesh.englishjokes.base.IView;
import com.app.setayesh.englishjokes.model.pojo.Joke;

public interface MainContract {

    interface View extends IView {
        void showJokesList(Joke jokes);
        void showError(String message);
        void showComplete();
        void showDeviceInfo(DeviceInfo deviceInfo);
    }

    interface Presenter<V extends View> extends IPresenter<V> {
        void loadJokesList();
        void loadDeviceInfo();
        void loadCurrentLocation(Activity activity);
        void loadSetUpLocation();

    }
}
