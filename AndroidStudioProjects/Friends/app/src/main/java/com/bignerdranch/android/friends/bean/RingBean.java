package com.bignerdranch.android.friends.bean;

import java.io.Serializable;

/**
 * 歌曲信息实体类
 */
public class RingBean implements Serializable {
    private String mTitle;
    private String mPath;
    private String mFileName;
    private boolean isSelected;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String fileName) {
        mFileName = fileName;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
