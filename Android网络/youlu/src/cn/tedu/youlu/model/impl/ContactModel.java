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
		//������ϵ�˹���ʱ  ��ѯ����
		//������Ҫ�첽ִ��
		new AsyncTask<String, String, List<Contact>>() {
			protected List<Contact> doInBackground(String... params) {
				List<Contact> contacts=loadContacts();
				//��ȡ��ϵ���б�
				return contacts;
			}
			//���߳���ִ�� 
			//��ȡ�б�  ���ûص�����  onResponse����
			protected void onPostExecute(List<Contact> result) {
				callback.onResponse(result);
			};
		}.execute();
	}

	/**
	 * ��ѯ������ϵ��   (��ʱ  ��Ҫ�첽)
	 * @return
	 */
	private List<Contact> loadContacts() {
		List<Contact> cs =new ArrayList<Contact>();
		//�Ȳ�contacts��  ��ȡ�����˵�id
		Context context=MyApplication.getContext();
		ContentResolver r=context.getContentResolver();
		//  content://com.android.contacts/contact
		Uri contactUri=Contacts.CONTENT_URI;
		String[] columns = {
				Contacts._ID,					//0
				Contacts.PHOTO_ID		//1
		};
		Cursor c1=r.query(contactUri, columns, null, null, null);
		//�ٱ��������˵�id  ͨ��id ȥdata������������
		while(c1.moveToNext()){
			Contact c=new Contact();
			int id = c1.getInt(0);
			int photoId = c1.getInt(1);
			c.setId(id);
			c.setPhotoId(photoId);
			//��data��
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
					//data������������
					c.setEmail(data);
				}else if(mt.equals(Phone.CONTENT_ITEM_TYPE)){
					//data�������ǵ绰
					c.setPhone(data);
				}else if(mt.equals("vnd.android.cursor.item/postal-address_v2")){
					//data�������ǵ�ַ
					c.setAddress(data);
				}else if(mt.equals("vnd.android.cursor.item/name")){
					//data������������
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


