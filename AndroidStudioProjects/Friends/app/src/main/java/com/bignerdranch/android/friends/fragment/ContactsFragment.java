package com.bignerdranch.android.friends.fragment;

import android.app.AlertDialog;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.provider.ContactsContract.CommonDataKinds.Organization;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.provider.ContactsContract.CommonDataKinds.StructuredPostal;
import android.provider.ContactsContract.CommonDataKinds.Website;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.support.v4.content.CursorLoader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AlphabetIndexer;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.adapter.ContactAdapter;
import com.bignerdranch.android.friends.app.MyApplication;
import com.bignerdranch.android.friends.bean.ContactBean;
import com.bignerdranch.android.friends.constant.Constant.Position;
import com.bignerdranch.android.friends.listener.RecyclerItemClickListener;
import com.bignerdranch.android.friends.ui.AddContactActivity;
import com.bignerdranch.android.friends.ui.ContactDetailActivity;
import com.bignerdranch.android.friends.ui.SearchContactActivity;
import com.bignerdranch.android.friends.util.ShowDialog;
import com.bignerdranch.android.friends.view.SideNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends BaseFragment {

    private static final String TAG = "ContactsFragment";
    private static final String EXTRA_CONTACT = "contact";
    private static final String EXTRA_JUMP_FROM = "jump_from";
    private static final String EXTRA_LIST_CONTACT = "contacts";
    private static final String EXTRA_ADD = "add";
    private static final String EXTRA_EDIT = "edit";

    private ContactBean contact;

    /**
     * 分组的布局
     */
    private LinearLayout mTitleLayout;
    /**
     * 分组上显示的字母
     */
    private TextView mTitle;
    /**
     * 联系人RecyclerView
     */
    private RecyclerView mContactsRecyclerView;
    /**
     * 屏幕中心显示的大字母
     */
    private TextView mContactsLetter;
    /**
     * 联系人列表适配器
     */
    private ContactAdapter mAdapter;
    /**
     * 联系人
     */
    private List<ContactBean> mContacts = new ArrayList<>();
    /**
     * 自定义字母表的排序规则
     */
    private String alphabet = "❤ABCDEFGHIJKLMNOPQRSTUVWXYZ#";
    /**
     * 上次第一个可见元素，用于滚动时记录标识
     */
    private int lastFirstVisibleItem = -1;
    /**
     * 快速索引的自定义view
     */
    private SideNavigationBar mSideNavigationBar;
    /**
     * 异步查询联系人
     */
    private AsyncQueryHandler mAsyncQueryHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAsyncQueryHandler = new MyAsyncQueryHandler(getActivity().getContentResolver());
        initQuery();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contacts, container, false);
        // 初始化视图
        initView(v);
        mContactsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mContactsRecyclerView.setHasFixedSize(true);
        // 配置adapter
        setupAdapter();

        mContactsRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), mContactsRecyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = intentExtra(position);
                                jump(intent, false);
                            }

                            @Override
                            public void onItemLongClick(View view, final int position) {

                                ShowDialog showDialog = new ShowDialog(getActivity()).invoke(
                                        R.layout.dialog_contact_long_click_content);

                                View dialogView = showDialog.getDialogView();
                                final AlertDialog dialog = showDialog.getDialog();

                                TextView mName = (TextView) dialogView
                                        .findViewById(R.id.dialog_contact_long_click_name);
                                TextView mCheck = (TextView) dialogView.
                                        findViewById(R.id.dialog_contact_long_click_check);
                                TextView mEdit = (TextView) dialogView
                                        .findViewById(R.id.dialog_contact_long_click_edit);
                                TextView mDelete = (TextView) dialogView.
                                        findViewById(R.id.dialog_contact_long_click_delete);

                                contact = mContacts.get(position);

                                if (contact.getName() != null) {
                                    mName.setText(contact.getName());
                                } else {
                                    mName.setText(contact.getNumber().get(0));
                                }
                                mCheck.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        Intent intent = intentExtra(position);
                                        jump(intent, false);
                                    }
                                });

                                mEdit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        Intent intent = new Intent(getActivity(), AddContactActivity.class);
                                        intent.putExtra(EXTRA_JUMP_FROM, EXTRA_EDIT);
                                        intent.putExtra(EXTRA_CONTACT, contact);
                                        jump(intent, false);
                                    }
                                });

                                mDelete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        // TODO 跳转界面，删除联系人
                                        toast(getString(R.string.delete_contact));
                                    }
                                });
                            }
                        }));

        return v;
    }

    /**
     * 携带信息跳转界面
     * @param position 点击的item
     * @return 绑定信息的intent
     */
    private Intent intentExtra(int position) {
        contact = mContacts.get(position);
        Intent intent = new Intent(getActivity(), ContactDetailActivity.class);
        intent.putExtra(EXTRA_JUMP_FROM, EXTRA_EDIT);
        intent.putExtra(EXTRA_CONTACT, contact);
        return intent;
    }

    /**
     * 初始化视图
     */
    private void initView(View v) {
        initHeaderView(v);

        mContactsRecyclerView = (RecyclerView) v
                .findViewById(R.id.fragment_contacts_recycler_view);
        mSideNavigationBar = (SideNavigationBar) v
                .findViewById(R.id.side_navigation_bar);
        mContactsLetter = (TextView) v
                .findViewById(R.id.fragment_contacts_letter);
        mTitleLayout = (LinearLayout) v
                .findViewById(R.id.fragment_contacts_title_layout);
    }

    /**
     * 初始化标题栏
     */
    private void initHeaderView(View v) {
        // 标题
        View headerView = v.findViewById(R.id.headerview);
        // 设置标题
        setHeaderTitle(headerView, getString(R.string.contact), Position.CENTER);
        // 设置右侧图标
        setHeaderImage(headerView, R.drawable.ic_add_contact, Position.RIGHT, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 点击跳转到“添加联系人”界面AddContactActivity
                Intent intent = new Intent(getActivity(), AddContactActivity.class);
                intent.putExtra(EXTRA_JUMP_FROM, EXTRA_ADD);
                jump(intent, false);
            }
        });
        // 设置左侧图标
        setHeaderImage(headerView, R.drawable.ic_search_white, Position.LEFT, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jump(SearchContactActivity.class, false);
            }
        });
    }

    private void initQuery() {
        Uri uri = Phone.CONTENT_URI; // 联系人的uri

        String[] projection = new String[] {
                Contacts.DISPLAY_NAME, "sort_key", Phone.NUMBER,
                Photo.PHOTO_ID, Phone.CONTACT_ID, Phone.TYPE };

        // 如果android操作系统版本4.4或4.4以上就要用phonebook_label而不是sort_key字段
        if (Build.VERSION.SDK_INT >= 19) {
            projection[1] = "phonebook_label";
        }

        mAsyncQueryHandler.startQuery(0, null, uri, projection, null, null, "sort_key");
    }

    private class MyAsyncQueryHandler extends AsyncQueryHandler {

        MyAsyncQueryHandler(ContentResolver cr) {
            super(cr);
        }

        /**
         * 获取手机联系人信息
         */
        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            mContacts.clear();
            ContentResolver resolver = MyApplication.context.getContentResolver();
            if (cursor.moveToFirst()) {
                do {
                    // 联系人姓名
                    String name = cursor.getString(0);
                    // sort key
                    String sortKey = getSortKey(cursor.getString(1));
                    // 联系人电话
                    List<String> numbers = new ArrayList<>();
                    String number = cursor.getString(2);
                    number = formatNumber(number);
                    numbers.add(number);
                    // 联系人头像id
                    int avatarId = cursor.getInt(3);
                    // 联系人id
                    int contactId = cursor.getInt(4);
                    // 电话类型
                    int phoneType = cursor.getInt(5);

                    // 联系人邮箱
                    Cursor emailCur = resolver.query(Email.CONTENT_URI, null,
                            Email.CONTACT_ID + " = " + contactId, null, null);
                    String email = null;
                    int emailType = 0;
                    while (emailCur.moveToNext()) {
                        email = emailCur.getString(emailCur.getColumnIndex(Email.DATA));
                        emailType = emailCur.getInt(emailCur.getColumnIndex(Email.TYPE));
                    }
                    // 联系人公司
                    String orgWhere = Data.CONTACT_ID + " = ? AND " + Data.MIMETYPE + " = ?";
                    String[] orgWhereParams = new String[] {contactId + "", Organization.CONTENT_ITEM_TYPE};
                    Cursor orgCur = resolver.query(Data.CONTENT_URI, null, orgWhere, orgWhereParams, null);
                    String company = null;
                    String post = null;
                    if (orgCur.moveToNext()) {
                        // 组织名（公司名）
                        company = orgCur.getString(orgCur.getColumnIndex(Organization.COMPANY));
                        // 职位
                        post = orgCur.getString(orgCur.getColumnIndex(Organization.TITLE));
                    }

                    // 联系人地址
                    Cursor addressCur = resolver.query(
                            StructuredPostal.CONTENT_URI, null, StructuredPostal.CONTACT_ID
                                    + " = " + contactId, null, null);
                    int postFormattedNdx = addressCur.getColumnIndex(StructuredPostal.FORMATTED_ADDRESS);
                    int postStreetNdx = addressCur.getColumnIndex(StructuredPostal.STREET);

                    String address = null;
                    int addressType = 0;
                    while (addressCur.moveToNext()) {
                        String str1 = addressCur.getString(postFormattedNdx);
                        String str2 = addressCur.getString(postStreetNdx);

                        addressType = addressCur.getInt(addressCur.getColumnIndex(StructuredPostal.TYPE));

                        if (str2 != null) {
                            if (str1.equals(str2)) {
                                address = str1;
                            } else {
                                address = str1 + str2;
                            }
                        } else {
                            address = str1;
                        }
                    }

                    // 联系人事件
                    int eventType = 0;
                    String event = null;
                    String[] projection = new String[] {Event.START_DATE, Event.TYPE};
                    String filter = Data.MIMETYPE + " = ? AND " + Data.CONTACT_ID + " =?";
                    String parameters[] = {Event.CONTENT_ITEM_TYPE, contactId + ""};
                    Cursor eventCur = resolver.query(Data.CONTENT_URI, projection, filter, parameters, null);
                    if (eventCur.moveToFirst()) {
                        for (eventCur.moveToFirst(); ! eventCur.isAfterLast(); eventCur.moveToNext()) {
                            event = eventCur.getString(eventCur.getColumnIndex(Event.START_DATE));
                            eventType = eventCur.getInt(eventCur.getColumnIndex(Event.TYPE));
                        }
                    }

                    // 网站
                    String where = Data.CONTACT_ID + " = ? AND " + Data.MIMETYPE + " = ?";
                    String[] whereParams = new String[] {contactId + "", Website.CONTENT_ITEM_TYPE};
                    String website = null;
                    Cursor websiteCur = resolver.query(Data.CONTENT_URI, null, where, whereParams, null);
                    if (websiteCur.moveToFirst()) {
                        website = websiteCur.getString(websiteCur.getColumnIndex(Website.URL));
                    }

                    // 电话类型字符串
                    String typePhone = null;
                    switch (phoneType) {
                        case Phone.TYPE_MOBILE:
                            typePhone = getString(R.string.phone_type_mobile);
                            break;

                        case Phone.TYPE_HOME:
                            typePhone = getString(R.string.phone_type_home);
                            break;

                        case Phone.TYPE_FAX_HOME:
                            typePhone = getString(R.string.phone_type_fax_home);
                            break;

                        case Phone.TYPE_WORK:
                            typePhone = getString(R.string.phone_type_work);
                            break;

                        case Phone.TYPE_WORK_MOBILE:
                            typePhone = getString(R.string.phone_type_work_mobile);
                            break;

                        case Phone.TYPE_FAX_WORK:
                            typePhone = getString(R.string.phone_type_fax_work);
                            break;
                    }

                    // 邮箱类型字符串
                    String typeEmail = null;
                    switch (emailType) {
                        case Email.TYPE_HOME:
                            typeEmail = getString(R.string.email_type_own);
                            break;
                        case Email.TYPE_WORK:
                            typeEmail = getString(R.string.email_type_work);
                            break;
                    }

                    // 地址类型字符串
                    String typeAddress = null;
                    switch (addressType) {
                        case StructuredPostal.TYPE_HOME:
                            typeAddress = getString(R.string.address_home);
                            break;
                        case StructuredPostal.TYPE_WORK:
                            typeAddress = getString(R.string.address_work);
                            break;
                        case StructuredPostal.TYPE_OTHER:
                            typeAddress = getString(R.string.other);
                            break;
                    }

                    // 事件类型字符串
                    String typeEvent = null;
                    switch (eventType) {
                        case Event.TYPE_BIRTHDAY:
                            typeEvent = getString(R.string.birthday);
                            break;
                        case Event.TYPE_ANNIVERSARY:
                            typeEvent = getString(R.string.anniversary);
                            break;
                        case Event.TYPE_OTHER:
                            typeEvent = getString(R.string.other);
                            break;
                    }

                    // 获取收藏夹联系人
                    Cursor favCur = getActivity().getContentResolver().query(
                            Contacts.CONTENT_URI, null, Contacts.STARRED + " = 1 " , null, null);

                    while (favCur != null && favCur.moveToNext()) {

                        long id = favCur.getLong(favCur.getColumnIndex("_id"));
                        Cursor c = getActivity().getContentResolver().query(
                                Phone.CONTENT_URI,
                                null,
                                Phone.CONTACT_ID + "=" + id, null, null);

                        while (c != null && c.moveToNext()) {
                            String favName = favCur.getString(favCur.getColumnIndex("display_name"));
                            String favNumber = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
                            List<String> favNumbers = new ArrayList<>();
                            favNumber = formatNumber(favNumber);
                            favNumbers.add(favNumber);
                        }
                    }

                    contact = new ContactBean();
                    contact.setName(name);
                    contact.setSortKey(sortKey);
                    contact.setNumber(numbers);
                    contact.setAvatarId(avatarId);
                    contact.setId(contactId);
                    contact.setPhoneType(phoneType);
                    contact.setPhoneTypeStr(typePhone);
                    contact.setEmail(email);
                    contact.setEmailType(emailType);
                    contact.setEmailTypeStr(typeEmail);
                    contact.setCompany(company);
                    contact.setPost(post);
                    contact.setAddress(address);
                    contact.setAddressType(addressType);
                    contact.setAddressTypeStr(typeAddress);
                    contact.setEvent(event);
                    contact.setEventType(eventType);
                    contact.setEventTypeStr(typeEvent);
                    contact.setWebsite(website);
                    mContacts.add(contact);
                } while (cursor.moveToNext());
            }
            CursorLoader loader = new CursorLoader(MyApplication.getContext());
            loader.deliverResult(cursor);

            // 用于进行字母表分组
            AlphabetIndexer indexer = new AlphabetIndexer(cursor, 1, alphabet);
            mAdapter.setIndexer(indexer);
            if (mContacts.size() > 0) {
                setAlphabetListener();
            }
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
                mContactsRecyclerView.scrollToPosition(
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
            mAdapter = new ContactAdapter(getActivity(), mContacts);
            mContactsRecyclerView.setAdapter(mAdapter);
        }
    }

    /**
     * 获取sort key的首个字符，如果是英文字母就直接返回，否则返回#
     * @param sortKeyString
     *          数据库中读取出的sort key
     * @return 英文字母或者#
     */
    private static String getSortKey(String sortKeyString) {
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        }
        return "#";
    }
}