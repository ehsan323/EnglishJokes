package com.app.setayesh.englishjokes.di;

import com.app.setayesh.englishjokes.MainContract;
import com.app.setayesh.englishjokes.Model.AppRepository;
import com.app.setayesh.englishjokes.Presenter.MainPresenter;
import com.app.setayesh.englishjokes.View.MainActivity;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class MainPresenterModule {

    private MainContract.View mView;

    public MainPresenterModule(MainContract.View mView) {
        this.mView = mView;
    }

   @Provides
   @ActivityScope
    MainContract.View getmView(){
        return mView;
    }

    @Provides
    @ActivityScope
    CompositeDisposable getCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @ActivityScope
    MainContract.Presenter getMainPresenter(AppRepository repository, CompositeDisposable compositeDisposable){
        return new MainPresenter(mView, repository, compositeDisposable);
    }


}
