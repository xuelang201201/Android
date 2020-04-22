package com.bignerdranch.android.contacts;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;

import java.util.ArrayList;

public class ContactsManager {
    private static final String TAG = "ContactsManager";
    
    private ContentResolver mContentResolver;

    public ContactsManager(ContentResolver contentResolver) {
        mContentResolver = contentResolver;
    }

    /**
     * Search and fill the contact information by the contact name given.
     * @param name Only the name is necessary.
     */
    public Contact searchContact(String name) {
        Log.w(TAG, "**search start**");
        Contact contact = new Contact();
        contact.setName(name);
        Log.d(TAG, "search name: " + contact.getName());
        int id = getContactID(contact.getName());
        contact.setId(id);

        if(id == 0) {
            Log.d(TAG, contact.getName() + " not exist. exit.");
        } else {
            Log.d(TAG, "find id: " + id);
            //Fetch Phone Number
            Cursor cursor = mContentResolver.query(
                    Phone.CONTENT_URI,
                    new String[]{Phone.NUMBER, Phone.TYPE},
                    Data.CONTACT_ID + "='" + id + "'", null, null);
            while(cursor != null && cursor.moveToNext()) {
                String number = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
                contact.setNumber(number);
                contact.setPhoneType(cursor.getInt(cursor.getColumnIndex(Phone.TYPE)));
                Log.d(TAG, "find number: " + contact.getNumber());
                Log.d(TAG, "find numberType: " + contact.getPhoneType());
            }

            //Fetch email
            cursor = mContentResolver.query(
                    Email.CONTENT_URI,
                    new String[]{Email.DATA, Email.TYPE},
                    Data.CONTACT_ID + "='" + id + "'", null, null);
            while(cursor != null && cursor.moveToNext()) {
                contact.setEmail(cursor.getString(cursor.getColumnIndex(Email.DATA)));
                contact.setEmailType(cursor.getInt(cursor.getColumnIndex(Email.TYPE)));
                Log.d(TAG, "find email: " + contact.getEmail());
                Log.d(TAG, "find emailType: " + contact.getEmailType());
            }
        }
        Log.w(TAG, "**search end**");
        return contact;
    }

    /**
     * You must specify the contact's ID.
     * @param contact contacts in the phone's database
     * @throws Exception The contact's name should not be empty.
     */
    public void insertContact(Contact contact) {
        Log.w(TAG, "**add start**");
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();

        int id = getContactID(contact.getName());
        if (id != 0) {
            Log.d(TAG, "contact already exist. exit.");
        } else if (contact.getName().trim().equals("")) {
            Log.d(TAG, "contact name is empty. exit.");
        } else {
            ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)
                    .withValue(RawContacts.ACCOUNT_TYPE, null)
                    .withValue(RawContacts.ACCOUNT_NAME, null)
                    .build());
            ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
                    .withValueBackReference(Data.RAW_CONTACT_ID, 0)
                    .withValue(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(StructuredName.DISPLAY_NAME, contact.getName())
                    .build());
            Log.d(TAG, "add name: " + contact.getName());

            if (! contact.getNumber().trim().equals("")) {
                ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
                        .withValueBackReference(Data.RAW_CONTACT_ID, 0)
                        .withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
                        .withValue(Phone.NUMBER, contact.getNumber())
                        .withValue(Phone.TYPE, contact.getPhoneType())
                        .build());
                Log.d(TAG, "add number: " + contact.getNumber());
            }

            if (! contact.getEmail().trim().equals("")) {
                ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
                        .withValueBackReference(Data.RAW_CONTACT_ID, 0)
                        .withValue(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE)
                        .withValue(Email.DATA, contact.getEmail())
                        .withValue(Email.TYPE, contact.getEmailType())
                        .build());
                Log.d(TAG, "add email: " + contact.getEmail());
            }

            try {
                mContentResolver.applyBatch(ContactsContract.AUTHORITY, ops);
                Log.d(TAG, "add contact success.");
            } catch (Exception e) {
                Log.d(TAG, "add contact failed.");
                Log.e(TAG, e.getMessage());
            }
        }
        Log.w(TAG, "**add end**");

    }

    /**
     * Delete contacts who's name equals contact.getName();
     * @param contact
     */
    public void deleteContact(Contact contact) {
        Log.w(TAG, "**delete start**");
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();

        int id = getContactID(contact.getName());
        // delete contact
        ops.add(ContentProviderOperation.newDelete(RawContacts.CONTENT_URI)
                .withSelection(RawContacts.CONTACT_ID + "=" + id, null)
                .build());
        // delete contact information such as phone number,email
        ops.add(ContentProviderOperation.newDelete(Data.CONTENT_URI)
                .withSelection(Data.CONTACT_ID + "=" + id, null)
                .build());
        Log.d(TAG, "delete contact: " + contact.getName());

        try {
            mContentResolver.applyBatch(ContactsContract.AUTHORITY, ops);
            Log.d(TAG, "delete contact success");
        } catch (Exception e) {
            Log.d(TAG, "delete contact failed");
            Log.e(TAG, e.getMessage());
        }
        Log.w(TAG, "**delete end**");
    }

    /**
     * @param contactOld The contact wants to be updated. The name should exists.
     * @param contactNew
     */
    public void updateContact(Contact contactOld, Contact contactNew) {
        Log.w(TAG, "**update start**");
        int id = getContactID(contactOld.getName());
        if(id == 0) {
            Log.d(TAG, contactOld.getName()+" not exist.");
        } else if (contactNew.getName().trim().equals("")) {
            Log.d(TAG, "contact name is empty. exit.");
        } else if (getContactID(contactNew.getName()) != 0) {
            Log.d (TAG, "new contact name already exist. exit.");
        } else {

            ArrayList<ContentProviderOperation> ops = new ArrayList<>();

            // update name
            ops.add(ContentProviderOperation.newUpdate(Data.CONTENT_URI)
                    .withSelection(Data.CONTACT_ID + "=? AND " + Data.MIMETYPE + "=?",
                            new String[]{id + "", StructuredName.CONTENT_ITEM_TYPE})
                    .withValue(StructuredName.DISPLAY_NAME, contactNew.getName())
                    .build());
            Log.d(TAG, "update name: " + contactNew.getName());

            // update number
            if (!contactNew.getNumber().trim().equals("")) {
                ops.add(ContentProviderOperation.newUpdate(Data.CONTENT_URI)
                        .withSelection(Data.CONTACT_ID + "=? AND " + Data.MIMETYPE + "=?",
                                new String[]{id + "", Phone.CONTENT_ITEM_TYPE})
                        .withValue(Phone.NUMBER, contactNew.getNumber())
                        .withValue(Phone.TYPE, contactNew.getPhoneType())
                        .build());
                Log.d(TAG, "update number: " + contactNew.getNumber());
            }

            // update email if mail
            if (!contactNew.getEmail().trim().equals("")) {
                ops.add(ContentProviderOperation.newUpdate(Data.CONTENT_URI)
                        .withSelection(Data.CONTACT_ID + "=? AND " + Data.MIMETYPE + "=?",
                                new String[]{id + "", Email.CONTENT_ITEM_TYPE})
                        .withValue(Email.DATA, contactNew.getEmail())
                        .withValue(Email.TYPE, contactNew.getEmailType())
                        .build());
                Log.d(TAG, "update email: " + contactNew.getEmail());
            }

            try {
                mContentResolver.applyBatch(ContactsContract.AUTHORITY, ops);
                Log.d(TAG, "update success");
            } catch (Exception e) {
                Log.d(TAG, "update failed");
                Log.e(TAG, e.getMessage());
            }
        }
        Log.w(TAG, "**update end**");
    }

    /**
     * @param name The contact who you get the id from. The name of
     * the contact should be set.
     * @return 0 if contact not exist in contacts list. Otherwise return
     * the id of the contact.
     */
    private int getContactID(String name) {
        int id = 0;
        Cursor cursor = mContentResolver.query(
                Contacts.CONTENT_URI,
                new String[]{Contacts._ID},
                Contacts.DISPLAY_NAME + "='" + name + "'",
                null, null);
        if (cursor != null && cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex(Contacts._ID));
        }
        return id;
    }
}
