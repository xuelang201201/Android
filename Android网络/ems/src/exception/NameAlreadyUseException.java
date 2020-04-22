package exception;

public class NameAlreadyUseException extends Exception{
	//获取异常信息
	public String getMessage() {
		return "账号已存在";
	}
}
