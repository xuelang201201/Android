package com.bignerdranch.android.contacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ContactsManager cm = new ContactsManager(this.getContentResolver());

        cm.searchContact("张一");

        Contact contact = new Contact();
        contact.setName("张一");
        contact.setEmail("test@test.com");
        contact.setNumber("123456789");
        contact.setEmailType(1);
        contact.setPhoneType(0);
        contact.setAddress("四川 自贡");

        // test insertContact
        cm.insertContact(contact);
        cm.searchContact("张一");

        // test updateContact
        Contact contactNew = new Contact(contact);
        contactNew.setName("张二");
        contactNew.setNumber("987654321");
        contactNew.setEmail("newEmail@test");
        contactNew.setAddress("四川 成都");
        contactNew.setCompany("四川理工学院");
        contactNew.setPost("学生");
        contactNew.setEvent("2016-10-7");
        cm.updateContact(contact, contactNew);
        cm.searchContact("张一");
        cm.searchContact("张二");

        // test deleteContact
        cm.deleteContact(contactNew);
        cm.searchContact("张二");
    }
}
