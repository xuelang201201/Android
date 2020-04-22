package tarena.day1501;

import java.math.BigDecimal;
import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {
		System.out.println(" ‰»ÎÀ„ Ω£∫");
		String s = new Scanner(System.in).nextLine();
		
		Formula f = new Formula(s);
		
		BigDecimal r = f.eval();
		System.out.println(r);
	}
}
