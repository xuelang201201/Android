package com.bignerdranch.android.friends.bean;

public class CallLogBean {
    private int mId;
    private String mName; //名字
    private String mNumber; //手机号
    private String mDate; //日期
    private int mType; //类型   1,来电  2,呼出  3,未接
    private int mCount; //通话次数
    private String mPhonelocal; //号码归属地
    private int mDuration;

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

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public String getPhonelocal() {
        return mPhonelocal;
    }

    public void setPhonelocal(String phonelocal) {
        mPhonelocal = phonelocal;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }
}
