package com.trio.mentor9.common.dependencyinjection.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.trio.mentor9.common.Constant;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application getApplication() {
        return mApplication;
    }

    @Provides
    SharedPreferences getSharedPreferences(Application application) {
        return application.getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    @Provides
    SharedPreferences.Editor getEditor(SharedPreferences sharedPreferences) {
        return sharedPreferences.edit();
    }

}
