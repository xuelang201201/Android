package com.bignerdranch.android.friends.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bignerdranch.android.friends.fragment.CallLogFragment;
import com.bignerdranch.android.friends.fragment.ContactsFragment;
import com.bignerdranch.android.friends.fragment.ConversationFragment;
import com.bignerdranch.android.friends.fragment.DialFragment;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragments;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
        mFragments.add(new CallLogFragment());
        mFragments.add(new ContactsFragment());
        mFragments.add(new ConversationFragment());
        mFragments.add(new DialFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
