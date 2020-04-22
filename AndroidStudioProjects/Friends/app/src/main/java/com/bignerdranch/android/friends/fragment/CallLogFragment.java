package com.bignerdranch.android.friends.fragment;

import android.app.AlertDialog;
import android.content.AsyncQueryHandler;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog.Calls;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.adapter.CallLogAdapter;
import com.bignerdranch.android.friends.app.MyApplication;
import com.bignerdranch.android.friends.bean.CallLogBean;
import com.bignerdranch.android.friends.listener.RecyclerItemClickListener;
import com.bignerdranch.android.friends.util.DateUtil;
import com.bignerdranch.android.friends.util.ShowDialog;

import java.util.ArrayList;
import java.util.List;

public class CallLogFragment extends BaseFragment {

    private RecyclerView mCallLogRecyclerView;
    private List<CallLogBean> mCallLogs = new ArrayList<>();
    private AsyncQueryHandler mAsyncQueryHandler;
    private CallLogBean mCallLog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAsyncQueryHandler = new MyAsyncQueryHandler(getActivity().getContentResolver());
        initQuery();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_call_log, container, false);

        // 初始化顶部标题栏
        initHeaderView(v);

        mCallLogRecyclerView = (RecyclerView) v
                .findViewById(R.id.fragment_call_log_recycler_view);
        mCallLogRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mCallLogRecyclerView.setHasFixedSize(true);
        setupAdapter();

        mCallLogRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), mCallLogRecyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                String number = mCallLogs.get(position).getNumber();
                                String name = mCallLogs.get(position).getName();
                                String phoneNumber;
                                if (number == null) {
                                    phoneNumber = name;
                                } else {
                                    phoneNumber = number;
                                }
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                                jump(intent, false);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {
                                ShowDialog showDialog = new ShowDialog(
                                        getActivity()).invoke(R.layout.dialog_call_log_content);

                                View dialogView = showDialog.getDialogView();
                                final AlertDialog dialog = showDialog.getDialog();

                                TextView mName = (TextView) dialogView
                                        .findViewById(R.id.dialog_call_log_click_name);
                                TextView mSendMessage = (TextView) dialogView
                                        .findViewById(R.id.dialog_call_log_click_send_message);
                                TextView mEditBeforeCall = (TextView) dialogView
                                        .findViewById(R.id.dialog_call_log_click_edit_before_call);
                                TextView mCopyNumber = (TextView) dialogView
                                        .findViewById(R.id.dialog_call_log_click_copy_number);
                                TextView mDeleteCallLog = (TextView) dialogView
                                        .findViewById(R.id.dialog_call_log_click_delete_call_log);
                                TextView mBatchDelete = (TextView) dialogView
                                        .findViewById(R.id.dialog_call_log_click_batch_delete);
                                TextView mBlackList = (TextView) dialogView
                                        .findViewById(R.id.dialog_call_log_click_black_list);

                                final String name = mCallLogs.get(position).getName();
                                final String number = mCallLogs.get(position).getNumber();
                                if (name != null) {
                                    mName.setText(name);
                                } else {
                                    mName.setText(number);
                                }

                                mSendMessage.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        // TODO 发送短信界面
                                    }
                                });

                                mEditBeforeCall.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        // TODO 呼叫前编辑
                                    }
                                });

                                mCopyNumber.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // 复制号码
                                        ClipboardManager manager = (ClipboardManager)
                                                MyApplication.context.getSystemService(Context.CLIPBOARD_SERVICE);
                                        String text;
                                        if (number != null) {
                                            text = number;
                                        } else {
                                            text = name;
                                        }
                                        ClipData clipData = ClipData.newPlainText("Label", text);
                                        manager.setPrimaryClip(clipData);
                                        dialog.dismiss();
                                        toast(getString(R.string.number_has_been_copied_to_the_clipboard));
                                    }
                                });

                                mDeleteCallLog.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        // TODO 删除通话记录
                                    }
                                });

                                mBatchDelete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });

                                mBlackList.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                            }
                        }));

        return v;
    }

    /**
     * 初始化顶部标题栏
     */
    private void initHeaderView(View v) {
        View headerView = v.findViewById(R.id.headerview);
        setHeaderTitle(headerView, getString(R.string.call_log));
    }

    private class MyAsyncQueryHandler extends AsyncQueryHandler {

        MyAsyncQueryHandler(ContentResolver cr) {
            super(cr);
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            mCallLogs.clear();
            if (cursor.moveToFirst()) {
                do {
                    // 取出数据
                    long date = cursor.getLong(cursor.getColumnIndex(Calls.DATE));
                    int id = cursor.getInt(cursor.getColumnIndex(Calls._ID));
                    String cacheName = cursor.getString(cursor.getColumnIndex(Calls.CACHED_NAME));
                    int type = cursor.getInt(cursor.getColumnIndex(Calls.TYPE));
                    String number = cursor.getString(cursor.getColumnIndex(Calls.NUMBER));
                    number = formatNumber(number);
                    int duration = cursor.getInt(cursor.getColumnIndex(Calls.DURATION));

                    // 封装bean
                    mCallLog = new CallLogBean();
                    mCallLog.setId(id);
                    mCallLog.setName(cacheName);
                    mCallLog.setNumber(number + "  ");
                    mCallLog.setType(type);
                    mCallLog.setDuration(duration);
                    if (cacheName == null || cacheName.equals("")) {
                        mCallLog.setName(number + "  ");
                    }

                    if (cacheName == null && number != null) {
                        mCallLog.setNumber(null);
                    }

                    // 设置时间
                    mCallLog.setDate(DateUtil.parse(date));
                    mCallLog.setDuration(duration);

                    mCallLogs.add(mCallLog);
                } while (cursor.moveToNext());
            }
        }
    }

    private void initQuery() {
        Uri uri = Calls.CONTENT_URI; // 通讯录的uri
        String[] projection = {
                Calls.DATE,
                Calls.NUMBER,
                Calls.TYPE,
                Calls.CACHED_NAME,
                Calls._ID,
                Calls.DURATION
        };
        mAsyncQueryHandler.startQuery(0, null, uri, projection, null, null, "date desc");
    }

    private void setupAdapter() {
        if (isAdded()) {
            mCallLogRecyclerView.setAdapter(
                    new CallLogAdapter(getActivity(), mCallLogs));
        }
    }
}
