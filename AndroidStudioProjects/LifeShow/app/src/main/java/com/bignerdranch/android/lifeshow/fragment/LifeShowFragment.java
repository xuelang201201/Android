package com.bignerdranch.android.lifeshow.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.NetworkImageView;
import com.bignerdranch.android.lifeshow.R;
import com.bignerdranch.android.lifeshow.bean.GalleryItem;

import java.util.ArrayList;
import java.util.List;

public class LifeShowFragment extends Fragment {

    private static final String TAG = "LifeShowFragment";

    private RecyclerView mRecyclerView;
    private List<GalleryItem> mItems = new ArrayList<>();

    public static LifeShowFragment newInstance() {
        return new LifeShowFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_life_show, container, false);

        mRecyclerView = (RecyclerView) v
                .findViewById(R.id.fragment_life_show_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        setupAdapter();

        return v;
    }

    private void setupAdapter() {
    }

    public class PhotoHolder extends RecyclerView.ViewHolder {

        private NetworkImageView mImageView;

        public PhotoHolder(View itemView) {
            super(itemView);

            mImageView = (NetworkImageView) itemView
                    .findViewById(R.id.fragment_life_show_image_view);
        }

        public void bindDrawable(Drawable drawable) {
            mImageView.setImageDrawable(drawable);
        }

    }

    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {
        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(PhotoHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }
}
