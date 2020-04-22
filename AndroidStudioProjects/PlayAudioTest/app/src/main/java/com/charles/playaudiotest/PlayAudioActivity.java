package com.charles.playaudiotest;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class PlayAudioActivity extends Activity implements View.OnClickListener {

    private Button mPlay, mPause, mStop;
    private MediaPlayer mMediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_audio);

        initView(); // 初始化视图组件
        initListener(); // 初始化监听器
        initMediaPlayer(); // 初始化MediaPlayer
    }

    private void initView() {
        mPlay = (Button) findViewById(R.id.play);
        mPause = (Button) findViewById(R.id.pause);
        mStop = (Button) findViewById(R.id.stop);
    }

    private void initListener() {
        mPlay.setOnClickListener(this);
        mPause.setOnClickListener(this);
        mStop.setOnClickListener(this);
    }

    private void initMediaPlayer() {

        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),
                    "/kgmusic/download/Ed Sheeran - Shape of You.mp3");
            mMediaPlayer.setDataSource(file.getPath()); // 指定音频文件的路径
            mMediaPlayer.prepare(); // 让MediaPlayer进入到准备状态
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                if (! mMediaPlayer.isPlaying()) {
                    mMediaPlayer.start(); // 开始播放
                }
                break;
            case R.id.pause:
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause(); // 暂停播放
                }
                break;
            case R.id.stop:
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.reset(); // 停止播放
                    initMediaPlayer();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
            mMediaPlayer.release();
        }
    }
}
