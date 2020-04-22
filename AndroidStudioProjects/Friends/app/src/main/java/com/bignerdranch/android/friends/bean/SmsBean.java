package com.bignerdranch.android.friends.bean;

/**
 * 描述一条短信
 */
public class SmsBean {

    private int mId;
    private String mName;
    private String mNumber;
    private long mDate;
    private String mBody;
    private int mAvatarId;
    private int mType;
    private boolean isRead;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
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

    public long getDate() {
        return mDate;
    }

    public void setDate(long date) {
        mDate = date;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public int getAvatarId() {
        return mAvatarId;
    }

    public void setAvatarId(int avatarId) {
        mAvatarId = avatarId;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }
}