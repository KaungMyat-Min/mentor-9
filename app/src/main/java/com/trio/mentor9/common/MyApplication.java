package com.trio.mentor9.common;

import android.app.Application;

import com.trio.mentor9.common.dependencyinjection.application.ApplicationComponent;
import com.trio.mentor9.common.dependencyinjection.application.ApplicationModule;
import com.trio.mentor9.common.dependencyinjection.application.DaggerApplicationComponent;

import io.realm.Realm;

public class MyApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        Realm.init(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
