package com.bignerdranch.android.friends.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.bean.SmsBean;
import com.bignerdranch.android.friends.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class SmsAdapter extends RecyclerView.Adapter<SmsHolder> {

    private static final int TYPE_LEFT = 0;
    private static final int TYPE_RIGHT = 1;

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<SmsBean> mSmss = new ArrayList<>();

    public SmsAdapter(Context context, List<SmsBean> sms) {
        mContext = context;
        mSmss = sms;
        mLayoutInflater = LayoutInflater.from(context);
    }

    /**
     * 渲染具体的ViewHolder
     * @param parent ViewHolder的容器
     * @param viewType 一个标志，根据该标志可以实现渲染不同类型的ViewHolder
     */
    @Override
    public SmsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            case TYPE_LEFT:
                v = mLayoutInflater.inflate(R.layout.list_item_text_sms_receive, parent, false);
                return new SmsHolder(v);
            case TYPE_RIGHT:
                v = mLayoutInflater.inflate(R.layout.list_item_text_sms_send, parent, false);
                return new SmsHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(SmsHolder holder, int position) {
        SmsBean sms = mSmss.get(position);
        // 显示日期
        holder.mDate.setText(DateUtil.parse2(sms.getDate()));
        // 显示内容
        holder.mBody.setText(sms.getBody());
    }

    @Override
    public int getItemCount() {
        return mSmss.size();
    }

    /**
     * 决定元素的布局使用哪种类型
     * @param position 数据源的下标
     * @return 一个int标志，传递给onCreateViewHolder的第二个参数
     */
    @Override
    public int getItemViewType(int position) {
        SmsBean sms = mSmss.get(position);
        int type = sms.getType();
        switch (type) {
            case 1:
                return TYPE_LEFT;
            case 2:
                return TYPE_RIGHT;
        }
        return 3;
    }
}

class SmsHolder extends RecyclerView.ViewHolder {

    TextView mDate, mBody;

    public SmsHolder(View itemView) {
        super(itemView);

        mDate = (TextView) itemView
                .findViewById(R.id.list_item_text_sms_time);
        mBody = (TextView) itemView
                .findViewById(R.id.list_item_text_sms_body);
    }
}