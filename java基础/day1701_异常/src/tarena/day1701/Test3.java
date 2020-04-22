package tarena.day1701;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test3 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		Collections.addAll(
				list,
				"2016-1-22",
				"2016-1-7",
				"2016-1-17",
				"2016-1-9",
				"2016-1-15",
				"2016-1-30",
				"2016-1-5"
				);
		System.out.println(list);
		
		// main() --> sort() --> compare()
		
		Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date d1 = f.parse(o1);
					Date d2 = f.parse(o2);
					return d1.compareTo(d2);
				} catch (ParseException e) {
					RuntimeException re = new RuntimeException(e);
					throw re;
				}
			}
		});
		System.out.println(list);
	}
}
