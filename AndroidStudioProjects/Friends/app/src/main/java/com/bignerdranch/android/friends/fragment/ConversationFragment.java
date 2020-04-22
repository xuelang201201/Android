package com.bignerdranch.android.friends.fragment;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.content.CursorLoader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.adapter.ConversationAdapter;
import com.bignerdranch.android.friends.app.MyApplication;
import com.bignerdranch.android.friends.bean.ConversationBean;
import com.bignerdranch.android.friends.constant.Constant.Position;
import com.bignerdranch.android.friends.listener.RecyclerItemClickListener;
import com.bignerdranch.android.friends.ui.NewSmsActivity;
import com.bignerdranch.android.friends.ui.SmsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ConversationFragment extends BaseFragment {

    private static final String EXTRA_CONVERSATION = "Conversation";

    private RecyclerView mConRecyclerView;
    private static List<ConversationBean> mConversations = new ArrayList<>();

    /**
     * 会话
     */
    private static final String conversatoinsUri = "content://sms/conversations/";

    /**
     * 异步查询短信
     */
    private AsyncQueryHandler mAsyncQueryHandler;

    private ConversationAdapter mAdapter;

    private View headerView;
    private LinearLayout mBaseLayout, mSearchLayout, mSelectLayout;
    private ImageView mClear, mBack, mCancel;
    private EditText mSearch;
    private TextView mCount, mAll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAsyncQueryHandler = new MyAsyncQueryHandler(getActivity().getContentResolver());
        initQuery();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_conversation, container, false);
        initView(v);

        mConRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mConRecyclerView.setHasFixedSize(true);
        mConRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideInput(view);
                mSearchLayout.setVisibility(View.INVISIBLE);
                mBaseLayout.setVisibility(View.VISIBLE);
                return false;
            }
        });

        mConRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), mConRecyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                ConversationBean conversation = mConversations.get(position);
                                // 如果不是多选状态，则进入点击事件的业务逻辑
                                Intent intent = new Intent(getActivity(), SmsActivity.class);
                                intent.putExtra(EXTRA_CONVERSATION, conversation);
                                jump(intent, false);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {
                            }
                        }));
        setupAdapter();
        return v;
    }

    private void initView(View v) {
        headerView = v.findViewById(R.id.headerview);
        mConRecyclerView = (RecyclerView) v
                .findViewById(R.id.fragment_con_recycler_view);
        mBaseLayout = (LinearLayout) headerView
                .findViewById(R.id.header_view_base_layout);
        mSearchLayout = (LinearLayout) headerView
                .findViewById(R.id.header_view_search_layout);
        mSelectLayout = (LinearLayout) headerView
                .findViewById(R.id.header_view_select_layout);
        mClear = (ImageView) headerView
                .findViewById(R.id.search_clear_text_image_view);
        mSearch = (EditText) headerView
                .findViewById(R.id.search_edit_text);
        mBack = (ImageView) headerView
                .findViewById(R.id.search_back_image_view);
        mCancel = (ImageView) headerView
                .findViewById(R.id.select_back_image_view);
        mCount = (TextView) headerView
                .findViewById(R.id.select_count_text_view);
        mAll = (TextView) headerView
                .findViewById(R.id.select_done_all_text_view);
        initHeaderView();
    }

    private void initHeaderView() {
        // 设置标题
        setHeaderTitle(headerView, getString(R.string.sms), Position.CENTER);
        // 设置右侧图标
        setHeaderImage(headerView, R.drawable.ic_add_sms, Position.RIGHT, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 点击跳转到“添加短消息”界面AddMessageActivity
                jump(NewSmsActivity.class, false);
            }
        });
        // 设置左侧图标
        setHeaderImage(headerView, R.drawable.ic_search_white, Position.LEFT, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mClear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mSearch.setText("");
                    }
                });
                mBaseLayout.setVisibility(View.INVISIBLE);
                mSearchLayout.setVisibility(View.VISIBLE);
                mSearch.requestFocus(); // 获取焦点
                Timer timer = new Timer(); // 设置定时器
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() { // 弹出软键盘的代码
                        InputMethodManager imm = (InputMethodManager)
                                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(mSearch, InputMethodManager.RESULT_SHOWN);
                        imm.toggleSoftInput(
                                InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                    }
                }, 200); // 设置200毫秒的弹出时长

                mSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int after, int count) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (mSearch.getText().toString().equals("")) {
                            mClear.setVisibility(View.INVISIBLE);
                        } else {
                            mClear.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
                mBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mSearchLayout.setVisibility(View.INVISIBLE);
                        mBaseLayout.setVisibility(View.VISIBLE);
                        hideInput(view);
                    }
                });
            }
        });
    }

    /**
     * 强制隐藏输入法键盘
     * @param view
     */
    private void hideInput(View view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void setupAdapter() {
        if (isAdded()) {
            mAdapter = new ConversationAdapter(getActivity(), mConversations);
            mConRecyclerView.setAdapter(mAdapter);
        }
    }

    private void initQuery() {
        Uri uri = Uri.parse(conversatoinsUri);

        String[] projection = new String[] {
                "groups.group_thread_id AS group_id", "groups.msg_count AS msg_count",
                "groups.group_date AS last_date", "sms.body AS last_sms", "sms.address AS address"};

        mAsyncQueryHandler.startQuery(0, null, uri, projection, null, null, "date desc");
    }

    private static class MyAsyncQueryHandler extends AsyncQueryHandler {

        MyAsyncQueryHandler(ContentResolver cr) {
            super(cr);
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            mConversations.clear();

            while (cursor.moveToNext()) {
                int groupId = cursor.getInt(cursor.getColumnIndex("group_id"));
                int msgCount = cursor.getInt(cursor.getColumnIndex("msg_count"));
                String lastSms = cursor.getString(cursor.getColumnIndex("last_sms"));
                String number = cursor.getString(cursor.getColumnIndex("address"));
                number = formatNumber(number);
                long lastDate = cursor.getLong(cursor.getColumnIndex("last_date"));

                String name = null;
                String[] projection = new String[] { Phone.DISPLAY_NAME, Phone.NUMBER };
                Uri uri = Uri.withAppendedPath(Phone.CONTENT_FILTER_URI, number);
                Cursor cur = MyApplication.context.getContentResolver().query(uri, projection, null, null, null);

                if (cur.moveToFirst()) {
                    name = cur.getString(cur.getColumnIndex(Phone.DISPLAY_NAME));
                }

                ConversationBean conversation = new ConversationBean();
                conversation.setGroupId(groupId);
                conversation.setCount(msgCount);
                conversation.setLastSms(lastSms);
                conversation.setName(name);
                conversation.setNumber(number);
                conversation.setLastDate(lastDate);
                mConversations.add(conversation);
            }

            CursorLoader loader = new CursorLoader(MyApplication.context);
            loader.deliverResult(cursor);
        }
    }
}
