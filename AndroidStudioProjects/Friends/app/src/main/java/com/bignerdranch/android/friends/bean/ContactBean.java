package com.bignerdranch.android.friends.bean;

import java.io.Serializable;
import java.util.List;

public class ContactBean implements Serializable {
    private int mId;
    private String mName;
    private String mCompany;
    private String mPost;
    private int mAvatarId;
    private List<String> mNumber;
    private String mAddress;
    private int mAddressType;
    private String mAddressTypeStr;
    private String mEmail;
    private int mEmailType;
    private String mEmailTypeStr;
    private String mSortKey;
    private int mPhoneType;
    private String mPhoneTypeStr;
    private String mEvent;
    private int mEventType;
    private String mEventTypeStr;
    private String mWebsite;
    private boolean isFav;

    public ContactBean() {
    }

    public ContactBean(ContactBean contact) {
        mId = contact.getId();
        mName = contact.getName();
        mCompany = contact.getCompany();
        mPost = contact.getPost();
        mAvatarId = contact.getAvatarId();
        mNumber = contact.getNumber();
        mAddress = contact.getAddress();
        mAddressType = contact.getAddressType();
        mAddressTypeStr = contact.getAddressTypeStr();
        mEmail = contact.getEmail();
        mEmailType = contact.getEmailType();
        mEmailTypeStr = contact.getEmailTypeStr();
        mSortKey = contact.getSortKey();
        mPhoneType = contact.getPhoneType();
        mPhoneTypeStr = contact.getPhoneTypeStr();
        mEvent = contact.getEvent();
        mEventType = contact.getEventType();
        mEventTypeStr = contact.getEventTypeStr();
        mWebsite = contact.getWebsite();
        isFav = contact.isFav();
    }

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

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String company) {
        mCompany = company;
    }

    public String getPost() {
        return mPost;
    }

    public void setPost(String post) {
        mPost = post;
    }

    public int getAvatarId() {
        return mAvatarId;
    }

    public void setAvatarId(int avatarId) {
        mAvatarId = avatarId;
    }

    public List<String> getNumber() {
        return mNumber;
    }

    public void setNumber(List<String> number) {
        mNumber = number;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public int getAddressType() {
        return mAddressType;
    }

    public void setAddressType(int addressType) {
        mAddressType = addressType;
    }

    public String getAddressTypeStr() {
        return mAddressTypeStr;
    }

    public void setAddressTypeStr(String addressTypeStr) {
        mAddressTypeStr = addressTypeStr;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public int getEmailType() {
        return mEmailType;
    }

    public void setEmailType(int emailType) {
        mEmailType = emailType;
    }

    public String getEmailTypeStr() {
        return mEmailTypeStr;
    }

    public void setEmailTypeStr(String emailTypeStr) {
        mEmailTypeStr = emailTypeStr;
    }

    public String getSortKey() {
        return mSortKey;
    }

    public void setSortKey(String sortKey) {
        mSortKey = sortKey;
    }

    public int getPhoneType() {
        return mPhoneType;
    }

    public void setPhoneType(int phoneType) {
        mPhoneType = phoneType;
    }

    public String getPhoneTypeStr() {
        return mPhoneTypeStr;
    }

    public void setPhoneTypeStr(String phoneTypeStr) {
        mPhoneTypeStr = phoneTypeStr;
    }

    public String getEvent() {
        return mEvent;
    }

    public void setEvent(String event) {
        mEvent = event;
    }

    public int getEventType() {
        return mEventType;
    }

    public void setEventType(int eventType) {
        mEventType = eventType;
    }

    public String getEventTypeStr() {
        return mEventTypeStr;
    }

    public void setEventTypeStr(String eventTypeStr) {
        mEventTypeStr = eventTypeStr;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public void setWebsite(String website) {
        mWebsite = website;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }
}
