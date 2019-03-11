package com.trio.mentor9.common.dependencyinjection.presenter;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    private final Activity mActivity;

    public PresenterModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity getActivity() {
        return this.mActivity;
    }

    @Provides
    Context getContext(Activity activity) {
        return activity;
    }
}
