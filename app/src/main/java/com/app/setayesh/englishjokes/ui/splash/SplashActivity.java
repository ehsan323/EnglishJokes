package com.app.setayesh.englishjokes.ui.splash;

import android.Manifest;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.app.setayesh.englishjokes.R;
import com.app.setayesh.englishjokes.base.BaseActivity;
import com.app.setayesh.englishjokes.ui.MainActivity;

import java.util.logging.Handler;

import javax.inject.Inject;

import static com.app.setayesh.englishjokes.Utils.CurrentLocationFinder.REQUEST_PERMISSION_CODE;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    @Inject
    SplashPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mPresenter.loadCurrentLocation(this);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPresenter.loadSetUpLocation();
                    keepSplash(false);
                    Log.i("5521", "onRequestPermissionsResult:  ok perm");
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
              //      keepSplash(false);
                    Log.i("5521", "onRequestPermissionsResult:  deny  perm");
                } else {
                    Log.i("5521", "onRequestPermissionsResult:  not perm");
                }
                break;
        }
    }



    @Override
    protected void setUp() {

    }

    @Override
    public void findLocation() {

    }

    boolean isKeep = false;

    @Override
    public void showError(String message) {


    }

    @Override
    public void keepSplash(boolean keep) {
        if (!keep){
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }, 3000);
            Log.i("5521", "onRequestPermissionsResult:  handler");
        }

    }
}
