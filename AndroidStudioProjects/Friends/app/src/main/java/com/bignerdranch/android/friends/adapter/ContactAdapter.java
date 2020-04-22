package com.bignerdranch.android.friends.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.bean.ContactBean;
import com.bignerdranch.android.friends.util.BitmapUtils;
import com.bignerdranch.android.friends.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactHolder>
        implements SectionIndexer {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<ContactBean> mContacts = new ArrayList<>();
    /**
     * 字母表分组工具
     */
    private SectionIndexer mIndexer;

    public ContactAdapter(Context context, List<ContactBean> contacts) {
        mContext = context;
        mContacts = contacts;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.list_item_contacts, parent, false);
        return new ContactHolder(v);
    }

    @Override
    public void onBindViewHolder(final ContactHolder holder, final int position) {
        ContactBean contact = mContacts.get(position);
        // 显示姓名
        holder.mNameTextView.setText(contact.getName());
        // 姓名的分组字母是否显示
        if (position == getPositionForSection(getSectionForPosition(position))) {
            // 显示
            holder.mSortKeyTextView.setText(contact.getSortKey());
            holder.mSortKeyLayout.setVisibility(View.VISIBLE);
        } else {
            // 不显示
            holder.mSortKeyLayout.setVisibility(View.GONE);
        }
        // 设置头像
        Bitmap bitmap = BitmapUtils.loadBitmap(mContext, contact.getAvatarId());
        if (bitmap != null) {
            holder.mAvatarImageView.setImageBitmap(bitmap);
        } else {
            holder.mAvatarImageView.setImageResource(R.drawable.ic_account);
        }
        // 设置信息
        if (contact.getCompany() != null) {
            holder.mContactInfoTextView.setText(contact.getCompany());
            holder.mContactInfoTextView.setVisibility(View.VISIBLE);
        } else {
            holder.mContactInfoTextView.setText("");
            holder.mContactInfoTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    /**
     * 当前适配器传入一个分组工具
     * @param indexer 索引
     */
    public void setIndexer(SectionIndexer indexer) {
        mIndexer = indexer;
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int section) {
        for (int i = 0; i < mContacts.size(); i++) {
            if (section == getSectionForPosition(i)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        // position位置的contact的sortKey属性所对应的char
        return mContacts.get(position).getSortKey().charAt(0);
    }
}

class ContactHolder extends RecyclerView.ViewHolder {

    TextView mSortKeyTextView, mNameTextView, mContactInfoTextView;
    LinearLayout mSortKeyLayout, mNameLayout;
    CircleImageView mAvatarImageView;

    public ContactHolder(View itemView) {
        super(itemView);

        mSortKeyTextView = (TextView) itemView
                .findViewById(R.id.list_item_contacts_sort_key);
        mNameTextView = (TextView) itemView
                .findViewById(R.id.list_item_contacts_name);
        mSortKeyLayout = (LinearLayout) itemView
                .findViewById(R.id.list_item_contacts_sort_key_layout);
        mNameLayout = (LinearLayout) itemView
                .findViewById(R.id.list_item_contacts_name_layout);
        mAvatarImageView = (CircleImageView) itemView
                .findViewById(R.id.list_item_contacts_avatar);
        mContactInfoTextView = (TextView) itemView
                .findViewById(R.id.list_item_contacts_info);
    }
}