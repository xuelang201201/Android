package com.tarena.myyserver.util;

import java.io.IOException;
import java.util.Properties;

/** 用于读取dao.properties配置文件  */
public class DaoConfig {
	private static Properties prop=new Properties();
	// 静态代码块  加载文件  加载当前类时执行
	static{
		try {
			prop.load(DaoConfig.class.getClassLoader()
					.getResourceAsStream("com/tarena/myyserver/util/dao.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//通过key  获取value的静态方法
	public static String getValue(String key){
		return prop.getProperty(key);
	}
	
	public static void main(String[] args) {
		System.out.println(getValue("empDao"));
	}
	
}

