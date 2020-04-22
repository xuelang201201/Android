package com.charles.contactstest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Contact> mContacts;

    public ContactsAdapter(Context context, List<Contact> contacts) {
        mContext = context;
        mContacts = contacts;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ContactsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.list_item_contact, parent, false);
        return new ContactsHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactsHolder holder, int position) {
        Contact contact = mContacts.get(position);
        holder.mNameTextView.setText(contact.getName());
        holder.mNumberTextView.setText(contact.getNumber());
        if (position == mContacts.size() - 1) {
            holder.mTotalTextView.setVisibility(View.VISIBLE);
            holder.mTotalTextView.setText(getItemCount() + "位联系人");
        } else {
            holder.mTotalTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}

class ContactsHolder extends RecyclerView.ViewHolder {

    TextView mNameTextView, mNumberTextView, mTotalTextView;

    public ContactsHolder(View itemView) {
        super(itemView);

        mNameTextView = (TextView) itemView.findViewById(R.id.name);
        mNumberTextView = (TextView) itemView.findViewById(R.id.number);
        mTotalTextView = (TextView) itemView.findViewById(R.id.contacts_total);
    }
}
