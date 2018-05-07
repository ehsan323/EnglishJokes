package com.app.setayesh.englishjokes;

import com.app.setayesh.englishjokes.Model.pojo.Joke;

public interface MainContract {

    interface View {
        void showJokesList(Joke jokes);
        void showError(String message);
        void showComplete();
    }

    interface Presenter extends BasePresenter<View> {
        void loadJokesList();
    }
}
