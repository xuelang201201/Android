package cn.tedu.music_player_2.util;

public interface IMusicPlayer {
	/**
	 * 播放
	 */
	void play();

	/**
	 * 播放指定的歌曲
	 * 
	 * @param position
	 *            歌曲在列表中的位置
	 */
	void play(int position);

	/**
	 * 从指定的百分比开始播放歌曲
	 * 
	 * @param progress
	 *            百分比，该数据为整数，例如50%则该参数应该是50
	 */
	void play(float progress);

	/**
	 * 暂停
	 */
	void pause();

	/**
	 * 播放上一首
	 */
	void previous();

	/**
	 * 播放下一首
	 */
	void next();

	/**
	 * 是否正在播放
	 * 
	 * @return 如果正在播放，则返回true，反之，则返回false
	 */
	boolean isPlaying();

	/**
	 * 获取当前播放的位置
	 * 
	 * @return 当前播放的位置，单位：毫秒
	 */
	int getCurrentPosition();

	/**
	 * 获取当前播放的歌曲的总时长
	 * 
	 * @return 当前播放的歌曲的总时长，单位：毫秒
	 */
	int getDuration();

	/**
	 * 获取当前播放的歌曲的索引
	 * 
	 * @return 当前播放的歌曲的索引
	 */
	int getCurrentMusicIndex();

}
