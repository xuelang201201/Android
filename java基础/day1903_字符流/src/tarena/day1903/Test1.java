package tarena.day1903;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Test1 {
	public static void main(String[] args) throws Exception {
		OutputStreamWriter out = 
				new OutputStreamWriter(
						new FileOutputStream(
								"d:/abc/f6"));
								//"d:/abc/f6), "UTF-8");
		
		out.write("中文范围：\\u4e00 - \\u9fa5");
		out.write('\n');
		out.write("打印 20902个字符，每行50个");
		out.write('\n');
		
		int count = 0;
		for(char c='\u4e00'; c<='\u9fa5'; c++) {
			out.write(c);
			count++;
			if(count == 50) {
				out.write('\n');
				count = 0;
			}
		}
	}
}
