package com.bignerdranch.android.friends.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.friends.bean.ContactBean;

import java.util.ArrayList;
import java.util.List;

public class PickContactAdapter extends RecyclerView.Adapter<PickContactHolder> {

    private List<ContactBean> mContacts = new ArrayList<>();
    private RecyclerView mRecyclerView;

    @Override
    public PickContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PickContactHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class PickContactHolder extends RecyclerView.ViewHolder {

    public PickContactHolder(View itemView) {
        super(itemView);
    }
}
