package com.app.setayesh.englishjokes.Utils;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;



public class CurrentLocationFinder  implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    public static final int REQUEST_PERMISSION_CODE = 200;

    private Activity activity;
    private GoogleApiClient mGoogleApiClient;
    private OnLocationUpdateListener onLocationUpdateListener;
    private boolean isLocationAvailable;


    public CurrentLocationFinder(Activity activity, OnLocationUpdateListener onLocationUpdateListener) {
        this.activity = activity;
        buildGoogleApiClient();
        this.onLocationUpdateListener = onLocationUpdateListener;
    }

    public synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();

    }

    public void setupLocationManager() {
        if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);

            mFusedLocationClient.getLocationAvailability().addOnCompleteListener( activity, task -> {
                if (task.isSuccessful() && task.getResult() != null) {
                    LocationAvailability lastLocation = task.getResult();
                    isLocationAvailable = lastLocation.isLocationAvailable();
                }
            });


            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener( activity, location -> {
                        if (isLocationAvailable) {
                            if (location != null) {
                                storeLocation(location);
                            }
                        }

                    });

            LocationCallback mLocationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    for (Location location : locationResult.getLocations()) {
                        storeLocation(location);
                    }
                }
            };
            LocationRequest mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(3000); //5 seconds
            mLocationRequest.setFastestInterval(1000); //3 seconds
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setExpirationTime(30000);

            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null);

        } else {
            ActivityCompat.requestPermissions( activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_CODE);
            onLocationUpdateListener.onKeepSplash(true);
        }


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        setupLocationManager();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public interface OnLocationUpdateListener {
        void onLocationChange(Location location);
        void onKeepSplash(boolean isKeep);
    }

   /*
    public void setOnLocationUpdateListener(OnLocationUpdateListener onLocationUpdateListener) {
        this.onLocationUpdateListener = onLocationUpdateListener;
    }
    */

    private void storeLocation(Location location) {
        if (mGoogleApiClient.isConnected() && onLocationUpdateListener != null) {
            onLocationUpdateListener.onLocationChange(location);
        }
    }

}



