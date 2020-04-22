package com.charles.coolmusic.dal;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;

import com.charles.coolmusic.bean.Music;

import java.util.ArrayList;
import java.util.List;

public class MediaStoreMusicDao implements IDao<Music> {

    private Context mContext;

    public MediaStoreMusicDao(Context context) {
        super();
        setContext(context);
    }

    public void setContext(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("参数Context不允许为null！！！");
        }
        mContext = context;
    }

    @Override
    public List<Music> getData() {
        // 准备返回值
        List<Music> musics = new ArrayList<>();
        Cursor cursor = mContext.getContentResolver().query(
                Media.EXTERNAL_CONTENT_URI,
                new String[] {
                        Media._ID,
                        Media.DISPLAY_NAME,
                        Media.TITLE,
                        Media.DURATION,
                        Media.ARTIST,
                        Media.ALBUM,
                        Media.YEAR,
                        Media.MIME_TYPE,
                        Media.SIZE,
                        Media.DATA,
                        Media.ALBUM_KEY
                }, null, null, null); // 所有格式的音频文件
        // 检查结果并遍历
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Music music = new Music();
                // 文件名
                music.setFileName(cursor.getString(1));
                // 歌曲名
                music.setTitle(cursor.getString(2));
                // 时长
                music.setDuration(cursor.getInt(3));
                // 歌手名
                music.setSinger(cursor.getString(4));
                // 专辑名
                music.setAlbum(cursor.getString(5));
                // 年代
                if (cursor.getString(6) != null) {
                    music.setYear(cursor.getString(6));
                } else {
                    music.setYear("未知");
                }
                // 歌曲格式
                switch (cursor.getString(7).trim()) {
                    case "audio/mpeg":
                        music.setType("mp3");
                        break;
                    case "audio/x-ms-wma":
                        music.setType("wma");
                        break;
                    case "audio/flac":
                        music.setType("flac");
                        break;
                    case "audio/x-monkeys-audio":
                        music.setType("ape");
                        break;
                    default:
                        break;
                }
                // 文件大小
                if (cursor.getString(8) != null) {
                    float size = cursor.getInt(8) / 1024f / 1024f;
                    music.setSize((size + "").substring(0, 4) + "M");
                } else {
                    music.setSize("未知");
                }
                // 文件路径
                if (cursor.getString(9) != null) {
                    music.setFileUrl(cursor.getString(9));
                }
                if (cursor.getString(10) != null) {
                    music.setAlbumKey(cursor.getString(10));
                }
                music.setAlbumArt(getAlbumArtByKey(mContext, music.getAlbumKey()));

                musics.add(music);
            } while (cursor.moveToNext());

            // 释放资源
            cursor.close();
        }

        // 返回
        return musics;
    }

    /**
     * 根据album_key检索对应的album_art
     * @param albumKey
     * @return album_art，即音乐内置图片的路径
     */
    private static String getAlbumArtByKey(Context context, String albumKey) {
        // 判断参数是否有效
        if (albumKey == null) {
            return null;
        }

        // 声明返回值
        String result = null;

        // ContentResolver
        ContentResolver cr = context.getContentResolver();

        // Uri
        Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

        // 执行查询
        Cursor c = cr.query(uri, new String[] { "album_art" },
                "album_key = ?", new String[] { albumKey }, null);

        // 获取查询结果
        if (c != null && c.moveToFirst()) {
            result = c.getString(0);
        }

        // 释放资源
        if (c != null && ! c.isClosed()) {
            c.close();
        }

        return result;
    }
}
