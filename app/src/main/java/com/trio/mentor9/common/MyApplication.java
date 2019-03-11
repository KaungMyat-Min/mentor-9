package com.trio.mentor9.common;

import android.app.Application;

import com.trio.mentor9.common.dependencyinjection.application.ApplicationComponent;
import com.trio.mentor9.common.dependencyinjection.application.ApplicationModule;
import com.trio.mentor9.common.dependencyinjection.application.DaggerApplicationComponent;

public class MyApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
