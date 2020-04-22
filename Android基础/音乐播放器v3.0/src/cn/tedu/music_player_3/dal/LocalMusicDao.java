package cn.tedu.music_player_3.dal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.os.Environment;
import cn.tedu.music_player_3.entity.Music;

/**
 * 扫描SD卡目录获取歌曲信息
 */
class LocalMusicDao implements IDao<Music> {

	@Override
	public List<Music> getData() {
		// 准备返回值
		List<Music> musics = new ArrayList<Music>();
		
		// 检查SD卡的挂载状态
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			// 检查SD卡的Music文件夹是否存在
			File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			if (dir.exists()) {
				// 获取Music文件夹下的文件列表
				File[] files = dir.listFiles();
				// 遍历文件列表
				for (File file : files) {
					// 检查是否是文件
					if (file.isFile()) {
						// 检查是否是mp3文件
						if (file.getName().toLowerCase(Locale.CHINA).endsWith(".mp3")) {
							// 创建Music对象
							Music music = new Music();
							// 向Music对象中封装数据
							music.setPath(file.getAbsolutePath());
							music.setTitle(file.getName().substring(0, file.getName().length() - 4));
							// 将Music添加到返回值
							musics.add(music);
						}
					}
				}
			}
		}
		
		// 返回
		return musics;
	}

}
