package com.app.setayesh.englishjokes.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.app.setayesh.englishjokes.R;
import com.app.setayesh.englishjokes.Utils.DeviceInfo;
import com.app.setayesh.englishjokes.base.BaseActivity;
import com.app.setayesh.englishjokes.model.pojo.Joke;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements MainContract.View {


    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Inject
    MainPresenter mPresenter;
    @BindView(R.id.nav)
    NavigationView nav;
    @BindView(R.id.drawer)
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_rounded_edges);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    //    dialog.show();


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
