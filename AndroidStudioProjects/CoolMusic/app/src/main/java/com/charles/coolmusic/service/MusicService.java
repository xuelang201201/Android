package com.charles.coolmusic.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.charles.coolmusic.app.MusicPlayerApplication;
import com.charles.coolmusic.bean.Music;
import com.charles.coolmusic.util.Consts;

import java.io.IOException;
import java.util.List;

public class MusicService extends Service implements Consts {

    /**
     * 数据源
     */
    private List<Music> mMusics;
    /**
     * Application
     */
    private MusicPlayerApplication mApplication;
    /**
     * 播放工具
     */
    private MediaPlayer mMediaPlayer;
    /**
     * 当前播放的歌曲的索引
     */
    private int mCurrentMusicIndex;
    /**
     * 暂停时播放到的位置
     */
    private int mPausePosition;
    /**
     * 广播接收者
     */
    private BroadcastReceiver mReceiver;
    /**
     * 提交播放进度的线程的循环条件
     */
    private boolean isRunning;
    /**
     * 更新进度的线程
     */
    private UpdateThread mUpdateThread;

    @Override
    public void onCreate() {
        // 获取数据源
        mApplication = (MusicPlayerApplication) getApplication();
        mMusics = mApplication.getMusics();
        // 初始化播放工具
        mMediaPlayer = new MediaPlayer();
        // 注册广播接收者
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // 判断获取的广播中的Intent的Action
                switch (intent.getAction()) {
                    case ACTION_PLAY_OR_PAUSE:
                        // 判断歌曲是否正在播放
                        if (mMediaPlayer.isPlaying()) {
                            // 正在播放，暂停
                            pause();
                        } else {
                            // 没有播放，播放
                            play();
                        }
                        break;
                    case ACTION_PREVIOUS:
                        // 播放上一首
                        previous();
                        break;
                    case ACTION_NEXT:
                        // 播放下一首
                        next();
                        break;
                    case ACTION_PLAY:
                        // 播放指定的歌曲
                        int position = intent.getIntExtra(EXTRA_POSITION, 0);
                        play(position);
                        break;
                    case ACTION_PLAY_FROM_PERCENT:
                        // 从指定的百分比位置开始播放
                        float percent = intent.getFloatExtra(EXTRA_POSITION, 0F);
                        play(percent);
                        break;
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_PLAY_OR_PAUSE);
        filter.addAction(ACTION_PREVIOUS);
        filter.addAction(ACTION_NEXT);
        filter.addAction(ACTION_PLAY);
        filter.addAction(ACTION_PLAY_FROM_PERCENT);
        registerReceiver(mReceiver, filter);
    }

    /**
     * 暂停
     */
    private void pause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mPausePosition = mMediaPlayer.getCurrentPosition();

            // 发送广播，告知Activity已经暂停
            Intent intent = new Intent();
            intent.setAction(ACTION_SET_PAUSE_STATE);
            sendBroadcast(intent);

            // 停止更新进度的线程
            stopUpdateThread();
        }
    }

    /**
     * 播放
     */
    private void play() {
        mMediaPlayer.reset();
        try {
            mMediaPlayer.setDataSource(mMusics.get(mCurrentMusicIndex).getFileUrl());
            mMediaPlayer.prepare();
            mMediaPlayer.seekTo(mPausePosition);
            mMediaPlayer.start();

            // 发送广播，告知Activity正在播放
            Intent intent = new Intent();
            intent.setAction(ACTION_SET_PLAY_STATE);
            intent.putExtra(EXTRA_POSITION, mCurrentMusicIndex); // 播放的是第?首
            intent.putExtra(EXTRA_DURATION, mMediaPlayer.getDuration());
            sendBroadcast(intent);
            // 开启更新进度的线程
            startUpdateThread();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放上一首
     */
    private void previous() {
        mCurrentMusicIndex --;
        if (mCurrentMusicIndex < 0) {
            mCurrentMusicIndex = mMusics.size() - 1;
        }
        mPausePosition = 0;
        play();
    }

    /**
     * 播放下一首
     */
    private void next() {
        mCurrentMusicIndex ++;
        if (mCurrentMusicIndex >= mMusics.size()) {
            mCurrentMusicIndex = 0;
        }
        mPausePosition = 0;
        play();
    }


    /**
     * 播放指定的歌曲
     * @param position 歌曲的位置/索引
     */
    private void play(int position) {
        mCurrentMusicIndex = position;
        mPausePosition = 0;
        play();
    }

    /**
     * 从指定的位置开始播放歌曲
     * @param percent 播放位置的百分比，例如：0.65
     */
    private void play(float percent) {
        mPausePosition = (int) (mMediaPlayer.getDuration() * percent);
        play();
    }

    /**
     * 开启更新进度的线程
     */
    private void startUpdateThread() {
        if (mUpdateThread == null) {
            mUpdateThread = new UpdateThread();
            isRunning = true;
            mUpdateThread.start();
        }
    }

    /**
     * 停止更新线程
     */
    private void stopUpdateThread() {
        if (mUpdateThread != null) {
            isRunning = false;
            mUpdateThread = null;
        }
    }

    private class UpdateThread extends Thread {
        @Override
        public void run() {
            Intent intent = new Intent();
            while (isRunning) {
                if (mMediaPlayer.isPlaying()) {
                    intent.setAction(ACTION_UPDATE_PROGRESS);
                    intent.putExtra(EXTRA_CURRENT_POSITON, mMediaPlayer.getCurrentPosition());
                    intent.putExtra(EXTRA_PERCENT, mMediaPlayer.getDuration()
                            == 0 ? 0 : mMediaPlayer.getCurrentPosition() * 100
                            / mMediaPlayer.getDuration());
                    sendBroadcast(intent);
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 设置当前Service为非粘性的
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // 停止更新进度的线程
        stopUpdateThread();
        // 释放播放工具的资源
        mMediaPlayer.release();
        // 取消注册广播接收者
        unregisterReceiver(mReceiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
