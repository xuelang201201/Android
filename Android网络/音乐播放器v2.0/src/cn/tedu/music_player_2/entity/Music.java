package cn.tedu.music_player_2.entity;

/**
 * Music实体类: 描述数据的属性特征的
 */
public class Music {
	/**
	 * 歌曲路径
	 */
	private String path;
	/**
	 * 歌曲标题
	 */
	private String title;
	/**
	 * 歌曲是否正在播放
	 */
	private boolean isPlaying;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	@Override
	public String toString() {
		return "Music [path=" + path + ", title=" + title + "]";
	}

}
