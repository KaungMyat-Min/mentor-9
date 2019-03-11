package com.trio.mentor9.common.dependencyinjection.application;

import com.trio.mentor9.common.dependencyinjection.fragment.FragmentComponent;
import com.trio.mentor9.common.dependencyinjection.fragment.FragmentModule;
import com.trio.mentor9.common.dependencyinjection.presenter.PresenterComponent;
import com.trio.mentor9.common.dependencyinjection.presenter.PresenterModule;

import dagger.Component;

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    PresenterComponent getPresenterComponent(PresenterModule presenterModule);


}
