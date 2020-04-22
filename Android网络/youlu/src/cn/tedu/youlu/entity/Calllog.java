package cn.tedu.youlu.entity;

public class Calllog {
	private int id;
	private String name;
	private String number;
	private int photoId;
	private int type;
	private long date;

	public Calllog() {
	}

	public Calllog(int id, String name, String number, int photoId, int type,
			long date) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.photoId = photoId;
		this.type = type;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

}
