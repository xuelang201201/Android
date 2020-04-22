package cn.tedu.youlu.activity;

import android.app.Application;

public class MyApplication extends Application{
	private static MyApplication context;
	
	@Override
	public void onCreate() {
		super.onCreate();
		//把当前对象保存到静态成员中
		context=this;  
	}
	
	public static MyApplication getContext(){
		return context;
	}
	
}
