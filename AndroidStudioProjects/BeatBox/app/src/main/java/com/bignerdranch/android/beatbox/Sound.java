package com.bignerdranch.android.beatbox;

public class Sound {
    private String mAssetPath;
    private String mName;
    private Integer mSoundId; // 用Integer类型而不用int类型，这样在Sound的mSoundId没有值时可以设置其为null

    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String filename = components[components.length - 1];
        mName = filename.replace(".wav", "");
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }
}
