package com.bignerdranch.android.lifeshow.ui;

import android.support.v4.app.Fragment;

import com.bignerdranch.android.lifeshow.fragment.LifeShowFragment;

public class LifeShowActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return LifeShowFragment.newInstance();
    }
}
