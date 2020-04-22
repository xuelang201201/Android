package cn.tedu.listview_section;

public class Contact {
	private String name;
	private String number;
	private int photo;
	
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

	public int getPhoto() {
		return photo;
	}

	public void setPhoto(int photo) {
		this.photo = photo;
	}

	public Contact(String name, String number, int photo) {
		super();
		this.name = name;
		this.number = number;
		this.photo = photo;
	}
}
