package com.bignerdranch.android.friends.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

import com.bignerdranch.android.friends.fragment.WebsiteFragment;

/**
 * 网页显示activity
 */
public class WebsiteActivity extends SingleFragmentActivity {

    private WebsiteFragment mFragment;

    public static Intent newIntent(Context context, Uri websiteUri) {
        Intent i = new Intent(context, WebsiteActivity.class);
        i.setData(websiteUri);
        return i;
    }

    @Override
    protected Fragment createFragment() {
        mFragment =  WebsiteFragment.newInstance(getIntent().getData());
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
