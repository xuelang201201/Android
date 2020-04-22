package tarena.day1703;

import java.io.RandomAccessFile;
import java.util.Arrays;

public class Test1 {
	public static void main(String[] args) throws Exception {
		RandomAccessFile raf = new RandomAccessFile("d:/abc/f1", "rw");
		
		raf.write(97);//00 00 00 61 --> 61
		raf.write(98);
		raf.write(99);
		raf.write(356);//00 00 01 64 --> 64
		byte[] buf = {
			101,102,103,104,105,
			106,107,108,109,110
		};
		raf.write(buf);
		raf.write(buf, 6, 3);
		
		//��λ�±굽0λ��
		raf.seek(0);
		
		//���ֽڶ�ȡ��׼��ʽ
		int b;
		while((b = raf.read()) != -1) {
			System.out.println(b);
		}
		
		///
		raf.seek(0);
		
		//������ȡ��׼��ʽ
		buf = new byte[5];
		int n;//ÿ�ζ�ȡ������
		while((n = raf.read(buf)) != -1) {
			System.out.println(n+": "+Arrays.toString(buf));
		}
		
		raf.close();//�ͷ�ϵͳ��Դ
		System.out.println("��d:/abc/f1 �л�16���Ʋ鿴");
	}
}
