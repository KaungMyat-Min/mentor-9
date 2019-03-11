package com.trio.mentor9.frag.common;

import android.support.v4.app.Fragment;

import com.trio.mentor9.common.dependencyinjection.application.ApplicationComponent;
import com.trio.mentor9.common.dependencyinjection.fragment.FragmentComponent;
import com.trio.mentor9.common.dependencyinjection.fragment.FragmentModule;
import com.trio.mentor9.common.dependencyinjection.presenter.PresenterComponent;
import com.trio.mentor9.presenter.common.BaseActivity;

public class BaseFragment extends Fragment {

    protected FragmentComponent getFragmentComponent() {
        return getPresenterComponent().getFragmentComponent(new FragmentModule(this));
    }

    private PresenterComponent getPresenterComponent() {
        return ((BaseActivity) getActivity()).getPresenterComponent();
    }
}
