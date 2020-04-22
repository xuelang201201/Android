package com.tarena.myyserver.util;

import java.io.IOException;
import java.util.Properties;

/** ���ڶ�ȡdao.properties�����ļ�  */
public class DaoConfig {
	private static Properties prop=new Properties();
	// ��̬�����  �����ļ�  ���ص�ǰ��ʱִ��
	static{
		try {
			prop.load(DaoConfig.class.getClassLoader()
					.getResourceAsStream("com/tarena/myyserver/util/dao.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//ͨ��key  ��ȡvalue�ľ�̬����
	public static String getValue(String key){
		return prop.getProperty(key);
	}
	
	public static void main(String[] args) {
		System.out.println(getValue("empDao"));
	}
	
}

