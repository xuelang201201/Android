package tarena.day1806;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test1 {
	public static void main(String[] args) throws IOException {
		DataOutputStream out = new DataOutputStream(new FileOutputStream("d:/abc/f4"));
		
		out.writeUTF("张三");
		out.writeUTF("男");
		out.writeInt(23);
		
		out.writeUTF("李四");
		out.writeUTF("女");
		out.writeInt(22);
		
		out.writeUTF("王五");
		out.writeUTF("男");
		out.writeInt(24);
		
		out.close();
	}
}
