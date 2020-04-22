package com.bignerdranch.android.friends.bean;

import java.io.Serializable;

/**
 * 对短信分组
 */
public class ConversationBean implements Serializable {
    private int mGroupId;
    private int mAvatarId;
    private int mCount;
    private String mName;
    private String mNumber;
    private String mLastSms;
    private long mLastDate;
    private int mIsRead;
    private boolean mIsChecked;

    public int getGroupId() {
        return mGroupId;
    }

    public void setGroupId(int groupId) {
        mGroupId = groupId;
    }

    public int getAvatarId() {
        return mAvatarId;
    }

    public void setAvatarId(int avatarId) {
        mAvatarId = avatarId;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public String getLastSms() {
        return mLastSms;
    }

    public void setLastSms(String lastSms) {
        mLastSms = lastSms;
    }

    public long getLastDate() {
        return mLastDate;
    }

    public void setLastDate(long lastDate) {
        mLastDate = lastDate;
    }

    public int getIsRead() {
        return mIsRead;
    }

    public void setIsRead(int isRead) {
        mIsRead = isRead;
    }

    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean checked) {
        mIsChecked = checked;
    }
}
