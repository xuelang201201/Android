package tarena.day1202;

public class Test2 {
	public static void main(String[] args) {
		String s0 = "abcdefghijklmnopqrstuvwxyz";
		String s = "";
		//StringBuilder sb = new StringBuilder();
		/*
		 * 获得系统当前时间的毫秒值
		 * 毫秒值：1970-1-1 0点开始的毫秒值
		 */
		long t = System.currentTimeMillis();
		
		for(int i=0; i<100000; i++) {
			s += s0;
			//sb.append(s0);
		}
		
		t = System.currentTimeMillis()-t;
		System.out.println(t);
	}
}
