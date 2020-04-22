package tarena.day1202;

public class Test3 {
	public static void main(String[] args) {
		String s = " abc abc abc ";
		System.out.println(s);
		System.out.println("1位置字符:"+s.charAt(1));
		System.out.println("2位置字符:"+s.charAt(2));
		System.out.println("变大写:"+s.toUpperCase());
		System.out.println("变小写:"+s.toLowerCase());
		System.out.println("与xyz比大小:"+s.compareTo(" xyz"));
		System.out.println("以 abc开头:"+s.startsWith(" abc"));
		System.out.println("以bc 结尾:"+s.endsWith("bc "));
		System.out.println("找bc的位置:"+s.indexOf("bc"));
		System.out.println("从3位置向后找:"+s.indexOf("bc",3));
		System.out.println("从后向前找:"+s.lastIndexOf("bc"));
		System.out.println("找xxxxx的位置:"+s.indexOf("xxxxx"));
		System.out.println("a替换成-:"+s.replace('a', '-'));
		System.out.println("6到末尾的子串:"+s.substring(6));
		System.out.println("[6,10)的子串:"+s.substring(6, 10));
		System.out.println("去除两端空白:"+s.trim());
	}
}
