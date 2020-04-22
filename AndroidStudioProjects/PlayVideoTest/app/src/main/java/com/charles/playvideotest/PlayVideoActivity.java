package com.charles.playvideotest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;

public class PlayVideoActivity extends Activity implements View.OnClickListener {

    private VideoView mVideoView;
    private Button mPlay, mPause, mReplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        initView();
        initListener();
    }

    private void initView() {
        mPlay = (Button) findViewById(R.id.play);
        mPause = (Button) findViewById(R.id.pause);
        mReplay = (Button) findViewById(R.id.replay);
        mVideoView = (VideoView) findViewById(R.id.video_view);
    }

    private void initListener() {
        mPlay.setOnClickListener(this);
        mPause.setOnClickListener(this);
        mReplay.setOnClickListener(this);
        initVideoPath();
    }

    /**
     * 指定视频文件的路径
     */
    private void initVideoPath() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),
                "/Movies/movie.mp4");
        mVideoView.setVideoPath(file.getPath());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                if (! mVideoView.isPlaying()) {
                    mVideoView.start(); // 开始播放
                }
                break;
            case R.id.pause:
                if (mVideoView.isPlaying()) {
                    mVideoView.pause(); // 暂停播放
                }
                break;
            case R.id.replay:
                if (mVideoView.isPlaying()) {
                    mVideoView.resume(); // 重新播放
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.suspend();
        }
    }
}
