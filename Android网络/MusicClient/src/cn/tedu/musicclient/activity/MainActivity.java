package cn.tedu.musicclient.activity;

import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import cn.tedu.musicclient.R;
import cn.tedu.musicclient.adapter.MusicAdapter;
import cn.tedu.musicclient.app.MusicPlayerApplication;
import cn.tedu.musicclient.biz.MusicBiz;
import cn.tedu.musicclient.biz.MusicBiz.Callback;
import cn.tedu.musicclient.entity.Music;
import cn.tedu.musicclient.service.PlayMusicService;
import cn.tedu.musicclient.util.CommonUtils;
import cn.tedu.musicclient.util.GlobalConsts;
import cn.tedu.musicclient.util.IMusicPlayer;

public class MainActivity extends Activity implements OnSeekBarChangeListener, View.OnClickListener, OnItemClickListener {
	/**
	 * ImageButton: 播放/暂停
	 */
	private ImageButton ibPlayOrPause;
	/**
	 * ImageButton: 上一首
	 */
	private ImageButton ibPrevious;
	/**
	 * ImageButton: 下一首
	 */
	private ImageButton ibNext;
	/**
	 * ListView: 歌曲列表控件
	 */
	private ListView lvMusics;
	/**
	 * 歌曲的数据源
	 */
	private List<Music> musics;
	/**
	 * 歌曲列表的Adapter
	 */
	private MusicAdapter adapter;
	/**
	 * Application
	 */
	private MusicPlayerApplication app;

	private ServiceConnection conn;

	private IMusicPlayer player;
	private TextView tvMusicTitle;
	private SeekBar sbMusicProgress;
	private TextView tvMusicCurrentPosition;
	private TextView tvMusicDuration;
	private boolean isUpdateThreadRunning;
	private UpdateThread updateThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent intent = new Intent(this, PlayMusicService.class);
		conn = new InnerServiceConnection();
		bindService(intent, conn, BIND_AUTO_CREATE);

		setViews();
		setListeners();

		//准备数据源
		app = (MusicPlayerApplication) getApplication();
		musics = app.getMusics();

		//启动异步任务  发送请求  获取音乐列表
		String url=GlobalConsts.BASEURL+"loadMusics.jsp";
		MusicBiz biz=new MusicBiz(new Callback() {
			public void onSuccess(List<Music> musics) {
				//自定义Adapter  更新UI    主线程中执行
				Log.i("info", "onSuccess:"+musics.toString());
				MainActivity.this.musics = musics;
				adapter = new MusicAdapter(MainActivity.this, musics, lvMusics);
				lvMusics.setAdapter(adapter);
			}
		});
		biz.execute(url); //执行异步任务
	}

	private void setListeners() {
		//为控件添加监听器
		ibPlayOrPause.setOnClickListener(this);
		ibPrevious.setOnClickListener(this);
		ibNext.setOnClickListener(this);
		lvMusics.setOnItemClickListener(this);
		sbMusicProgress.setOnSeekBarChangeListener(this);
	}

	//控件初始化
	private void setViews() {
		ibPlayOrPause = (ImageButton) findViewById(R.id.ib_music_play_or_pause);
		ibPrevious = (ImageButton) findViewById(R.id.ib_music_previous);
		ibNext = (ImageButton) findViewById(R.id.ib_music_next);
		lvMusics = (ListView) findViewById(R.id.lv_musics);
		tvMusicTitle = (TextView) findViewById(R.id.tv_music_title);
		sbMusicProgress = (SeekBar) findViewById(R.id.sb_music_progress);
		tvMusicCurrentPosition = (TextView) findViewById(R.id.tv_music_current_position);
		tvMusicDuration = (TextView) findViewById(R.id.tv_music_duration);
	}

	@Override
	protected void onPause() {
		stopUpdateThread();
		super.onPause();
	}

	@Override
	protected void onRestart() {
		if (player != null && player.isPlaying()) {
			startUpdateThread();
		}
		super.onRestart();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (conn != null) {
			unbindService(conn);
			conn = null;
		}
		//停掉adapter中的工作线程
		adapter.stopThread();
	}

	private void startUpdateThread() {
		if (updateThread == null) {
			updateThread = new UpdateThread();
			isUpdateThreadRunning = true;
			updateThread.start();
		}
	}

	private void stopUpdateThread() {
		if (updateThread != null) {
			isUpdateThreadRunning = false;
			updateThread = null;
		}
	}

	private class UpdateThread extends Thread {
		private Runner runner = new Runner();

		@Override
		public void run() {
			while (isUpdateThreadRunning) {
				runOnUiThread(runner);

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		private class Runner implements Runnable {

			@Override
			public void run() {
				int currentPosition = player.getCurrentPosition();
				int duration = player.getDuration();
				int percent = currentPosition * 100 / duration;
				Music music = musics.get(player.getCurrentMusicIndex());
				tvMusicTitle.setText("正在播放：" + music.getName());
				if(!isStartTracking) {
					sbMusicProgress.setProgress(percent);
				}
				tvMusicCurrentPosition.setText(CommonUtils
						.getFormattedTime(currentPosition));
				tvMusicDuration.setText(CommonUtils.getFormattedTime(duration));

				if (lastMusicIndex != player.getCurrentMusicIndex()) {
					if(lastMusicIndex != -1) {
						musics.get(lastMusicIndex).setPlaying(false);
					}

					musics.get(player.getCurrentMusicIndex()).setPlaying(true);

					lastMusicIndex = player.getCurrentMusicIndex();

					adapter.notifyDataSetChanged();

					lvMusics.smoothScrollToPosition(player.getCurrentMusicIndex());
				}
			}

		}
	}

	private class InnerServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			player = (IMusicPlayer) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// （无视）
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_music_play_or_pause:
			if (player.isPlaying()) {
				player.pause();
				ibPlayOrPause
				.setImageResource(android.R.drawable.ic_media_play);
				stopUpdateThread();
			} else {
				player.play();
				ibPlayOrPause
				.setImageResource(android.R.drawable.ic_media_pause);
				startUpdateThread();
			}
			break;

		case R.id.ib_music_previous:
			player.previous();
			ibPlayOrPause.setImageResource(android.R.drawable.ic_media_pause);
			startUpdateThread();
			break;

		case R.id.ib_music_next:
			player.next();
			ibPlayOrPause.setImageResource(android.R.drawable.ic_media_pause);
			startUpdateThread();
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		player.play(position);
		ibPlayOrPause.setImageResource(android.R.drawable.ic_media_pause);
		startUpdateThread();
	}

	private int lastMusicIndex = -1;

	private boolean isStartTracking;

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		isStartTracking = true;
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		isStartTracking = false;
		int progress = seekBar.getProgress();
		player.play(progress * 1F);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// （无视）
	}

}