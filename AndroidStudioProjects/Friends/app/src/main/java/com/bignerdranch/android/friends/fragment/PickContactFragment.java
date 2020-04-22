package com.bignerdranch.android.friends.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.adapter.PickContactAdapter;
import com.bignerdranch.android.friends.bean.ContactBean;

import java.util.ArrayList;
import java.util.List;

public class PickContactFragment extends Fragment {

    private RecyclerView mPickContactRecyclerView;
    private PickContactAdapter mAdapter;
    private List<ContactBean> mContacts = new ArrayList<>();

    public static PickContactFragment newInstance() {
        return new PickContactFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pick_contact, container, false);
        initView(v);
        mPickContactRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setupAdapter();
        return v;
    }

    private void initView(View v) {
        mPickContactRecyclerView = (RecyclerView) v
                .findViewById(R.id.fragment_list_item_pick_contact_recycler_view);

    }

    private void setupAdapter() {
        //mAdapter = new PickContactAdapter(getActivity(), mContacts);
        mPickContactRecyclerView.setAdapter(mAdapter);
    }
}
