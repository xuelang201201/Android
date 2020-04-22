package cn.tedu.youlu.model.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;

import cn.tedu.youlu.activity.MyApplication;
import cn.tedu.youlu.entity.Contact;
import cn.tedu.youlu.model.IContactModel;

public class ContactModel implements IContactModel{

	@Override
	public void loadContacts(final Callback callback) {
		//由于联系人过多时  查询较慢
		//所以需要异步执行
		new AsyncTask<String, String, List<Contact>>() {
			protected List<Contact> doInBackground(String... params) {
				List<Contact> contacts=loadContacts();
				//获取联系人列表
				return contacts;
			}
			//主线程中执行 
			//获取列表  调用回调方法  onResponse方法
			protected void onPostExecute(List<Contact> result) {
				callback.onResponse(result);
			};
		}.execute();
	}

	/**
	 * 查询所有联系人   (耗时  需要异步)
	 * @return
	 */
	private List<Contact> loadContacts() {
		List<Contact> cs =new ArrayList<Contact>();
		//先查contacts表  获取所有人的id
		Context context=MyApplication.getContext();
		ContentResolver r=context.getContentResolver();
		//  content://com.android.contacts/contact
		Uri contactUri=Contacts.CONTENT_URI;
		String[] columns = {
				Contacts._ID,					//0
				Contacts.PHOTO_ID		//1
		};
		Cursor c1=r.query(contactUri, columns, null, null, null);
		//再遍历所有人的id  通过id 去data查找其他数据
		while(c1.moveToNext()){
			Contact c=new Contact();
			int id = c1.getInt(0);
			int photoId = c1.getInt(1);
			c.setId(id);
			c.setPhotoId(photoId);
			//查data表
			Uri dataUri = Data.CONTENT_URI;
			String[] projection = {
					Data.DATA1,		//0
					Data.DATA15, 		//1
					Data.MIMETYPE	//2
			};
			Cursor c2=r.query(dataUri, projection, Data.RAW_CONTACT_ID+"=?", new String[]{""+id}, null);
			while(c2.moveToNext()){
				String data=c2.getString(0);
				String mt=c2.getString(2);
				if(mt.equals(Email.CONTENT_ITEM_TYPE)){
					//data描述的是邮箱
					c.setEmail(data);
				}else if(mt.equals(Phone.CONTENT_ITEM_TYPE)){
					//data描述的是电话
					c.setPhone(data);
				}else if(mt.equals("vnd.android.cursor.item/postal-address_v2")){
					//data描述的是地址
					c.setAddress(data);
				}else if(mt.equals("vnd.android.cursor.item/name")){
					//data描述的是名字
					c.setName(data);
				}
			}
			c2.close();
			cs.add(c);
		}
		c1.close();
		return cs;
	}

	
}


