package com.bignerdranch.android.friends.ui;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.bean.ContactBean;
import com.bignerdranch.android.friends.util.BitmapUtils;
import com.bignerdranch.android.friends.util.ShowDialog;
import com.bignerdranch.android.friends.view.CircleImageView;

import java.io.File;
import java.util.List;

public class ContactDetailActivity extends BaseActivity {

    private static final int REQUEST_INTENT_PICK = 1;
    private static final int REQUEST_INTENT_CAPTURE = 2;
    private static final String EXTRA_CONTACT = "contact";
    private static final String EXTRA_JUMP_FROM = "jump_from";
    private static final String EXTRA_EDIT = "edit";

    private View headerView, mCall, mEmailContainer, mSendEmail, mEventContainer,
            mAddressContainer, mCopyEvent, mCopyAddress, mWebsiteContainer;
    private ImageView mBack, mMenu, mMessage, mEdit;
    private TextView mName, mNumber, mPhoneInfo, mEmail, mWebsite,
            mEmailType, mComPost, mEvent, mEventType, mAddress, mAddressType;
    private CircleImageView mAvatar;

    private ContactBean contact;
    private String website;

    private String mPhotoPath; // 拍摄头像照片的本地地址
    private String mFilePath; // 用户头像图像的本地地址

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        initView();
        initData();
    }

    private void initView() {
        headerView = findViewById(R.id.headerview);
        mBack = (ImageView) findViewById(R.id.header_view_left_image_view);
        mMenu = (ImageView) findViewById(R.id.header_view_right_image_view);
        mEdit = (ImageView) findViewById(R.id.activity_contact_detail_edit);

        mName = (TextView) findViewById(R.id.activity_contact_detail_name);
        mNumber = (TextView) findViewById(R.id.activity_contact_detail_number);
        mAvatar = (CircleImageView) findViewById(R.id.activity_contact_detail_avatar);
        mPhoneInfo = (TextView) findViewById(R.id.activity_contact_detail_phone_info);
        mEmail = (TextView) findViewById(R.id.activity_contact_detail_email);
        mEmailType = (TextView) findViewById(R.id.activity_contact_detail_email_type);
        mComPost = (TextView) findViewById(R.id.activity_contact_detail_company_post);
        mEvent = (TextView) findViewById(R.id.activity_contact_detail_event);
        mEventType = (TextView) findViewById(R.id.activity_contact_detail_event_type);
        mAddress = (TextView) findViewById(R.id.activity_contact_detail_address);
        mAddressType = (TextView) findViewById(R.id.activity_contact_detail_address_type);
        mWebsite = (TextView) findViewById(R.id.activity_contact_detail_website);

        mEmailContainer = findViewById(R.id.activity_contact_detail_email_container);
        mAddressContainer = findViewById(R.id.activity_contact_detail_address_container);
        mEventContainer = findViewById(R.id.activity_contact_detail_event_container);
        mWebsiteContainer = findViewById(R.id.activity_contact_detail_website_container);

        mCall = findViewById(R.id.call);
        mMessage = (ImageView) findViewById(R.id.activity_contact_detail_to_message);
        mSendEmail = findViewById(R.id.email);
        mCopyAddress = findViewById(R.id.address);
        mCopyEvent = findViewById(R.id.event);

        initHeaderView();
        initListener();
    }

    private void initData() {
        contact = (ContactBean) getIntent().getSerializableExtra(EXTRA_CONTACT);
        String name = contact.getName();
        List<String> numbers = contact.getNumber();
        int avatarId = contact.getAvatarId();
        String typePhone = contact.getPhoneTypeStr();
        String email = contact.getEmail();
        String typeEmail = contact.getEmailTypeStr();
        String company = contact.getCompany();
        String post = contact.getPost();
        String event = contact.getEvent();
        String typeEvent = contact.getEventTypeStr();
        String address = contact.getAddress();
        String typeAddress = contact.getAddressTypeStr();
        website = contact.getWebsite();

        // 姓名
        mName.setText(name);
        // 电话
        mNumber.setText(numbers.get(0));
        // 电话类型
        mPhoneInfo.setText(typePhone);
        // 头像
        Bitmap bitmap = BitmapUtils.loadBitmap(this, avatarId);
        if (bitmap != null) {
            mAvatar.setImageBitmap(bitmap);
        }
        // 邮箱
        if (email != null) {
            mEmailContainer.setVisibility(View.VISIBLE);
            mEmail.setText(email);
        }
        // 邮箱类型
        mEmailType.setText(typeEmail);
        // 公司
        if (mComPost.getText() != null) {
            mComPost.setVisibility(View.VISIBLE);
            if (company != null) {
                if (post == null) {
                    mComPost.setText(company);
                } else {
                    mComPost.setText(company + "，" + post);
                }
            } else {
                mComPost.setVisibility(View.GONE);
            }
        }
        // 地址
        if (address != null) {
            mAddressContainer.setVisibility(View.VISIBLE);
            mAddress.setText(address);
        }
        // 地址类型
        mAddressType.setText(typeAddress);
        // 事件
        if (event != null) {
            mEventContainer.setVisibility(View.VISIBLE);
            mEvent.setText(event);
        }
        // 事件类型
        mEventType.setText(typeEvent);
        // 网站
        if (website != null) {
            mWebsiteContainer.setVisibility(View.VISIBLE);
            mWebsite.setText(website);
        }
    }

    private void initListener() {
        mAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog showDialog = new ShowDialog(ContactDetailActivity.this)
                        .invoke(R.layout.dialog_intent_chooser_content);

                View dialogView = showDialog.getDialogView();
                final AlertDialog dialog = showDialog.getDialog();

                TextView mCapture = (TextView) dialogView
                        .findViewById(R.id.dialog_intent_chooser_capture);
                TextView mPick = (TextView) dialogView
                        .findViewById(R.id.dialog_intent_chooser_pick);

                mCapture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        captureFromCamera(); // 拍照
                    }
                });

                mPick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        pickFromGallery(); // 从相册选择

                    }
                });
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactDetailActivity.this, AddContactActivity.class);
                intent.putExtra(EXTRA_JUMP_FROM, EXTRA_EDIT);
                intent.putExtra(EXTRA_CONTACT, contact);
                jump(intent, false);
            }
        });

        mMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        ContactDetailActivity.this, R.style.Dialog_Full_Screen);
                View dialogView = LayoutInflater.from(
                        ContactDetailActivity.this).inflate(R.layout.dialog_menu, null);
                final AlertDialog dialog = builder.setView(dialogView).create();
                Window dialogWindow = dialog.getWindow();

                WindowManager.LayoutParams params = dialogWindow.getAttributes();
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                dialogWindow.setAttributes(params);

                dialogWindow.setGravity(Gravity.TOP);
                dialog.show();

                ImageView close = (ImageView) dialogView.findViewById(R.id.dialog_menu_close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                TextView delete = (TextView) dialogView.findViewById(R.id.dialog_menu_delete_contact);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        deleteContact();
                    }
                });

                TextView addToBlacklist = (TextView) dialogView.findViewById(R.id.dialog_menu_add_to_blacklist);
                addToBlacklist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO 加入黑名单，弹窗确认
                        toast(getString(R.string.add_to_blacklist));
                        dialog.dismiss();
                    }
                });
            }
        });

        mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call(mNumber.getText());
            }
        });

        mCall.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                ShowDialog showDialog = new ShowDialog(ContactDetailActivity.this).invoke(
                        R.layout.dialog_copy, R.id.dialog_copy_title, mNumber.getText().toString());

                final AlertDialog dialog = showDialog.getDialog();

                TextView copy = (TextView) dialog.findViewById(R.id.dialog_copy_copy);
                copy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        copyAndToast(dialog, mNumber.getText(), getString(R.string.number));
                    }
                });

                View call = dialog.findViewById(R.id.dialog_copy_call);
                call.setVisibility(View.VISIBLE);
                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        call(mNumber.getText());
                        dialog.dismiss();
                    }
                });
                return true;
            }
        });

        mMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO 找到当前联系人短信界面，如果没有短信则新建短信
            }
        });

        mSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                String[] to = {mEmail.getText().toString()};
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                jump(intent, false);
            }
        });

        mSendEmail.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                String email = mEmail.getText().toString();
                return showDialogToCopyText(ContactDetailActivity.this, email, getString(R.string.email));
            }
        });

        mCopyAddress.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String address = mAddress.getText().toString();
                return showDialogToCopyText(ContactDetailActivity.this, address, getString(R.string.address));
            }
        });

        mCopyEvent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String event = mEvent.getText().toString();
                return showDialogToCopyText(ContactDetailActivity.this, event, getString(R.string.event));
            }
        });

        mWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visitWebsite(website);
            }
        });
        mWebsite.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                ShowDialog showDialog = new ShowDialog(ContactDetailActivity.this)
                        .invoke(R.layout.dialog_copy, R.id.dialog_copy_title, website);
                final AlertDialog dialog = showDialog.getDialog();
                View dialogView = showDialog.getDialogView();

                TextView copy = (TextView) dialogView.findViewById(R.id.dialog_copy_copy);
                copy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        copyAndToast(dialog, website, getString(R.string.website));
                    }
                });
                return true;
            }
        });
    }

    /**
     * 删除联系人
     */
    private void deleteContact() {
        ShowDialog showDialog = new ShowDialog(this).invoke(R.layout.dialog_delete_contact);
        final AlertDialog dialog = showDialog.getDialog();
        View dialogView = showDialog.getDialogView();

        TextView confirm = (TextView) dialogView.findViewById(R.id.dialog_delete_contact_ok);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                // 真正删除
                String name = mName.getText().toString();
                Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
                ContentResolver resolver = getContentResolver();
                Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Data._ID},
                        "display_name=?", new String[]{name}, null);
                if (cursor.moveToFirst()) {
                    int id = cursor.getInt(0);
                    resolver.delete(uri, "display_name=?", new String[]{name});
                    uri = Uri.parse("content://com.android.contacts/data");
                    resolver.delete(uri, "raw_contact_id=?", new String[]{id+""});
                }
                finish();
                notifyAll();
            }
        });
        TextView cancel = (TextView) dialogView.findViewById(R.id.dialog_delete_contact_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 从图库中获取图片
     */
    private void pickFromGallery() {
        // 创建打开系统图库的Intent
        Intent pickIntent = new Intent(Intent.ACTION_PICK);
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        // 发送intent
        startActivityForResult(pickIntent, REQUEST_INTENT_PICK);
    }

    /**
     * 相机拍照
     */
    private void captureFromCamera() {
        // 创建打开系统相机的Intent
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 指定拍摄照片保存的位置
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), System.currentTimeMillis() + ".png");
        mPhotoPath = file.getAbsolutePath();
        Uri imgUri = Uri.fromFile(file);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri); // 必须用Uri格式描述保存路径
        // 发送intent
        startActivityForResult(captureIntent, REQUEST_INTENT_CAPTURE);
    }

    /**
     * 访问网站
     * @param uri 要访问的网址
     */
    private void visitWebsite(String uri) {
        Intent i = WebsiteActivity.newIntent(ContactDetailActivity.this, Uri.parse(uri));
        startActivity(i);
    }

    /**
     * 打电话
     * @param number 电话号码
     */
    private void call(CharSequence number) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
        jump(intent, false);
    }

    private void initHeaderView() {
        TextView titleView = (TextView) headerView.findViewById(R.id.header_view_title_text_view);
        titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        setHeaderTitle(headerView, getString(R.string.contact_detail));
    }
}