package com.bignerdranch.android.friends.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.bean.ContactBean;
import com.bignerdranch.android.friends.constant.Constant.Position;
import com.bignerdranch.android.friends.ui.ContactDetailActivity;
import com.bignerdranch.android.friends.util.BitmapUtils;
import com.bignerdranch.android.friends.util.ContactsManager;
import com.bignerdranch.android.friends.util.ShowDialog;
import com.bignerdranch.android.friends.view.CircleImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddContactFragment extends BaseFragment {
    
    private static final String TAG = "AddContactFragment";

    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_INTENT_PICK = 1;
    private static final int REQUEST_INTENT_CAPTURE = 2;

    private static final String EXTRA_CONTACT = "contact";
    private static final String EXTRA_JUMP_FROM = "jump_from";
    private static final String EXTRA_ADD = "add";
    private static final String EXTRA_EDIT = "edit";
    private static final String EXTRA_NUMBER = "number";

    private String from; // add, edit表示从不同界面跳转过来
    private View headerView, mPhoneTypeView, mEmailTypeView,
            mAddressTypeView, mEventTypeView, mWebsiteTypeView,
            mPhoneContainer, mEmailContainer, mAddressContainer,
            mEventContainer, mWebsiteContainer;
    /**
     * 头像
     */
    private CircleImageView mAvatar;
    /**
     * 联系人：姓名，公司，职位，电话，邮件
     */
    private EditText mName, mCompany, mPost, mPhone,
            mEmail, mAddress, mWebsite;

    private String mPhotoPath; // 拍摄头像照片的本地地址
    public String mFilePath; // 用户头像图像的本地地址
    private TextView typePhone, typeEmail, typeAddress, typeEvent, typeWebsite, mEvent;

    private Button mAddMore, mDeleteContact;

    private ContactBean mContact;
    private ContactsManager mContactsManager;

    public static AddContactFragment newInstance() {
        return new AddContactFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContactsManager = new ContactsManager();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_contact, container, false);
        initView(v);
        initHeaderView();

        mAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShowDialog showDialog = new ShowDialog(getActivity())
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

        mPhoneTypeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog showDialog = new ShowDialog(getActivity())
                        .invoke(R.layout.dialog_number_type);

                View dialogView = showDialog.getDialogView();
                final AlertDialog dialog = showDialog.getDialog();

                TextView phone = (TextView) dialogView
                        .findViewById(R.id.dialog_number_type_mobile);
                TextView home = (TextView) dialogView
                        .findViewById(R.id.dialog_number_type_home);
                TextView faxHome = (TextView) dialogView
                        .findViewById(R.id.dialog_number_type_fax_home);
                TextView work = (TextView) dialogView
                        .findViewById(R.id.dialog_number_type_work);
                TextView workMobile = (TextView) dialogView
                        .findViewById(R.id.dialog_number_type_work_mobile);
                TextView faxWork = (TextView) dialogView
                        .findViewById(R.id.dialog_number_type_fax_work);

                phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        typePhone.setText(R.string.phone_type_mobile);
                    }
                });

                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        typePhone.setText(R.string.phone_type_home);
                    }
                });

                faxHome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        typePhone.setText(R.string.phone_type_fax_home);
                    }
                });

                work.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        typePhone.setText(R.string.phone_type_work);
                    }
                });

                workMobile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        typePhone.setText(R.string.phone_type_work_mobile);
                    }
                });

                faxWork.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        typePhone.setText(R.string.phone_type_fax_work);
                    }
                });
            }
        });

        mEmailTypeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog showDialog = new ShowDialog(getActivity())
                        .invoke(R.layout.dialog_email_type);

                View dialogView = showDialog.getDialogView();
                final AlertDialog dialog = showDialog.getDialog();

                TextView work = (TextView) dialogView
                        .findViewById(R.id.dialog_email_type_work);
                TextView own = (TextView) dialogView
                        .findViewById(R.id.dialog_email_type_own);

                work.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        typeEmail.setText(R.string.email_type_work);
                    }
                });

                own.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        typeEmail.setText(R.string.email_type_own);
                    }
                });
            }
        });

        mAddressTypeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog showDialog = new ShowDialog(getActivity())
                        .invoke(R.layout.dialog_address_type);

                View dialogView = showDialog.getDialogView();
                final AlertDialog dialog = showDialog.getDialog();

                TextView home = (TextView) dialogView.findViewById(R.id.dialog_address_type_home);
                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        typeAddress.setText(R.string.address_home);
                    }
                });
                TextView work = (TextView) dialogView.findViewById(R.id.dialog_address_type_work);
                work.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        typeAddress.setText(R.string.address_work);
                    }
                });
                TextView other = (TextView) dialogView.findViewById(R.id.dialog_address_type_other);
                other.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        typeAddress.setText(R.string.other);
                    }
                });
            }
        });

        mEventTypeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog showDialog = new ShowDialog(getActivity())
                        .invoke(R.layout.dialog_event_type);

                View dialogView = showDialog.getDialogView();
                final AlertDialog dialog = showDialog.getDialog();

                TextView birthday = (TextView) dialogView.findViewById(R.id.dialog_event_type_birthday);
                birthday.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        typeEvent.setText(R.string.birthday);
                    }
                });
                TextView anniversary = (TextView) dialogView.findViewById(R.id.dialog_event_type_anniversary);
                anniversary.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        typeEvent.setText(R.string.anniversary);
                    }
                });
                TextView other = (TextView) dialogView.findViewById(R.id.dialog_event_type_other);
                other.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        typeEvent.setText(R.string.other);
                    }
                });
            }
        });

        mWebsiteTypeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog showDialog = new ShowDialog(getActivity())
                        .invoke(R.layout.dialog_website_type);

                View dialogView = showDialog.getDialogView();
                final AlertDialog dialog = showDialog.getDialog();

                TextView website = (TextView) dialogView.findViewById(R.id.dialog_website_type_website);
                website.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        typeWebsite.setText(R.string.website);
                    }
                });
            }
        });

        mDeleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteContact();
            }
        });
        return v;
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

    private void initView(View v) {
        headerView = v.findViewById(R.id.headerview);
        mAvatar = (CircleImageView) v
                .findViewById(R.id.fragment_add_contact_avatar);
        mName = (EditText) v
                .findViewById(R.id.fragment_add_contact_et_name);
        mCompany = (EditText) v
                .findViewById(R.id.fragment_add_contact_et_company);
        mPost = (EditText) v
                .findViewById(R.id.fragment_add_contact_et_post);
        mPhone = (EditText) v
                .findViewById(R.id.fragment_add_contact_et_phone);
        mEmail = (EditText) v
                .findViewById(R.id.fragment_add_contact_et_email);
        mAddress = (EditText) v
                .findViewById(R.id.fragment_add_contact_et_address);
        mEvent = (TextView) v
                .findViewById(R.id.fragment_add_contact_tv_event);
        mWebsite = (EditText) v
                .findViewById(R.id.fragment_add_contact_et_website);
        typePhone = (TextView) v.findViewById(R.id.phone_type);
        typeEmail = (TextView) v.findViewById(R.id.email_type);
        typeAddress = (TextView) v.findViewById(R.id.address_type);
        typeEvent = (TextView) v.findViewById(R.id.event_type);
        typeWebsite = (TextView) v.findViewById(R.id.website_type);

        mAddMore = (Button) v.findViewById(R.id.fragment_add_contact_add_more);
        mDeleteContact = (Button) v.findViewById(R.id.fragment_edit_contact_delete);

        mPhoneTypeView = v.findViewById(R.id.fragment_add_contact_phone_type);
        mEmailTypeView = v.findViewById(R.id.fragment_add_contact_email_type);
        mAddressTypeView = v.findViewById(R.id.fragment_add_contact_address_type);
        mEventTypeView = v.findViewById(R.id.fragment_add_contact_event_type);
        mWebsiteTypeView = v.findViewById(R.id.fragment_add_contact_website_type);

        mPhoneContainer = v.findViewById(R.id.fragment_add_contact_phone_container);
        mEmailContainer = v.findViewById(R.id.fragment_add_contact_email_container);
        mAddressContainer = v.findViewById(R.id.fragment_add_contact_address_container);
        mEventContainer = v.findViewById(R.id.fragment_add_contact_event_container);
        mWebsiteContainer = v.findViewById(R.id.fragment_add_contact_website_container);

        from = getActivity().getIntent().getStringExtra(EXTRA_JUMP_FROM);
        mContact = (ContactBean) getActivity()
                .getIntent().getSerializableExtra(EXTRA_CONTACT);
        if (EXTRA_EDIT.equals(from)) {
            int avatarId = mContact.getAvatarId();
            Bitmap bitmap = BitmapUtils.loadBitmap(getActivity(), avatarId);
            if (bitmap == null) {
                mAvatar.setImageResource(R.drawable.ic_account);
            } else {
                mAvatar.setImageBitmap(bitmap);
            }
            String name = mContact.getName();
            mName.setText(name);
            String number = mContact.getNumber().get(0);
            mPhone.setText(number);
            String numberType = mContact.getPhoneTypeStr();
            typePhone.setText(numberType);
            String company = mContact.getCompany();
            mCompany.setText(company);
            String post = mContact.getPost();
            mPost.setText(post);
            String email = mContact.getEmail();
            mEmail.setText(email);
            if (email != null) {
                mEmailContainer.setVisibility(View.VISIBLE);
                String emailType = mContact.getEmailTypeStr();
                typeEmail.setText(emailType);
            }
            String address = mContact.getAddress();
            mAddress.setText(address);
            if (address != null) {
                mAddressContainer.setVisibility(View.VISIBLE);
                String addressType = mContact.getAddressTypeStr();
                typeAddress.setText(addressType);
            }
            String event = mContact.getEvent();
            mEvent.setText(event);
            if (event != null) {
                mEventContainer.setVisibility(View.VISIBLE);
                String eventType = mContact.getEventTypeStr();
                typeEvent.setText(eventType);
            }
            String website = mContact.getWebsite();
            if (website != null) {
                mWebsiteContainer.setVisibility(View.VISIBLE);
                mWebsite.setText(website);
            }

            // 添加更多项
            addMore();
            mDeleteContact.setVisibility(View.VISIBLE);
        } else {
            addMore();
            String number = getActivity().getIntent().getStringExtra(EXTRA_NUMBER);
            mPhone.setText(number);
        }
    }

    /**
     * 添加更多项
     */
    private void addMore() {
        mAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog showDialog = new ShowDialog(getActivity()).invoke(R.layout.dialog_add_more);
                View dialogView = showDialog.getDialogView();
                final AlertDialog dialog = showDialog.getDialog();

                final TextView address = (TextView) dialogView.findViewById(R.id.dialog_add_more_address);
                address.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        mAddressContainer.setVisibility(View.VISIBLE);
                        address.setVisibility(View.GONE);
                    }
                });

                final TextView event = (TextView) dialogView.findViewById(R.id.dialog_add_more_event);
                event.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        mEventContainer.setVisibility(View.VISIBLE);
                        event.setVisibility(View.GONE);
                    }
                });

                final TextView website = (TextView) dialogView.findViewById(R.id.dialog_add_more_website);
                website.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        mWebsiteContainer.setVisibility(View.VISIBLE);
                        website.setVisibility(View.GONE);
                    }
                });
            }
        });
        setAddMoreButtonVisibility();
    }

    private void setAddMoreButtonVisibility() {
        if (mAddressContainer.getVisibility() == View.VISIBLE &&
                mEventContainer.getVisibility() == View.VISIBLE &&
                mWebsiteContainer.getVisibility() == View.VISIBLE) {
            mAddMore.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化标题布局
     */
    private void initHeaderView() {

        String text;
        if (EXTRA_ADD.equals(from)) {
            text = getString(R.string.insert_contact);
        } else {
            text = getString(R.string.edit_contact);
        }

        // 设置标题
        setHeaderTitle(headerView, text);
        // 设置右侧图标
        setHeaderImage(headerView, R.drawable.ic_done, Position.RIGHT, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(mName.getText().toString()) && "".equals(mCompany.getText().toString())
                        && "".equals(mPost.getText().toString()) && "".equals(mPhone.getText().toString())
                        && "".equals(mEmail.getText().toString())) {
                    toast(getString(R.string.can_not_save_empty_contact));
                } else {
                    if (EXTRA_ADD.equals(from)) {
                        // 新建联系人
                        insertContact();
                        jump(ContactDetailActivity.class, true);
                    } else {
                        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
                        ContentValues values = new ContentValues();
                        long contactId = ContentUris.parseId(getActivity().getContentResolver().insert(uri, values));

                        // 更新联系人
                        updateContact(contactId, "13890934758");
                        getActivity().finish();

                    }
                }
            }
        });
        // 设置左侧图标
        setHeaderImage(headerView, R.drawable.ic_cancel, Position.LEFT, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(mName.getText().toString())
                        && "".equals(mCompany.getText().toString())
                        && "".equals(mPost.getText().toString())
                        && "".equals(mPhone.getText().toString())
                        && "".equals(mEmail.getText().toString())) {
                    getActivity().finish();
                } else {
                    ShowDialog showDialog = new ShowDialog(getActivity())
                            .invoke(R.layout.dialog_confirm_content);
                    View dialogView = showDialog.getDialogView();
                    final AlertDialog dialog = showDialog.getDialog();

                    TextView cancel = (TextView) dialogView
                            .findViewById(R.id.dialog_confirm_cancel);
                    TextView confirm = (TextView) dialogView
                            .findViewById(R.id.dialog_confirm_ok);

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            getActivity().finish();
                        }
                    });
                }
            }
        });
    }

    /**
     * 添加联系人
     */
    private void insertContact() {

        ContactBean contact = new ContactBean();
        contact.setName(mName.getText().toString());
        String number = mPhone.getText().toString();
        List<String> numbers = new ArrayList<>();
        numbers.add(number);
        contact.setNumber(numbers);
        contact.setPhoneTypeStr(typePhone.getText().toString());
        contact.setEmail(mEmail.getText().toString());
        contact.setEmailTypeStr(typeEmail.getText().toString());
        contact.setCompany(mCompany.getText().toString());
        contact.setPost(mPost.getText().toString());
        contact.setAddress(mAddress.getText().toString());
        contact.setAddressTypeStr(typeAddress.getText().toString());
        contact.setEvent(mEvent.getText().toString());
        contact.setEventTypeStr(typeEvent.getText().toString());
        contact.setWebsite(mWebsite.getText().toString());

        mContactsManager.insertContact(contact);

        setAddMoreButtonVisibility();
    }

    /**
     * 删除联系人
     */
    private void deleteContact() {
        mContactsManager.deleteContact(mContact);
    }

    /**
     * 更新联系人
     */
    private void updateContact(long rawContactId, String newNumber) {
        mContactsManager.updateContact(rawContactId, newNumber);
    }

    /**
     * 查找联系人
     */
    private void queryContact() {
        // TODO 查找联系人
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_INTENT_PICK) {
                // 用户是从图库选的图
                // 用户选择了哪张图
                // 该uri并不是图片在SD卡上的绝对路径
                // 而是该图片在图库中的索引（content://xxx/9）
                // 利用ContentResolver去图库中查询，uri对应图片在SD卡上的绝对路径
                Uri uri = data.getData();
                String[] projection = new String[] { MediaStore.Images.Media.DATA };
                Cursor c = getActivity().getContentResolver().query(uri, projection, null, null, null);
                c.moveToNext();
                mFilePath = c.getString(0);
                c.close();
            } else if (requestCode == REQUEST_INTENT_CAPTURE) {
                // 用户是从相机拍摄
                mFilePath = mPhotoPath;
            }
        }
    }
}
