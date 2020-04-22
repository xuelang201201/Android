package tarena.day1603;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test1 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		
		Collections.addAll(list, "1462", "83", "372", "7", "296", "94");
		System.out.println(list);
		System.out.println("----------------------");
		Collections.sort(list);
		System.out.println("----------------------");
		System.out.println(list);
		
		System.out.println("----------------------");
		Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				
				int a = Integer.parseInt(o1);
				int b = Integer.parseInt(o2);
				if(a > b) {
					return 1;
				} else if(a < b) {
					return -1;
				} else {
					return 0;
				}
				
			}
		});
		System.out.println("----------------------");
		System.out.println(list);
	}
}
