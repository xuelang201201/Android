package tarena.day1301;

public class Test1 {
	public static void main(String[] args) {
		String s0 = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder();
		
		long t = System.currentTimeMillis();
		for(int i=0; i<1000000; i++) {
			sb.append(s0);
		}
		t = System.currentTimeMillis() - t;
		System.out.println(t);
	}
}
