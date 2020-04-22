package tarena.day2001;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Test1 {
	public static void main(String[] args) throws Exception {
		/*
		 * 1.新建解析器赋给 p
		 * 2.新建数据处理器赋给 h
		 * 3.调用解析器的解析方法
		 *   并接入数据处理器
		 */
		String path = "D:/workspace_1512/day2001_xml/src/email.xml";
		
		SAXParser p = SAXParserFactory.newInstance().newSAXParser();
		
		EmailHandler h = new EmailHandler();
		
		p.parse(path, h);
	}
}
