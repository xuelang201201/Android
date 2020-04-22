package cn.tedu.music_player_2.entity;

/**
 * Musicʵ����: �������ݵ�����������
 */
public class Music {
	/**
	 * ����·��
	 */
	private String path;
	/**
	 * ��������
	 */
	private String title;
	/**
	 * �����Ƿ����ڲ���
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
