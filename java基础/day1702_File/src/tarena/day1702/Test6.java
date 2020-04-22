package tarena.day1702;

import java.io.File;

public class Test6 {
	public static void main(String[] args) {
		File dir = new File("c:/windows/");
		
		String[] names = dir.list();
		File[] files = dir.listFiles();
		
		for(String s: names) {
			System.out.println(s);
		}
		System.out.println("-----------------------------");
		
		for(File f: files) {
			System.out.println(f.getName()+" : "+f.length());
		}
	}
}
