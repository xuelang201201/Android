package cn.tedu.music_player_2.service;

import java.io.IOException;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import cn.tedu.music_player_2.app.MusicPlayerApplication;
import cn.tedu.music_player_2.entity.Music;
import cn.tedu.music_player_2.util.Consts;

/**
 * 播放歌曲的Service
 */
public class CopyOfPlayMusicService extends Service implements Consts {
	/**
	 * 歌曲的数据源
	 */
	private List<Music> musics;
	/**
	 * 当前播放的歌曲的索引
	 */
	private int currentMusicIndex;
	/**
	 * 暂停时播放到的位置，单位：毫秒
	 */
	private int pausePosition;
	/**
	 * Application
	 */
	private MusicPlayerApplication app;
	/**
	 * 播放工具
	 */
	private MediaPlayer player;

	@Override
	public void onCreate() {
		// 准备数据源
		app = (MusicPlayerApplication) getApplication();
		musics = app.getMusics();
		// 初始化播放工具
		player = new MediaPlayer();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int action = intent.getIntExtra(EXTRA_ACTION, -1);
		switch (action) {
		case ACTION_PLAY_OR_PAUSE: // 播放/暂停
			if(player.isPlaying()) {
				pause();
			} else {
				play();
			}
			break;

		case ACTION_PREVIOUS: // 上一首
			previous();
			break;
			
		case ACTION_NEXT: // 下一首
			next();
			break;
			
		case ACTION_PLAY_NEW: // 播放指定的歌曲
			int position = intent.getIntExtra(EXTRA_POSITION, 0);
			play(position);
			break;
		}
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	/**
	 * 播放
	 */
	private void play() {
		try {
			player.reset();
			player.setDataSource(musics.get(currentMusicIndex).getPath());
			player.prepare();
			player.seekTo(pausePosition);
			player.start();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 播放歌曲
	 * @param position 歌曲的索引
	 */
	private void play(int position) {
		currentMusicIndex = position;
		pausePosition = 0;
		play();
	}
	
	/**
	 * 暂停
	 */
	private void pause() {
		if(player.isPlaying()) {
			player.pause();
			pausePosition = player.getCurrentPosition();
		}
	}
	
	/**
	 * 播放上一首
	 */
	private void previous() {
		currentMusicIndex--;
		if(currentMusicIndex < 0) {
			currentMusicIndex = musics.size() - 1;
		}
		pausePosition = 0;
		play();
	}
	
	/**
	 * 播放下一首
	 */
	private void next() {
		currentMusicIndex++;
		if(currentMusicIndex >= musics.size()) {
			currentMusicIndex = 0;
		}
		pausePosition = 0;
		play();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// （无视）
		return null;
	}

}
