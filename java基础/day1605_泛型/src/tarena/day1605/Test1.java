package tarena.day1605;

import java.util.Date;

public class Test1 {
	public static void main(String[] args) {
		Holder<String> h1 = new Holder<>();
		Holder<Double> h2 = new Holder<>();
		
		h1.setValue("abc");
		h2.setValue(3.14);
		
		String s = h1.getValue();
		double d = h2.getValue();
		
		System.out.println(s);
		System.out.println(d);
		
		///
		/*
		 * raw type
		 * ԭʼ���ͣ���Object ����
		 */
		Holder h3 = new Holder();  //�������Ͳ���Ҳ���ԣ��ᾯ�棬û��
		h3.setValue(123);
		h3.setValue(3.14);
		h3.setValue("abc");
		h3.setValue(new Date());
		
		Date v = (Date) h3.getValue();
		System.out.println(v);
	}
}
