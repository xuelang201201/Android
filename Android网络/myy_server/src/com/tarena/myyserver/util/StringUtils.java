package com.tarena.myyserver.util;

import java.util.Random;

public class StringUtils {

	public static String genPicPath(){
		String[] chars=new String[]{"1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
		Random random=new Random();
		StringBuffer result=new StringBuffer();
		for(int i=0; i<20; i++){
			result.append(chars[random.nextInt(chars.length)]);
		}
		return result.toString();
	}
	
	public static void main(String[] args) {
		//System.out.println(genPicPath());
	}
	
}
