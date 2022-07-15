package com.dreamempire.goldclicker;

import android.app.Application;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.yandex.mobile.ads.common.InitializationListener;
import com.yandex.mobile.ads.common.MobileAds;

public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        /*FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(30)
                .setFetchTimeoutInSeconds(3600)
                .build();*/
        //mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        MobileAds.initialize(this, () -> {});
    }
}
