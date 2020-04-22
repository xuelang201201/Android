package tarena.day1402;

import java.util.Calendar;
import static java.util.Calendar.*;

public class Test1 {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		System.out.println(c);
		
		System.out.println(c.get(YEAR));
		System.out.println(c.get(MONTH));
		System.out.println(c.get(DAY_OF_MONTH));
		
		c.set(YEAR, 2000);
		System.out.println(c.getTime());
		c.set(2000, 1, 1, 0, 0, 0);
		System.out.println(c.getTime());
		c.add(DAY_OF_MONTH, 12);
		System.out.println(c.getTime());
		int days = c.getActualMaximum(DAY_OF_MONTH);
		System.out.println(days);
		System.out.println(c.get(DAY_OF_WEEK));
		c.setTimeInMillis(0);
		System.out.println(c.getTimeInMillis());
	}
}
