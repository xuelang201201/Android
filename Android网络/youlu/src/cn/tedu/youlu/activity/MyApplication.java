package cn.tedu.youlu.activity;

import android.app.Application;

public class MyApplication extends Application{
	private static MyApplication context;
	
	@Override
	public void onCreate() {
		super.onCreate();
		//�ѵ�ǰ���󱣴浽��̬��Ա��
		context=this;  
	}
	
	public static MyApplication getContext(){
		return context;
	}
	
}
