package com.charles.coolmusic.app;

import android.app.Application;

import com.charles.coolmusic.bean.Music;
import com.charles.coolmusic.dal.IDao;
import com.charles.coolmusic.dal.MusicDaoFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Application：在本例中，用于把歌曲的数据源提供给Activity、Service
 */
public class MusicPlayerApplication extends Application {
    private List<Music> mMusics;

    @Override
    public void onCreate() {
        super.onCreate();
        IDao<Music> dao = MusicDaoFactory.newInstance(this);
        List<Music> data = dao.getData();
        setMusics(data);
    }

    public void setMusics(List<Music> musics) {
        if (musics == null) {
            musics = new ArrayList<>();
        }
        mMusics = musics;
    }

    public List<Music> getMusics() {
        return mMusics;
    }
}
