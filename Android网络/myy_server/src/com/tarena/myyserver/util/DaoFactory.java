package com.tarena.myyserver.util;

/** 生产dao实现类对象的工厂类 */
public class DaoFactory {
	
	public static Object getInstance(String type){
		// 1. 通过type  
		//  读取配置文件获取实现类全限定名
		String className=DaoConfig.getValue(type);
		// 2. 使用java反射机制 创建对象  并且return
		Object obj=null;
		try {
			Class cl=Class.forName(className);
			obj = cl.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
