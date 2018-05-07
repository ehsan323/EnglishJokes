package com.app.setayesh.englishjokes.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.app.setayesh.englishjokes.MainContract;
import com.app.setayesh.englishjokes.Model.pojo.Joke;
import com.app.setayesh.englishjokes.Model.pojo.Value;
import com.app.setayesh.englishjokes.Presenter.MainPresenter;
import com.app.setayesh.englishjokes.R;
import com.app.setayesh.englishjokes.di.ApiServiceModule;
import com.app.setayesh.englishjokes.di.ContextModule;
import com.app.setayesh.englishjokes.di.DaggerMainPresenterComponent;
import com.app.setayesh.englishjokes.di.DaggerModelComponent;
import com.app.setayesh.englishjokes.di.MainPresenterComponent;
import com.app.setayesh.englishjokes.di.MainPresenterModule;
import com.app.setayesh.englishjokes.di.ModelComponent;


import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Inject
    MainPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ModelComponent modelComponent = DaggerModelComponent.builder()
                .apiServiceModule(new ApiServiceModule())
                .contextModule(new ContextModule(this))
                .build();

        MainPresenterComponent presenterComponent = DaggerMainPresenterComponent.builder()
                .modelComponent(modelComponent)
                .mainPresenterModule(new MainPresenterModule(this))
                .build();

        presenterComponent.inject(this);

        mPresenter.loadJokesList();
    }

    @Override
    public void showJokesList(Joke jokes) {
        JokesAdapter adapter = new JokesAdapter(this, jokes.getValue());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }


}
