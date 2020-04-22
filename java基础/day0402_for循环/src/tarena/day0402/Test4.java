package tarena.day0402;

public class Test4 {
	public static void main(String[] args) {
		//随机产生[0,1)范围的数字
		//double a = Math.random();
		//System.out.println(a);
		
		for(int i=1; ;i++) {
			double a = Math.random();
			if(a > 0.999) {
				System.out.println(i+": "+a);
				break; //中断循环（跳出循环）
			}
		}
	}
}
