package com.trio.mentor9.common.dependencyinjection.presenter;

import com.trio.mentor9.common.dependencyinjection.fragment.FragmentComponent;
import com.trio.mentor9.common.dependencyinjection.fragment.FragmentModule;

import dagger.Subcomponent;

@Subcomponent(modules = {PresenterModule.class})
public interface PresenterComponent {
    FragmentComponent getFragmentComponent(FragmentModule fragmentModule);

}


