package tarena.day1202;

public class Test3 {
	public static void main(String[] args) {
		String s = " abc abc abc ";
		System.out.println(s);
		System.out.println("1λ���ַ�:"+s.charAt(1));
		System.out.println("2λ���ַ�:"+s.charAt(2));
		System.out.println("���д:"+s.toUpperCase());
		System.out.println("��Сд:"+s.toLowerCase());
		System.out.println("��xyz�ȴ�С:"+s.compareTo(" xyz"));
		System.out.println("�� abc��ͷ:"+s.startsWith(" abc"));
		System.out.println("��bc ��β:"+s.endsWith("bc "));
		System.out.println("��bc��λ��:"+s.indexOf("bc"));
		System.out.println("��3λ�������:"+s.indexOf("bc",3));
		System.out.println("�Ӻ���ǰ��:"+s.lastIndexOf("bc"));
		System.out.println("��xxxxx��λ��:"+s.indexOf("xxxxx"));
		System.out.println("a�滻��-:"+s.replace('a', '-'));
		System.out.println("6��ĩβ���Ӵ�:"+s.substring(6));
		System.out.println("[6,10)���Ӵ�:"+s.substring(6, 10));
		System.out.println("ȥ�����˿հ�:"+s.trim());
	}
}
