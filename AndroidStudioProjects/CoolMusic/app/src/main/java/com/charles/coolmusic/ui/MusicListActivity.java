package com.charles.coolmusic.ui;

import android.support.v4.app.Fragment;

import com.charles.coolmusic.fragment.MusicListFragment;

public class MusicListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return MusicListFragment.newInstance();
    }
}
