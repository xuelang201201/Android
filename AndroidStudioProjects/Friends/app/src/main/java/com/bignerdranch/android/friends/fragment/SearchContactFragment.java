package com.bignerdranch.android.friends.fragment;

import android.content.AsyncQueryHandler;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.adapter.SearchAdapter;
import com.bignerdranch.android.friends.bean.ContactBean;
import com.bignerdranch.android.friends.listener.RecyclerItemClickListener;
import com.bignerdranch.android.friends.view.SideNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class SearchContactFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mClear;
    private EditText mSearch;

    private List<ContactBean> mContacts = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private SearchAdapter mAdapter;
    /**
     * 快速索引的自定义view
     */
    private SideNavigationBar mSideNavigationBar;

    private TextView mContactsLetter;

    /**
     * 异步查询联系人
     */
    private AsyncQueryHandler mAsyncQueryHandler;

    public static SearchContactFragment newInstance() {
        return new SearchContactFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_contact, container, false);

        View headerView = v.findViewById(R.id.headerview);
        ImageView back = (ImageView) headerView.findViewById(R.id.search_back_image_view);
        back.setOnClickListener(this);
        mSearch = (EditText) headerView.findViewById(R.id.search_edit_text);
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
        mClear = (ImageView) headerView.findViewById(R.id.search_clear_text_image_view);
        mClear.setOnClickListener(this);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.fragment_search_contact_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        }));

        mContactsLetter = (TextView) v.findViewById(R.id.fragment_search_contact_letter_text_view);

        mSideNavigationBar = (SideNavigationBar) v.findViewById(R.id.fragment_search_contact_side_navigation_bar);

        setupAdapter();

        if (mContacts.size() > 0) {
            setAlphabetListener();
        }

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_back_image_view:
                getActivity().finish();
                break;
            case R.id.search_clear_text_image_view:
                mSearch.setText("");
                break;
        }
    }
    /**
     * 右侧导航栏快速索引
     */
    private void setAlphabetListener() {
        mSideNavigationBar.setListener(new SideNavigationBar.OnTouchLetterListener() {
            @Override
            public void onTouchLetter(String str) {
                // 1) 挪动RecyclerView
                mRecyclerView.scrollToPosition(
                        mAdapter.getPositionForSection(str.charAt(0)));
                // 2) 显示大字母
                mContactsLetter.setVisibility(View.VISIBLE);
                mContactsLetter.setText(str);
            }

            @Override
            public void onFinishTouch() {
                mContactsLetter.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setupAdapter() {
        if (isAdded()) {
            mAdapter = new SearchAdapter(getActivity(), mContacts);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
