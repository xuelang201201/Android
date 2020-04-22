package com.bignerdranch.android.friends.ui;

import android.support.v4.app.Fragment;

import com.bignerdranch.android.friends.fragment.SearchContactFragment;

public class SearchContactActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SearchContactFragment.newInstance();
    }
}
