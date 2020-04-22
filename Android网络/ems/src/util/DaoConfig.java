package util;

import java.io.IOException;
import java.util.Properties;

/** 用于读取properties配置文件 */
public class DaoConfig {
	private static Properties prop=new Properties();
	/* 静态代码块  类加载时执行1次*/
	static{
		try {
			prop.load(DaoConfig.class.getClassLoader()
					.getResourceAsStream("util/dao.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key){
		return prop.getProperty(key);
	}
	
	public static void main(String[] args) {
		System.out.println(getValue("empDao"));
		System.out.println(getValue("userDao"));
		System.out.println(getValue("Dao"));
	}
	
}




