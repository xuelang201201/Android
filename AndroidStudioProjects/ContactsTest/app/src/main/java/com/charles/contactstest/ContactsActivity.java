package com.charles.contactstest;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends Activity {

    private RecyclerView mContactsRecyclerView;
    private List<Contact> mContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        mContactsRecyclerView = (RecyclerView) findViewById(R.id.contacts_recycler_view);

        mContactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        setupAdapter();
        readContacts();
    }

    private void setupAdapter() {
        mContactsRecyclerView.setAdapter(
                new ContactsAdapter(this, mContacts));
    }

    private void readContacts() {
        Cursor cursor = null;
        try {
            // 查询联系人数据
            cursor = getContentResolver().query(Phone.CONTENT_URI, null, null, null, null);
            while (cursor.moveToNext()) {
                // 获取联系人姓名
                String displayName = cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
                // 获取联系人电话
                String number = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));

                Contact contact = new Contact();
                contact.setName(displayName);
                contact.setNumber(number);
                mContacts.add(contact);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
