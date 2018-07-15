package com.app.setayesh.englishjokes.ui;

import com.app.setayesh.englishjokes.base.BasePresenter;
import com.app.setayesh.englishjokes.base.BaseView;
import com.app.setayesh.englishjokes.model.pojo.Joke;

public interface MainContract {

    interface View extends BaseView {
        void showJokesList(Joke jokes);
        void showError(String message);
        void showComplete();
    }

    interface Presenter extends BasePresenter<View> {
        void loadJokesList();
    }
}
