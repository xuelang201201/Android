package cn.tedu.music_player_3.entity;

/**
 * 歌曲信息的实体类
 */
public class Music {
	
	private long id;
	private String title;
	private String path;
	private int size;
	private int duration;
	private String artist;
	private String album;
	private String albumKey;
	private String albumArt;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public String getAlbumKey() {
		return albumKey;
	}
	
	public void setAlbumKey(String albumKey) {
		this.albumKey = albumKey;
	}
	
	public String getAlbumArt() {
		return albumArt;
	}
	
	public void setAlbumArt(String albumArt) {
		this.albumArt = albumArt;
	}
	
	@Override
	public String toString() {
		return "Music [id=" + id + ", title=" + title + ", path=" + path
				+ ", size=" + size + ", duration=" + duration + ", artist="
				+ artist + ", album=" + album + ", albumKey=" + albumKey
				+ ", albumArt=" + albumArt + "]";
	}
}
