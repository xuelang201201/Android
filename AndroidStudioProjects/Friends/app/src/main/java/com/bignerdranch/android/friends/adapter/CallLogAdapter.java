package com.bignerdranch.android.friends.adapter;

import android.content.Context;
import android.graphics.Color;
import android.provider.CallLog.Calls;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.bean.CallLogBean;

import java.util.List;

public class CallLogAdapter extends RecyclerView.Adapter<CallLogHolder> {

    private static final String TAG = "CallLogAdapter";
    private Context mContext;
    private List<CallLogBean> mCallLogs;
    private LayoutInflater mLayoutInflater;

    public CallLogAdapter(Context context, List<CallLogBean> callLogs) {
        mContext = context;
        mCallLogs = callLogs;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public CallLogHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.list_item_call_log, parent, false);
        return new CallLogHolder(v);
    }

    @Override
    public void onBindViewHolder(CallLogHolder holder, int position) {
        CallLogBean callLog = mCallLogs.get(position);
        // 根据电话类型（来电、已接、未接）分别设置
        switch (callLog.getType()) {
            case Calls.OUTGOING_TYPE:
                holder.mCallType.setVisibility(View.VISIBLE);
                holder.mName.setTextColor(Color.BLACK);
                holder.mCallType.setImageResource(R.drawable.ic_outgoing_type);
                break;
            case Calls.INCOMING_TYPE:
                holder.mCallType.setVisibility(View.GONE);
                holder.mName.setTextColor(Color.BLACK);
                break;
            case Calls.MISSED_TYPE:
                holder.mCallType.setVisibility(View.GONE);
                holder.mName.setTextColor(Color.RED);
                break;
        }
        holder.mName.setText(callLog.getName());
        holder.mPhone.setText(callLog.getNumber());
        holder.mTime.setText(callLog.getDate());
        // holder.mDuration.setText(callLog.getDuration());
    }

    @Override
    public int getItemCount() {
        return mCallLogs.size();
    }
}

class CallLogHolder extends RecyclerView.ViewHolder {

    TextView mName; // 电话簿姓名
    TextView mPhone; // 电话
    ImageView mCallType; // 图标
    TextView mTime; // 显示时间

    public CallLogHolder(View itemView) {
        super(itemView);

        mName = (TextView) itemView
                .findViewById(R.id.list_item_call_log_name);
        mPhone = (TextView) itemView
                .findViewById(R.id.list_item_call_log_number);
        mCallType = (ImageView) itemView
                .findViewById(R.id.list_item_call_log_out);
        mTime = (TextView) itemView
                .findViewById(R.id.list_item_call_log_time);
    }
}
