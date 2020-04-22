package com.charles.coolmusic.bean;

/**
 * 歌曲信息的实体类
 */
public class Music {
    private String mFileName;
    private String mTitle;
    private int mDuration;
    private String mSinger;
    private String mAlbum;
    private String mYear;
    private String mType;
    private String mSize;
    private String mFileUrl;
    private String mAlbumKey;
    private String mAlbumArt;

    public Music() {
    }

    public Music(String fileName, String title, int duration, String singer,
                 String album, String year, String type, String size,
                 String fileUrl, String albumKey, String albumArt) {
        mFileName = fileName;
        mTitle = title;
        mDuration = duration;
        mSinger = singer;
        mAlbum = album;
        mYear = year;
        mType = type;
        mSize = size;
        mFileUrl = fileUrl;
        mAlbumKey = albumKey;
        mAlbumArt = albumArt;
    }

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String fileName) {
        mFileName = fileName;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public String getSinger() {
        return mSinger;
    }

    public void setSinger(String singer) {
        mSinger = singer;
    }

    public String getAlbum() {
        return mAlbum;
    }

    public void setAlbum(String album) {
        mAlbum = album;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getSize() {
        return mSize;
    }

    public void setSize(String size) {
        mSize = size;
    }

    public String getFileUrl() {
        return mFileUrl;
    }

    public void setFileUrl(String fileUrl) {
        mFileUrl = fileUrl;
    }

    public String getAlbumKey() {
        return mAlbumKey;
    }

    public void setAlbumKey(String albumKey) {
        mAlbumKey = albumKey;
    }

    public String getAlbumArt() {
        return mAlbumArt;
    }

    public void setAlbumArt(String albumArt) {
        mAlbumArt = albumArt;
    }

    @Override
    public String toString() {
        return "Music{" +
                "mFileName='" + mFileName + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mDuration=" + mDuration +
                ", mSinger='" + mSinger + '\'' +
                ", mAlbum='" + mAlbum + '\'' +
                ", mYear='" + mYear + '\'' +
                ", mType='" + mType + '\'' +
                ", mSize='" + mSize + '\'' +
                ", mFileUrl='" + mFileUrl + '\'' +
                ", mAlbumKey='" + mAlbumKey + '\'' +
                ", mAlbumArt='" + mAlbumArt + '\'' +
                '}';
    }
}
