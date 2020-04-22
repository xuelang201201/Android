package com.bignerdranch.android.friends.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.bean.ConversationBean;
import com.bignerdranch.android.friends.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<ConversationBean> mConversations = new ArrayList<>();

    public ConversationAdapter(Context context, List<ConversationBean> conversation) {
        mContext = context;
        mConversations = conversation;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ConversationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.list_item_conversation, parent, false);
        return new ConversationHolder(v);
    }

    @Override
    public void onBindViewHolder(ConversationHolder holder, int position) {
        ConversationBean conversation = mConversations.get(position);
        if (conversation.getName() != null) {
            holder.mContact.setText(conversation.getName());
        } else {
            holder.mContact.setText(conversation.getNumber());
        }
        holder.mLastDate.setText(DateUtil.parse(conversation.getLastDate()));
        holder.mLastSms.setText(conversation.getLastSms());
    }

    @Override
    public int getItemCount() {
        return mConversations.size();
    }
}

class ConversationHolder extends RecyclerView.ViewHolder {

    TextView mContact, mLastDate, mLastSms;
    ImageView mIsRead;
    CheckBox mCheck;

    public ConversationHolder(View itemView) {
        super(itemView);

        mContact = (TextView) itemView
                .findViewById(R.id.list_item_conversation_contact);
        mLastDate = (TextView) itemView
                .findViewById(R.id.list_item_conversation_last_date);
        mLastSms = (TextView) itemView
                .findViewById(R.id.list_item_conversation_last_sms);
        mIsRead = (ImageView) itemView
                .findViewById(R.id.list_item_conversation_is_read);
        mCheck = (CheckBox) itemView
                .findViewById(R.id.list_item_conversation_check_box);
    }
}

