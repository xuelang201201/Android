package cn.tedu.music_player_2.ui;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import cn.tedu.music_player_2.R;
import cn.tedu.music_player_2.adapter.MusicAdapter;
import cn.tedu.music_player_2.app.MusicPlayerApplication;
import cn.tedu.music_player_2.entity.Music;
import cn.tedu.music_player_2.service.PlayMusicService;
import cn.tedu.music_player_2.util.Consts;

public class CopyOfMainActivity extends Activity implements Consts, OnItemClickListener, View.OnClickListener {
	/**
	 * ImageButton：播放/暂停
	 */
	private ImageButton ibPlayOrPause;
	/**
	 * ImageButton：上一首
	 */
	private ImageButton ibPrevious;
	/**
	 * ImageButton：下一首
	 */
	private ImageButton ibNext;
	/**
	 * ListView：歌曲列表控件
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
	/**
	 * 是否正在播放
	 */
	private boolean isPlaying;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// 初始化控件
		ibPlayOrPause = (ImageButton) findViewById(R.id.ib_music_play_or_pause);
		ibPrevious = (ImageButton) findViewById(R.id.ib_music_previous);
		ibNext = (ImageButton) findViewById(R.id.ib_music_next);
		lvMusics = (ListView) findViewById(R.id.lv_musics);
		
		// 准备数据源
		app = (MusicPlayerApplication) getApplication();
		musics = app.getMusics();
		
		// 准备显示ListView
		adapter = new MusicAdapter(this, musics);
		lvMusics.setAdapter(adapter);
		
		// 为控件添加监听器
		ibPlayOrPause.setOnClickListener(this);
		ibPrevious.setOnClickListener(this);
		ibNext.setOnClickListener(this);
		lvMusics.setOnItemClickListener(this);
	}
	
	

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, PlayMusicService.class);
		
		switch (v.getId()) {
		case R.id.ib_music_play_or_pause:
			intent.putExtra(EXTRA_ACTION, ACTION_PLAY_OR_PAUSE);
			startService(intent);
			
			isPlaying = !isPlaying;
			
			if(isPlaying) {
				ibPlayOrPause.setImageResource(android.R.drawable.ic_media_pause);
			} else {
				ibPlayOrPause.setImageResource(android.R.drawable.ic_media_play);
			}
			break;

		case R.id.ib_music_previous:
			intent.putExtra(EXTRA_ACTION, ACTION_PREVIOUS);
			startService(intent);
			isPlaying = true;
			ibPlayOrPause.setImageResource(android.R.drawable.ic_media_pause);
			break;
			
		case R.id.ib_music_next:
			intent.putExtra(EXTRA_ACTION, ACTION_NEXT);
			startService(intent);
			isPlaying = true;
			ibPlayOrPause.setImageResource(android.R.drawable.ic_media_pause);
			break;
		}
	}



	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(this, PlayMusicService.class);
		intent.putExtra(EXTRA_ACTION, ACTION_PLAY_NEW);
		intent.putExtra(EXTRA_POSITION, position);
		startService(intent);
		isPlaying = true;
		ibPlayOrPause.setImageResource(android.R.drawable.ic_media_pause);
	}

}
