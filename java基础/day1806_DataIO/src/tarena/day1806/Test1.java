package tarena.day1806;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test1 {
	public static void main(String[] args) throws IOException {
		DataOutputStream out = new DataOutputStream(new FileOutputStream("d:/abc/f4"));
		
		out.writeUTF("����");
		out.writeUTF("��");
		out.writeInt(23);
		
		out.writeUTF("����");
		out.writeUTF("Ů");
		out.writeInt(22);
		
		out.writeUTF("����");
		out.writeUTF("��");
		out.writeInt(24);
		
		out.close();
	}
}
