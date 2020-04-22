package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private static final String TAG = "BeatBox";

    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        // Lollipop引入了新的方式创建SoundPool：使用SoundPool.Builder
        // 但是为了兼容API 16只能选择SoundPool(int, int, int)这个老构造方法了
        mSoundPool = new SoundPool(
                MAX_SOUNDS, // 指定同时播放多少个音频
                AudioManager.STREAM_MUSIC, // 确定音频流类型
                0 // 指定采样率转换品质。这个不起作用，所以传入0
        );
        loadSounds();
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException ioe) {
            Log.e(TAG, "Could not list assets", ioe);
            ioe.printStackTrace();
            return;
        }

        for (String filename : soundNames) {
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                // 载入全部音频文件
                mSounds.add(sound);
            } catch (IOException ioe) {
                Log.e(TAG, "Could not load sound " + filename, ioe);
            }
        }
    }

    /**
     * 播放音频
     */
    public void play(Sound sound) {
        Integer soundId = sound.getSoundId();
        if (soundId == null) {
            return;
        }
        mSoundPool.play(
                soundId, // 音频ID
                1.0f, // 左音量
                1.0f, // 右音量
                1, // 优先级（无效）
                0, // 是否循环（传入-1无线循环）
                1.0f // 播放速率
        );
    }

    /**
     * 释放音频
     * 音频播放完毕，释放SoundPool
     * 在BeatBoxFragment.java中，使用完毕后，就在onDestroy()中调用这个释放方法
     */
    public void release() {
        mSoundPool.release();
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    /**
     * 加载音频
     */
    private void load(Sound sound) throws IOException {
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        // 调用mSoundPool.load(AssetFileDescriptor, int)方法可以把文件载入SoundPool待播
        int soundId = mSoundPool.load(afd, 1);
        sound.setSoundId(soundId);
    }
}
