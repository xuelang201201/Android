package com.charles.coolmusic.dal;

import android.os.Environment;

import com.charles.coolmusic.bean.Music;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 扫描SD卡目录获取歌曲信息
 */
public class LocalMusicDao implements IDao<Music> {

    @Override
    public List<Music> getData() {
        // 准备返回值
        List<Music> musics = new ArrayList<>();

        // 检查SD卡的挂载状态
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // 检查SD卡的Music文件夹是否存在
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
            if (dir.exists()) {
                // 获取Music文件夹下的文件列表
                File[] files = dir.listFiles();
                // 遍历文件列表
                for (File file : files) {
                    // 创建Music对象
                    Music music = new Music();

                    String str = file.getName();
                    music.setFileUrl(file.getAbsolutePath());
                    music.setTitle(str.split(".")[0]);
                    music.setType(str.split(".")[1]);
                    // 将Music添加到返回值
                    musics.add(music);
                }
            }
        }
        // 返回musics
        return musics;
    }
}
