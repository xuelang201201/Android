package cn.tedu.youlu.entity;

/**
 * √Ë ˆ“ªÃı∂Ã–≈
 */
public class Sms {
	private int id;
	private long date;
	private String body;
	private int type;

	public Sms() {
		// TODO Auto-generated constructor stub
	}

	public Sms(int id, long date, String body, int type) {
		super();
		this.id = id;
		this.date = date;
		this.body = body;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Sms [id=" + id + ", date=" + date + ", body=" + body
				+ ", type=" + type + "]";
	}
	
}
