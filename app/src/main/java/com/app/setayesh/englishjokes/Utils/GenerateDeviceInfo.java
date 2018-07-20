package com.app.setayesh.englishjokes.Utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import javax.inject.Inject;

public class GenerateDeviceInfo {

    Context context;

    @Inject
    public GenerateDeviceInfo(Context context) {
        this.context = context;
    }

    private String getDeviceID() {
        return Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    private String getDeviceName() {
        return Build.MODEL;
    }

    private String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    private String getBrand() {
        return Build.BRAND;
    }

    public DeviceInfo getDeviceInfo(){
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setBrand(getBrand());
        deviceInfo.setDeviceID(getDeviceID());
        deviceInfo.setOsVersion(getOsVersion());
        deviceInfo.setDeviceName(getDeviceName());
        return deviceInfo;
    }
}
