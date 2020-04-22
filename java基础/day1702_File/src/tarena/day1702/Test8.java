package tarena.day1702;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class Test8 {
	public static void main(String[] args) {
		File dir = new File("c:/windows/");

		
		String[] names = dir.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				//*.exe
				return name.toLowerCase().endsWith(".exe");
			}
		});
		
		
		File[] files = dir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File f) {
				if(f.isDirectory()) {
					return false;
				}
				return f.length() >= 1024*1024;
			}
		});

		for(String s: names) {
			System.out.println(s);
		}
		System.out.println("-----------------------------");

		for(File f: files) {
			System.out.println(f.getName()+" : "+f.length());
		}
	}
}
