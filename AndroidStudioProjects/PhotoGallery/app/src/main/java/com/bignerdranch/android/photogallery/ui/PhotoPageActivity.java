package com.bignerdranch.android.photogallery.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

import com.bignerdranch.android.photogallery.fragment.PhotoPageFragment;

/**
 * 网页显示activity
 */
public class PhotoPageActivity extends SingleFragmentActivity {

    private PhotoPageFragment mFragment;

    public static Intent newIntent(Context context, Uri photoPageUri) {
        Intent i = new Intent(context, PhotoPageActivity.class);
        i.setData(photoPageUri);
        return i;
    }

    @Override
    protected Fragment createFragment() {
        mFragment = PhotoPageFragment.newInstance(getIntent().getData());
        return mFragment;
    }

    /**
     * 按返回键逐级返回
     */
    @Override
    public void onBackPressed() {
        if (mFragment.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
