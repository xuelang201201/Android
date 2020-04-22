package cn.tedu.music_player_3.ui;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import cn.tedu.music_player_3.R;
import cn.tedu.music_player_3.adapter.MusicAdapter;
import cn.tedu.music_player_3.app.MusicPlayerApplication;
import cn.tedu.music_player_3.entity.Music;
import cn.tedu.music_player_3.service.PlayMusicService;
import cn.tedu.music_player_3.util.CommonUtils;
import cn.tedu.music_player_3.util.Consts;

public class MainActivity extends Activity implements
	Consts,
	SeekBar.OnSeekBarChangeListener,
	View.OnClickListener,
	AdapterView.OnItemClickListener,
	DialogInterface.OnClickListener {
	/**
	 * TextView: 正在播放的歌曲的标题
	 */
	private TextView tvMusicTitle;
	/**
	 * TextView: 正在播放的歌曲的总时长
	 */
	private TextView tvMusicDuration;
	/**
	 * TextView: 当前播放到的时间
	 */
	private TextView tvMusicCurrentPosition;
	/**
	 * SeekBar: 进度条
	 */
	private SeekBar sbProgress;
	/**
	 * ListView
	 */
	private ListView lvMusics;
	/**
	 * ImmageButton: 播放/暂停
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
	 * 歌曲列表的Adapter
	 */
	private MusicAdapter musicAdapter;
	/**
	 * 歌曲的数据源 
	 */
	private List<Music> musics;
	/**
	 * Application
	 */
	private MusicPlayerApplication app;
	/**
	 * 广播接收者
	 */
	private BroadcastReceiver receiver;
	/**
	 * 退出时的对话框
	 */
	private AlertDialog exitDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 激活Service
		Intent intent = new Intent(this, PlayMusicService.class);
		startService(intent);

		// 注册广播接收者
		receiver = new InnerReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(ACTION_SET_PLAY_STATE);
		filter.addAction(ACTION_SET_PAUSE_STATE);
		filter.addAction(ACTION_UPDATE_PROGRESS);
		registerReceiver(receiver, filter);

		// 获取数据源
		app = (MusicPlayerApplication) getApplication();
		musics = app.getMusics();

		// 初始化控件
		lvMusics = (ListView) findViewById(R.id.lv_musics);
		ibPlayOrPause= (ImageButton) findViewById(R.id.ib_music_play_or_pause);
		ibPrevious = (ImageButton) findViewById(R.id.ib_music_previous);
		ibNext = (ImageButton) findViewById(R.id.ib_music_next);
		tvMusicTitle = (TextView) findViewById(R.id.tv_music_title);
		tvMusicDuration = (TextView) findViewById(R.id.tv_music_duration);
		tvMusicCurrentPosition = (TextView) findViewById(R.id.tv_music_current_position);
		sbProgress = (SeekBar) findViewById(R.id.sb_music_progress);

		// 显示ListView
		musicAdapter = new MusicAdapter(this, musics);
		lvMusics.setAdapter(musicAdapter);

		// 为控件配置监听器
		lvMusics.setOnItemClickListener(this);
		sbProgress.setOnSeekBarChangeListener(this);
		ibPlayOrPause.setOnClickListener(this);
		ibPrevious.setOnClickListener(this);
		ibNext.setOnClickListener(this);
		
		// 创建退出的对话框
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder
			.setTitle("退出")
			.setMessage("确定退出应用程序吗？歌曲将停止播放！")
			.setCancelable(true)
			.setPositiveButton("退出程序", this)
			.setNeutralButton("取消", this)
			.setNegativeButton("回到桌面", this);
		exitDialog = builder.create();
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();

		switch (v.getId()) {
		case R.id.ib_music_play_or_pause:
			intent.setAction(ACTION_PLAY_OR_PAUSE);
			sendBroadcast(intent);
			break;

		case R.id.ib_music_previous:
			intent.setAction(ACTION_PREVIOUS);
			sendBroadcast(intent);
			break;

		case R.id.ib_music_next:
			intent.setAction(ACTION_NEXT);
			sendBroadcast(intent);
			break;
		}
	}

	private class InnerReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// 获取Action
			String action = intent.getAction();
			// 判断Action
			if (ACTION_SET_PLAY_STATE.equals(action)) {
				// 设置界面为播放状态
				ibPlayOrPause.setImageResource(android.R.drawable.ic_media_pause);
				int position = intent.getIntExtra(EXTRA_POSITION, 0);
				int duration = intent.getIntExtra(EXTRA_DURATION, 0);
				tvMusicTitle.setText("正在播放：" + musics.get(position).getTitle());
				tvMusicDuration.setText(CommonUtils.getFormattedTime(duration));
			} else if (ACTION_SET_PAUSE_STATE.equals(action)) {
				// 设置界面为暂停状态
				ibPlayOrPause.setImageResource(android.R.drawable.ic_media_play);
			} else if (ACTION_UPDATE_PROGRESS.equals(action)) {
				// 更新播放进度
				int currentPosition = intent.getIntExtra(EXTRA_CURRENT_POSITON, 0);
				int percent = intent.getIntExtra(EXTRA_PERCENT, 0);
				tvMusicCurrentPosition.setText(CommonUtils.getFormattedTime(currentPosition));
				if(! isSeekBarTracking) {
					sbProgress.setProgress(percent);
				}
			}

		}

	}

	@Override
	protected void onDestroy() {
		// 取消注册广播接收者
		unregisterReceiver(receiver);
		// 停止Service
		Intent intent = new Intent(this, PlayMusicService.class);
		stopService(intent);
		super.onDestroy();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 判断按键
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 弹出对话框
			exitDialog.show();
			// 消费
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		intent.setAction(ACTION_PLAY);
		intent.putExtra(EXTRA_POSITION, position);
		sendBroadcast(intent);
	}

	/**
	 * 是否正在拖拽进度条
	 */
	private boolean isSeekBarTracking;

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// 标识进度条正在被拖拽
		isSeekBarTracking = true;
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// 标识进度条停止拖拽
		isSeekBarTracking = false;
		// 通知Service从指定的位置开始播放歌曲
		Intent intent = new Intent();
		intent.setAction(ACTION_PLAY_FROM_PERCENT);
		intent.putExtra(EXTRA_PERCENT, seekBar.getProgress() / 100F); // 将进度传递给Service，值例如：0.65
		sendBroadcast(intent);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case DialogInterface.BUTTON_POSITIVE:
			finish();
			break;

		case DialogInterface.BUTTON_NEGATIVE:
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MAIN); 
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);
			break;
			
		case DialogInterface.BUTTON_NEUTRAL:
			exitDialog.dismiss();
			break;
		}
	}
}
