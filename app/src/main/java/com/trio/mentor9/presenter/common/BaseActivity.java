package com.trio.mentor9.presenter.common;

import android.support.v7.app.AppCompatActivity;

import com.trio.mentor9.common.MyApplication;
import com.trio.mentor9.common.dependencyinjection.application.ApplicationComponent;
import com.trio.mentor9.common.dependencyinjection.presenter.PresenterComponent;
import com.trio.mentor9.common.dependencyinjection.presenter.PresenterModule;

public class BaseActivity extends AppCompatActivity {
    public PresenterComponent getPresenterComponent() {
        return getApplicationComponent().getPresenterComponent(new PresenterModule(this));
    }

    private ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }

}
