package com.bignerdranch.android.friends.util;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.provider.ContactsContract.CommonDataKinds.Organization;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.CommonDataKinds.StructuredPostal;
import android.provider.ContactsContract.CommonDataKinds.Website;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.PhoneLookup;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.app.MyApplication;
import com.bignerdranch.android.friends.bean.ContactBean;

public class ContactsManager {
    private static final String TAG = "ContactsManager";

    private ContentResolver mResolver;
    private Context mContext = MyApplication.context;

    /**
     * 添加联系人
     */
    public void insertContact(ContactBean contact) {
        // 往raw_contacts中添加数据，并获取添加的id号
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        mResolver = mContext.getContentResolver();
        ContentValues values = new ContentValues();
        long contactId = ContentUris.parseId(mResolver.insert(uri, values));

        // 往data中添加数据（要根据前面获取的id号）
        // 添加姓名
        String name = contact.getName();

        uri = Uri.parse("content://com.android.contacts/data");
        values.put(Data.RAW_CONTACT_ID, contactId);
        values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE); // 内容类型
        if (!name.equals("")) {
            values.put("data2", name);
        } else {
            values.put("data2", "（无姓名）");
        }
        mResolver.insert(uri, values);
        // 添加电话
        String number = contact.getNumber().get(0);
        String NUMBER_TYPE = contact.getPhoneTypeStr();
        int typePhone = 0;
        switch (NUMBER_TYPE) {
            case "手机":
                typePhone = Phone.TYPE_MOBILE;
                break;
            case "住宅":
                typePhone = Phone.TYPE_HOME;
                break;
            case "住宅传真":
                typePhone = Phone.TYPE_FAX_HOME;
                break;
            case "单位":
                typePhone = Phone.TYPE_WORK;
                break;
            case "单位手机":
                typePhone = Phone.TYPE_WORK_MOBILE;
                break;
            case "单位传真":
                typePhone = Phone.TYPE_FAX_WORK;
                break;
        }
        if (! number.equals("")) {
            values.clear();
            values.put(Data.RAW_CONTACT_ID, contactId);
            values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
            values.put(Phone.NUMBER, number);
            values.put(Phone.TYPE, typePhone);
            mResolver.insert(uri, values);
        }
        // 添加邮箱
        String email = contact.getEmail();
        String EMAIL_TYPE = contact.getEmailTypeStr();
        int typeEmail = 0;
        switch (EMAIL_TYPE) {
            case "工作":
                typeEmail = Email.TYPE_WORK;
                break;
            case "个人":
                typeEmail = Email.TYPE_HOME;
                break;
        }
        if (! email.equals("")) {
            values.clear();
            values.put(Data.RAW_CONTACT_ID, contactId);
            values.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);
            values.put(Email.DATA, email);
            values.put(Email.TYPE, typeEmail);
            mResolver.insert(uri, values);
        }

        // 添加公司,职位
        String company = contact.getCompany();
        String post = contact.getPost();
        if (! company.equals("")) {
            values.clear();
            values.put(Data.RAW_CONTACT_ID, contactId);
            values.put(Data.MIMETYPE, Organization.CONTENT_ITEM_TYPE);
            values.put(Organization.DATA, company);
            values.put(Organization.TITLE, post);
            mResolver.insert(uri, values);
        }
        // 添加地址
        String address = contact.getAddress();
        String ADDRESS_TYPE = contact.getAddressTypeStr();
        int typeAddress = 0;
        switch (ADDRESS_TYPE) {
            case "住宅":
                typeAddress = StructuredPostal.TYPE_HOME;
                break;
            case "单位":
                typeAddress = StructuredPostal.TYPE_WORK;
                break;
            case "其他":
                typeAddress = StructuredPostal.TYPE_OTHER;
                break;
        }
        if (! address.equals("")) {
            values.clear();
            values.put(Data.RAW_CONTACT_ID, contactId);
            values.put(Data.MIMETYPE, StructuredPostal.CONTENT_ITEM_TYPE);
            values.put(StructuredPostal.DATA, address);
            values.put(StructuredPostal.TYPE, typeAddress);
            mResolver.insert(uri, values);
        }
        // 添加事件
        String event = contact.getEvent();
        String TYPE_EVENT = contact.getEventTypeStr();
        int typeEvent = 0;
        switch (TYPE_EVENT) {
            case "生日":
                typeEvent = Event.TYPE_BIRTHDAY;
                break;
            case "周年纪念日":
                typeEvent = Event.TYPE_ANNIVERSARY;
                break;
            case "其他":
                typeEvent = Event.TYPE_OTHER;
                break;
        }
        if (!event.equals("")) {
            values.clear();
            values.put(Data.RAW_CONTACT_ID, contactId);
            values.put(Data.MIMETYPE, Event.CONTENT_ITEM_TYPE);
            values.put(Event.DATA, event);
            values.put(Event.TYPE, typeEvent);
            mResolver.insert(uri, values);
        }
        // 添加网站
        String website = contact.getWebsite();
        if (!website.equals("")) {
            values.clear();
            values.put(Data.RAW_CONTACT_ID, contactId);
            values.put(Data.MIMETYPE, Website.CONTENT_ITEM_TYPE);
            values.put(Website.DATA, website);
            mResolver.insert(uri, values);
        }
    }

    /**
     * 删除联系人
     */
    public boolean deleteContact(final ContactBean contact) {
        ShowDialog showDialog = new ShowDialog(mContext).invoke(R.layout.dialog_delete_contact);
        final AlertDialog dialog = showDialog.getDialog();
        View dialogView = showDialog.getDialogView();

        TextView confirm = (TextView) dialogView.findViewById(R.id.dialog_delete_contact_ok);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                // 真正删除
                Uri contactUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
                        Uri.encode(contact.getNumber().get(0)));
                Cursor cur = mResolver.query(contactUri, null, null, null, null);
                try {
                    if (cur.moveToFirst()) {
                        do {
                            if (cur.getString(cur.getColumnIndex(PhoneLookup.DISPLAY_NAME))
                                    .equalsIgnoreCase(contact.getName())) {
                                String lookupKey = cur.getString(cur.getColumnIndex(Contacts.LOOKUP_KEY));
                                Uri uri = Uri.withAppendedPath(Contacts.CONTENT_LOOKUP_URI, lookupKey);
                                mResolver.delete(uri, null, null);
                                Toast.makeText(MyApplication.context, "删除成功", Toast.LENGTH_SHORT).show();
                            }

                        } while (cur.moveToNext());
                    }

                } catch (Exception e) {
                    Log.e(TAG, "删除失败：" + e.getMessage());
                }
            }
        });
        TextView cancel = (TextView) dialogView.findViewById(R.id.dialog_delete_contact_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        return false;
    }

    /**
     * 更新联系人
     */
    public void updateContact(long rawContactId, String newNumber) {
        // TODO
        ContentValues values = new ContentValues();
        values.put(Phone.NUMBER, newNumber);
        values.put(Phone.TYPE, Phone.TYPE_MOBILE);

        String where = Data.RAW_CONTACT_ID + " = ? AND " + Data.MIMETYPE + " = ?";
        String[] whereParams = new String[] {rawContactId + "", Phone.CONTENT_ITEM_TYPE};

        mResolver.update(Data.CONTENT_URI, values, where, whereParams);
    }
}
