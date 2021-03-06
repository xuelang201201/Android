package cn.tedu.musicclient.service;

import java.io.IOException;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.IBinder;
import cn.tedu.musicclient.app.MusicPlayerApplication;
import cn.tedu.musicclient.entity.Music;
import cn.tedu.musicclient.util.Consts;
import cn.tedu.musicclient.util.IMusicPlayer;

/**
 * 播放歌曲的Service
 */
public class PlayMusicService extends Service implements Consts {
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
		player.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				next();
			}
		});
	}

	/**
	 * 播放
	 */
	private void play() {
		try {
			player.reset();
			player.setDataSource(musics.get(currentMusicIndex).getMusicpath());
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

	private void play(float progress) {
		pausePosition = (int) (player.getDuration() * progress / 100);
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
		InnerBinder binder = new InnerBinder();
		return binder;
	}

	private class InnerBinder extends Binder implements IMusicPlayer {

		@Override
		public void play() {
			PlayMusicService.this.play();
		}

		@Override
		public void play(int position) {
			PlayMusicService.this.play(position);
		}

		@Override
		public void play(float progress) {
			PlayMusicService.this.play(progress);
		}

		@Override
		public void pause() {
			PlayMusicService.this.pause();
		}

		@Override
		public void previous() {
			PlayMusicService.this.previous();
		}

		@Override
		public void next() {
			PlayMusicService.this.next();
		}

		@Override
		public boolean isPlaying() {
			return player.isPlaying();
		}

		@Override
		public int getCurrentPosition() {
			return player.getCurrentPosition();
		}

		@Override
		public int getDuration() {
			return player.getDuration();
		}

		@Override
		public int getCurrentMusicIndex() {
			return currentMusicIndex;
		}
	}
}
