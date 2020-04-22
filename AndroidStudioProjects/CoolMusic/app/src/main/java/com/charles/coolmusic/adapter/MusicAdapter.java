package com.charles.coolmusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.charles.coolmusic.R;
import com.charles.coolmusic.bean.Music;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicHolder> {

    private Context mContext;
    private List<Music> mMuiscs;
    private LayoutInflater mLayoutInflater;

    public MusicAdapter(Context context, List<Music> musics) {
        mContext = context;
        mMuiscs = musics;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MusicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.list_item_music, parent, false);
        return new MusicHolder(v);
    }

    @Override
    public void onBindViewHolder(MusicHolder holder, int position) {
        Music music = mMuiscs.get(position);
        holder.mTitle.setText(music.getTitle());
        holder.mSinger.setText(music.getSinger());
    }

    @Override
    public int getItemCount() {
        return mMuiscs.size();
    }
}

class MusicHolder extends RecyclerView.ViewHolder {

    TextView mTitle, mSinger;

    public MusicHolder(View itemView) {
        super(itemView);

        mTitle = (TextView) itemView
                .findViewById(R.id.list_item_music_title);
        mSinger = (TextView) itemView
                .findViewById(R.id.list_item_music_singer);
    }
}
