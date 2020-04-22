package com.charles.coolmusic.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.charles.coolmusic.R;
import com.charles.coolmusic.adapter.MusicAdapter;
import com.charles.coolmusic.app.MusicPlayerApplication;
import com.charles.coolmusic.bean.Music;
import com.charles.coolmusic.listener.RecyclerItemClickListener;
import com.charles.coolmusic.service.MusicService;
import com.charles.coolmusic.util.Consts;

import java.util.ArrayList;
import java.util.List;

public class MusicListFragment extends Fragment implements
        Consts,
        SeekBar.OnSeekBarChangeListener,
        View.OnClickListener {

    private static final String TAG = "MusicListFragment";

    /**
     * TextView：正在播放的歌曲的标题
     */
    private TextView mMusicTitle;
    /**
     * TextView：正在播放的歌曲的演唱者
     */
    private TextView mSinger;
    /**
     * TextView：正在播放的歌曲的总时长
     */
    private TextView mMusicDuration;
    /**
     * TextView：当前播放到的时间
     */
    private TextView mMusicCurrentPosition;
    /**
     * SeekBar：进度条
     */
    private SeekBar mProgressSeekBar;
    /**
     * ImageButton：播放/暂停
     */
    private ImageButton mPlayOrPause;
    /**
     * ImageButton：上一首
     */
    private ImageButton mPrevious;
    /**
     * ImageButton：下一首
     */
    private ImageButton mNext;
    /**
     * RecyclerView
     */
    private RecyclerView mMusicRecyclerView;
    /**
     * 数据源
     */
    private List<Music> mMusics = new ArrayList<>();
    /**
     * Application
     */
    private MusicPlayerApplication mApplication;
    /**
     * 广播接收者
     */
    private BroadcastReceiver mReceiver;

    public static MusicListFragment newInstance() {
        return new MusicListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 激活Service
        Intent intent = new Intent(getActivity(), MusicService.class);
        getActivity().startService(intent);

        // 注册广播接收者
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // 判断获取的Action
                switch (intent.getAction()) {
                    case ACTION_SET_PLAY_STATE:
                        // 设置界面为播放状态
                        mPlayOrPause.setImageResource(android.R.drawable.ic_media_pause);
                        int position = intent.getIntExtra(EXTRA_POSITION, 0);
                        int duration = intent.getIntExtra(EXTRA_POSITION, 0);
                        mMusicTitle.setText(mMusics.get(position).getTitle());
                        mSinger.setText(mMusics.get(position).getSinger());
                        break;
                    case ACTION_SET_PAUSE_STATE:
                        // 设置界面为暂停状态
                        mPlayOrPause.setImageResource(android.R.drawable.ic_media_play);
                        break;
                    case ACTION_UPDATE_PROGRESS:
                        // TODO 更新播放进度
                        int currentPosition = intent.getIntExtra(EXTRA_CURRENT_POSITON, 0);
                        int percent = intent.getIntExtra(EXTRA_PERCENT, 0);

                        break;
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_SET_PLAY_STATE);
        filter.addAction(ACTION_SET_PAUSE_STATE);
        filter.addAction(ACTION_UPDATE_PROGRESS);
        getActivity().registerReceiver(mReceiver, filter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_music_list, container, false);

        // 初始化控件
        initView(v);

        // 获取数据源
        mApplication = (MusicPlayerApplication) getActivity().getApplication();
        mMusics = mApplication.getMusics();

        // 显示数据
        setupAdapter();

        mMusicRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), mMusicRecyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent();
                                intent.setAction(ACTION_PLAY);
                                intent.putExtra(EXTRA_POSITION, position);
                                getActivity().sendBroadcast(intent);
                                Log.d(TAG, "点击" + position);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {
                            }
                        }));

        return v;
    }

    private void initView(View v) {
        mMusicRecyclerView = (RecyclerView) v
                .findViewById(R.id.fragment_music_list_recycler_view);
        mMusicRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        mMusicTitle = (TextView) v.findViewById(R.id.fragment_music_list_title);
        mSinger = (TextView) v.findViewById(R.id.fragment_music_list_singer);
        mPrevious = (ImageButton) v.findViewById(R.id.fragment_music_list_previous);
        mNext = (ImageButton) v.findViewById(R.id.fragment_music_list_next);
        mProgressSeekBar = (SeekBar) v.findViewById(R.id.fragment_music_list_seek_bar);

    }

    private void setupAdapter() {
        if (isAdded()) {
            mMusicRecyclerView.setAdapter(
                    new MusicAdapter(getActivity(), mMusics));
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.fragment_music_list_play_or_pause:
                intent.setAction(ACTION_PLAY_OR_PAUSE);
                getActivity().sendBroadcast(intent);
                break;
            case R.id.fragment_music_list_previous:
                intent.setAction(ACTION_PREVIOUS);
                getActivity().sendBroadcast(intent);
                break;
            case R.id.fragment_music_list_next:
                intent.setAction(ACTION_NEXT);
                getActivity().sendBroadcast(intent);
                break;
        }
    }

    @Override
    public void onDestroy() {
        // 取消注册广播接收者
        getActivity().unregisterReceiver(mReceiver);
        // 停止Service
        Intent intent = new Intent(getActivity(), MusicService.class);
        getActivity().stopService(intent);
        super.onDestroy();
    }
}
