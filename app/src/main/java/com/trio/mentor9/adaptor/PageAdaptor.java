package com.trio.mentor9.adaptor;

import com.trio.mentor9.common.Constant;
import com.trio.mentor9.frag.FragEngText;
import com.trio.mentor9.frag.FragFacts;
import com.trio.mentor9.frag.FragImage;
import com.trio.mentor9.frag.FragQueShow;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PageAdaptor extends FragmentStatePagerAdapter {
    private final int total_tab = 3;
    private int unitNO;
    private GetValues values;
    private Context context;

    public PageAdaptor(int unitNO, FragmentManager fm, GetValues values, Context context) {
        super(fm);
        this.unitNO = unitNO;
        this.values = values;
        this.context = context;
    }

    @Override
    public Fragment getItem(int arg0) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.UNIT_NO, unitNO);
        Fragment fragment = null;
        switch (arg0 + 1) {
            case 0:
                fragment = new FragFacts();
                break;
            case 1:
                fragment = new FragQueShow();
                break;

            case 2:
                fragment = new FragEngText();
                break;
            case 3:
                fragment = new FragImage();
                break;
        }
        if (fragment != null) {
            fragment.setArguments(bundle);
        }
        return fragment;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position + 1) {
            case 0:
                return "Strange Facts";
            case 1:
                return "Q&A";
            case 2:
                return "English";
            case 3:
                return "Photos";
        }
        return null;

    }

    @Override
    public int getCount() {
        return total_tab;
    }
}
