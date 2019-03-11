package com.trio.mentor9.common.dependencyinjection.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private final Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    Fragment getFragment() {
        return this.mFragment;
    }

    @Provides
    Activity getActivity(Fragment fragment) {
        return fragment.getActivity();
    }

    @Provides
    Context getContext(Activity activity) {
        return activity;
    }
}
