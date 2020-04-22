package com.bignerdranch.android.friends.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.friends.R;

public class NewSmsActivity extends BaseActivity {

    View headerView;
    ImageView mBack, mMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sms);

        initView();
    }

    private void initView() {
        headerView = findViewById(R.id.headerview);
        mBack = (ImageView) findViewById(R.id.header_view_left_image_view);
        mMenu = (ImageView) findViewById(R.id.header_view_right_image_view);
        initHeaderView();
    }

    private void initHeaderView() {
        TextView textView = (TextView) findViewById(R.id.header_view_title_text_view);
        textView.setVisibility(View.GONE);

        EditText editText = (EditText) findViewById(R.id.header_view_title_edit_text);
        editText.setVisibility(View.VISIBLE);
        editText.setTextSize(16);

        mMenu.setImageResource(R.drawable.ic_pick_contact);
        initListener();
    }

    private void initListener() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jump(PickContactActivity.class, false);
            }
        });
    }
}
