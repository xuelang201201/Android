package cn.tedu.music_player_2.util;

/**
 * 提供常量的接口
 */
public interface Consts {
	/**
	 * 指令：播放/暂停
	 */
	int ACTION_PLAY_OR_PAUSE = 1;
	/**
	 * 指令：上一首
	 */
	int ACTION_PREVIOUS = 2;
	/**
	 * 指令：下一首
	 */
	int ACTION_NEXT = 3;
	/**
	 * 指令：播放新的歌曲，通常还需要在Intent中封装新的歌曲的索引
	 */
	int ACTION_PLAY_NEW = 4;

	/**
	 * Intent中封装数据的名称：指令
	 */
	String EXTRA_ACTION = "cn.tedu.intent.EXTRA_ACTION";
	/**
	 * Intent中封装数据的名称：歌曲的索引
	 */
	String EXTRA_POSITION = "cn.tedu.intent.EXTRA_POSITION";

}
