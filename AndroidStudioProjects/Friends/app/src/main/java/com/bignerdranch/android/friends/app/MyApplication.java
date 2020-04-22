package com.bignerdranch.android.friends.app;

import android.app.Application;

public class MyApplication extends Application {

	public static MyApplication context;

	@Override
	public void onCreate() {
		super.onCreate();
		// 把当前对象保存到静态成员中
		context = this;
	}

	public static MyApplication getContext() {
		return context;
	}
}
