package com.app.setayesh.englishjokes.ui;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.app.setayesh.englishjokes.Utils.CurrentLocationFinder;
import com.app.setayesh.englishjokes.Utils.DeviceInfo;
import com.app.setayesh.englishjokes.Utils.LocationParams;
import com.app.setayesh.englishjokes.Utils.SharedPrefs;
import com.app.setayesh.englishjokes.base.BaseActivity;
import com.app.setayesh.englishjokes.model.pojo.Joke;
import com.app.setayesh.englishjokes.R;


import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

import static com.app.setayesh.englishjokes.Utils.CurrentLocationFinder.REQUEST_PERMISSION_CODE;


public class MainActivity extends BaseActivity  implements MainContract.View {


    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Inject
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter.start();
        mPresenter.loadCurrentLocation(this);

        showMessage("123");
    }

    @Override
    protected void setUp() {

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
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDeviceInfo(DeviceInfo deviceInfo) {
        Toast.makeText(this, "" + deviceInfo.getDeviceID(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }





}
