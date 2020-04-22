package tarena.day1303;

public class Test1 {
	public static void main(String[] args) {
		Integer a = new Integer(10);//�·���
		Integer b = Integer.valueOf(10);//����Ѵ��ڵĶ���
		Integer c = Integer.valueOf(10);//����Ѵ��ڵĶ���
		
		System.out.println(a == b);
		System.out.println(b == c);
		System.out.println(a.equals(b));
		
		//��Number �̳е�6��ȡֵ����
		System.out.println(a.byteValue());
		System.out.println(a.shortValue());
		System.out.println(a.intValue());
		System.out.println(a.longValue());
		System.out.println(a.floatValue());
		System.out.println(a.doubleValue());
		
		//�ַ���ת����
		System.out.println(Integer.parseInt("255"));
		System.out.println(Integer.parseInt("11111111", 2));
		System.out.println(Integer.parseInt("377", 8));
		System.out.println(Integer.parseInt("ff", 16));
		
		System.out.println(Integer.toBinaryString(255));//2����
		System.out.println(Integer.toOctalString(255)); //8����
		System.out.println(Integer.toHexString(255));   //16����
	}
}
