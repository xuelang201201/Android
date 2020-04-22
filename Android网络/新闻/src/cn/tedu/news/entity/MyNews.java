package cn.tedu.news.entity;

import java.util.List;

public class MyNews {
	private String reason;
	private List<String> result;
	private int error_code;

	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public List<String> getResult() {
		return result;
	}
	
	public void setResult(List<String> result) {
		this.result = result;
	}
	
	public int getError_code() {
		return error_code;
	}
	
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	@Override
	public String toString() {
		return "MyNews [reason=" + reason + ", result=" + result
				+ ", error_code=" + error_code + "]";
	}
	
}
