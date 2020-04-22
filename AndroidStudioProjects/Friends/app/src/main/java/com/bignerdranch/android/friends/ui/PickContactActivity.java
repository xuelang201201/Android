package com.bignerdranch.android.friends.ui;

import android.support.v4.app.Fragment;

import com.bignerdranch.android.friends.fragment.PickContactFragment;

public class PickContactActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return PickContactFragment.newInstance();
    }
}
