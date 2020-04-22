package cn.tedu.youlu.model.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import cn.tedu.youlu.activity.MyApplication;
import cn.tedu.youlu.entity.Conversation;
import cn.tedu.youlu.entity.Sms;
import cn.tedu.youlu.model.ISmsModel;

public class SmsModel implements ISmsModel {

	@Override
	public List<Conversation> loadAllConversations() {
		Uri uri = Uri.parse("content://mms-sms/conversations/");
		Context context = MyApplication.getContext();
		ContentResolver r = context.getContentResolver();
		String[] columns = {
				"thread_id",		//0	
				"address", 		//1
				"body",				//2
				"date"				//3
		};
		Cursor c = r.query(uri, columns, null, null, "date  desc");
		List<Conversation> cs = new ArrayList<Conversation>();
		while(c.moveToNext()){
			Conversation con = new Conversation();
			con.setId(c.getInt(0));
			con.setNumber(c.getString(1));
			con.setBody(c.getString(2));
			con.setDate(c.getLong(3));
			cs.add(con);
		}
		c.close();
		return cs;
		
		
		
		// for(int i=0; i<c.getColumnCount(); i++){
		// Log.i("info", c.getColumnName(i) +" ------ "+c.getString(i));
		// }
		// c.close();
	}

	@Override
	public List<Sms> findSmsByThreadId(int threadId) {
		Uri uri = Uri.parse("content://sms/");
		Context context = MyApplication.getContext();
		ContentResolver r = context.getContentResolver();
		String[] columns={
				"_id",			//0
				"body",			//1
				"date",			//2
				"type"			//3
		};
		Cursor c=r.query(uri, columns, "thread_id=?", new String[]{threadId+""}, "date");
		List<Sms> smss = new ArrayList<Sms>();
		while(c.moveToNext()){
			Sms sms = new Sms(
			c.getInt(0),
			c.getLong(2),
			c.getString(1), 
			c.getInt(3));
			smss.add(sms);
		}
		c.close();
		return smss;
	}

}
