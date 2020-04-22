package tarena.day1307;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) throws ParseException {
		System.out.println(" ‰»Î∏°µ„ ˝£∫" );
		double a = new Scanner(System.in).nextDouble();
		
		DecimalFormat f = new DecimalFormat("\u00A4###,###.000");
		
		String s = f.format(a);
		System.out.println(s);
		
		///
		Number n = f.parse(s);
		System.out.println(n.doubleValue());
	}
}