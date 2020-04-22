package com.bignerdranch.android.friends.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.adapter.EmoAdapter;
import com.bignerdranch.android.friends.adapter.EmoPagerAdapter;
import com.bignerdranch.android.friends.adapter.SmsAdapter;
import com.bignerdranch.android.friends.app.MyApplication;
import com.bignerdranch.android.friends.bean.ConversationBean;
import com.bignerdranch.android.friends.bean.EmoBean;
import com.bignerdranch.android.friends.bean.SmsBean;
import com.bignerdranch.android.friends.util.SmsUtil;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class SmsActivity extends BaseActivity {

    private static final String EXTRA_CONVERSATION = "Conversation";
    private static final String EXTRA_JUMP_FROM = "jump_from";
    private static final String EXTRA_ADD = "add";
    private static final String EXTRA_NUMBER = "number";

    private static final int REQUEST_PHOTO = 1;

    private RecyclerView mSmsRecyclerView;
    /**
     * 全部短信
     */
    private static final String allSmsUri = "content://sms/";
    /**
     * 适配器
     */
    private SmsAdapter mAdapter;
    /**
     * RecyclerView中显示的与某人的短信
     */
    private List<SmsBean> mSmss = new ArrayList<>();
    /**
     * 文本信息输入框
     */
    private EditText mInputEditText;
    /**
     * 加号按钮
     */
    private Button mAddButton;
    /**
     * 发送按钮
     */
    private Button mSendButton;
    /**
     * 表情容器
     */
    private RelativeLayout mEmoContainer;
    /**
     * 表情容器中的ViewPager
     */
    private ViewPager mEmoViewPager;
    /**
     * 与mEmoViewPager配合管理表情区域
     */
    private EmoPagerAdapter mEmoPagerAdapter;
    /**
     * ViewPager的“指示器”
     */
    private CirclePageIndicator mPageIndicator;
    /**
     * 图片表情容器
     */
    private LinearLayout mEmoPictureContainer;
    /**
     * 大容器
     */
    private LinearLayout mMoreLayoutContainer;

    private String mFilePath;
    private String mCameraPath;
    /**
     * 头
     */
    private View mHeaderView;
    /**
     * 实例
     */
    private ConversationBean mConversation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        mConversation = (ConversationBean)
                getIntent().getSerializableExtra(EXTRA_CONVERSATION);
        int threadId = mConversation.getGroupId();
        String headerTitle;
        if (mConversation.getName() == null) {
            headerTitle = mConversation.getNumber();
        } else {
            headerTitle = mConversation.getName();
            TextView numberText = (TextView) findViewById(R.id.header_view_title_number);
            numberText.setVisibility(View.VISIBLE);
            String number = mConversation.getNumber();
            numberText.setText(number);
        }

        getSmsByThreadId(threadId);
        initView();
        initHeaderView(headerTitle);
        setupAdapter();
        // 用代码让RecyclerView滚动到最后一条短信进行显示
        mSmsRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
    }

    private void getSmsByThreadId(int threadId) {
        Uri uri = Uri.parse(allSmsUri);
        String[] projection = {"thread_id", "body", "date", "type"};
        Cursor cursor = getContentResolver().query(uri, projection, "thread_id=?", new String[]{threadId + ""}, "date");

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String body = cursor.getString(1);
            long date = cursor.getLong(2);
            int type = cursor.getInt(3);

            SmsBean sms = new SmsBean();
            sms.setId(id);
            sms.setBody(body);
            sms.setDate(date);
            sms.setType(type);
            mSmss.add(sms);
        }
    }

    private void initView() {
        mHeaderView = findViewById(R.id.headerview);
        mSmsRecyclerView = (RecyclerView) findViewById(R.id.activity_sms_recycler_view);
        mSmsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMoreLayoutContainer = (LinearLayout) findViewById(R.id.activity_sms_more_layout_container);
        mEmoPictureContainer = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.emo_picture_layout, mMoreLayoutContainer, false);

        // “+”按钮
        mAddButton = (Button) findViewById(R.id.activity_sms_add);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMoreLayoutContainer.getVisibility() == View.VISIBLE) {
                    mMoreLayoutContainer.setVisibility(GONE);
                } else {
                    mMoreLayoutContainer.removeAllViews();
                    mMoreLayoutContainer.setVisibility(View.VISIBLE);
                    mMoreLayoutContainer.addView(mEmoPictureContainer);
                }
            }
        });

        // 表情
        View emoView = mEmoPictureContainer.findViewById(R.id.emo_picture_layout_emo);
        emoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmoContainer = (RelativeLayout) getLayoutInflater()
                        .inflate(R.layout.emo_layout, mMoreLayoutContainer, false);
                mMoreLayoutContainer.removeAllViews();
                mMoreLayoutContainer.setVisibility(View.VISIBLE);
                mMoreLayoutContainer.addView(mEmoContainer);

                mEmoViewPager = (ViewPager) mEmoContainer
                        .findViewById(R.id.emo_layout_view_pager);
                mPageIndicator = (CirclePageIndicator) mEmoContainer
                        .findViewById(R.id.emo_layout_page_indicator);

                List<View> views = new ArrayList<>();
                for (int i = 0; i < 7; i++) {
                    View v = getLayoutInflater()
                            .inflate(R.layout.emo_grid_view_layout, mEmoContainer, false);
                    GridView gridView = (GridView) v
                            .findViewById(R.id.emo_grid_view_layout_grid_view);

                    List<EmoBean> emos = new ArrayList<>();
                    if (i == 0) {
                        // emos中存放SmsUtil.emos中前20个表情
                        emos = SmsUtil.emos.subList(0, 27);
                    } else if (i == 1) {
                        emos = SmsUtil.emos.subList(28, 55);
                    } else if (i == 2) {
                        emos = SmsUtil.emos.subList(56, 83);
                    } else if (i == 3) {
                        emos = SmsUtil.emos.subList(84, 99);
                    }
                    final EmoAdapter adapter = new EmoAdapter(MyApplication.context, emos);
                    gridView.setAdapter(adapter);
                    // 点击表情，将表情放到EditText中显示
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            EmoBean emo = adapter.getItem(position);
                            String emoId = emo.getId();
                            mInputEditText.append(SmsUtil.getSpannableString(emoId));
                        }
                    });
                    views.add(view);
                }
                mEmoPagerAdapter = new EmoPagerAdapter(views);
                mEmoViewPager.setAdapter(mEmoPagerAdapter);
                // mPageIndicator与PagerAdapter进行绑定的语句
                // 一定要写在viewPager与PagerAdapter绑定的语句后面，这样才知道有页面，显示几个点
                mPageIndicator.setViewPager(mEmoViewPager, 0);
                // 指定mPageIndicator用什么颜色来填“圈”
                mPageIndicator.setFillColor(Color.GRAY);
            }
        });

        // 从图库中选图
        View pickView = mEmoPictureContainer.findViewById(R.id.emo_picture_layout_pick);
        pickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, REQUEST_PHOTO);
            }
        });

        // 拍照
        View captureView = mEmoPictureContainer.findViewById(R.id.emo_picture_layout_capture);
        captureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES), System.currentTimeMillis() + ".png");
                mCameraPath = String.valueOf(file.getAbsoluteFile());
                Uri imgUri = Uri.fromFile(file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                startActivityForResult(intent, REQUEST_PHOTO);
            }
        });
    }

    private void initHeaderView(String text) {
        setHeaderTitle(mHeaderView, text);

        ImageView back = (ImageView) findViewById(R.id.header_view_left_image_view);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ImageView menu = (ImageView) findViewById(R.id.header_view_right_image_view);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        SmsActivity.this, R.style.Dialog_Full_Screen);
                View dialogView = LayoutInflater.from(
                        SmsActivity.this).inflate(R.layout.dialog_sms, null);
                final AlertDialog dialog = builder.setView(dialogView).create();
                Window dialogWindow = dialog.getWindow();

                WindowManager.LayoutParams params = dialogWindow.getAttributes();
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                dialogWindow.setAttributes(params);

                dialogWindow.setGravity(Gravity.TOP);
                dialog.show();

                ImageView close = (ImageView) dialogView.findViewById(R.id.dialog_sms_close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                TextView call = (TextView) dialogView.findViewById(R.id.dialog_sms_call);
                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        dial(mConversation.getNumber());
                    }
                });
                TextView insert = (TextView) dialogView.findViewById(R.id.dialog_sms_insert);
                TextView check = (TextView) dialogView.findViewById(R.id.dialog_sms_check);
                LinearLayout insertLayout = (LinearLayout) dialogView.findViewById(R.id.dialog_sms_insert_layout);
                LinearLayout checkLayout = (LinearLayout) dialogView.findViewById(R.id.dialog_sms_check_layout);
                if (mConversation.getName() != null) {
                    insertLayout.setVisibility(View.GONE);
                    checkLayout.setVisibility(View.VISIBLE);
                } else {
                    insertLayout.setVisibility(View.VISIBLE);
                    checkLayout.setVisibility(View.GONE);
                }
                insert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Intent intent = new Intent(SmsActivity.this, AddContactActivity.class);
                        intent.putExtra(EXTRA_JUMP_FROM, EXTRA_ADD);
                        intent.putExtra(EXTRA_NUMBER, mConversation.getNumber());
                        jump(intent, false);
                    }
                });
                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        // TODO 跳转到“联系人详情”界面
                    }
                });
            }
        });
    }

    private void dial(String phoneNumber) {
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(i);
    }

    private void setupAdapter() {
        mAdapter = new SmsAdapter(this, mSmss);
        mSmsRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_PHOTO) {
            if (data != null) { // 从图库中选图
                Uri uri = data.getData();
                String[] projection = new String[] {Media.DATA};
                Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                if (cursor != null) {
                    cursor.moveToNext();
                    mFilePath = cursor.getString(0);
                    cursor.close();
                }
            } else {
                mFilePath = mCameraPath;
            }
        }
    }
}
