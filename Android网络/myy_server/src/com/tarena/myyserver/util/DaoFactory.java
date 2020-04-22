package com.tarena.myyserver.util;

/** ����daoʵ�������Ĺ����� */
public class DaoFactory {
	
	public static Object getInstance(String type){
		// 1. ͨ��type  
		//  ��ȡ�����ļ���ȡʵ����ȫ�޶���
		String className=DaoConfig.getValue(type);
		// 2. ʹ��java������� ��������  ����return
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
