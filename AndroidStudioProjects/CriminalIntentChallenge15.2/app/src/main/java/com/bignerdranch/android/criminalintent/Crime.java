package com.bignerdranch.android.criminalintent;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private String mSuspect;
    private long mContactId;

    public Crime() {
        // 生成唯一的id
        // mId = UUID.randomUUID();
        // mDate = new Date();
        this(UUID.randomUUID());
    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }

    public long getContactId() {
        return mContactId;
    }

    public void setContactId(long contactId) {
        mContactId = contactId;
    }

    public void setDay(Date date) {
        Calendar crimeCalendar = Calendar.getInstance();
        crimeCalendar.setTime(mDate);

        Calendar newDate = Calendar.getInstance();
        newDate.setTime(date);

        // 将新日期设置为当前设置的时间
        newDate.set(Calendar.HOUR_OF_DAY, crimeCalendar.get(Calendar.HOUR_OF_DAY));
        newDate.set(Calendar.MINUTE, crimeCalendar.get(Calendar.MINUTE));

        mDate = newDate.getTime();
    }
}
