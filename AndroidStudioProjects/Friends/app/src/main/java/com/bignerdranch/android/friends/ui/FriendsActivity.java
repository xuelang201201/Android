package com.bignerdranch.android.friends.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.adapter.MyPagerAdapter;
import com.bignerdranch.android.friends.view.MyTabIcon;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FriendsActivity extends BaseActivity {

    /**
     * viewpager
     */
    @Bind(R.id.activity_friends_view_pager)
    ViewPager mViewPager;

    /**
     * 四个按钮
     */
    @Bind(R.id.activity_friends_button_call_logs)
    MyTabIcon mCallLogButton;
    @Bind(R.id.activity_friends_button_contacts)
    MyTabIcon mContactButton;
    @Bind(R.id.activity_friends_button_sms)
    MyTabIcon mSmsButton;
    @Bind(R.id.activity_friends_button_dial)
    MyTabIcon mDialButton;

    /**
     * 自定义View，显示底部按钮
     */
    private MyTabIcon[] mIcons = new MyTabIcon[4];

    /**
     * 最后一次按下返回键的时间
     */
    private long lastClickBackTimeMillis;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        // 绑定黄油刀
        ButterKnife.bind(this);

        initView();
        initViewPager();
    }

    private void initView() {
        // 初始化自定义按钮
        mIcons[0] = mCallLogButton;
        mIcons[1] = mContactButton;
        mIcons[2] = mSmsButton;
        mIcons[3] = mDialButton;
    }

    private void initViewPager() {
        // adapter
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(4); // 首次加载，以后不加载
        mViewPager.setAdapter(adapter);
        for (MyTabIcon icon : mIcons) {
            icon.setMyTabIconAlpha(0);
        }
        mIcons[0].setMyTabIconAlpha(255);
        // 为ViewPager添加滑动监听器
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 在ViewPager滑动过程中，该方法会被不断调用
                // 参数含义：position 当前的页面；positionOffset：滑动百分比 0->1；
                // positionOffsetPixels：根据滑动百分比计算出来的滑动距离
                if (position < mIcons.length - 1) {
                    mIcons[position].setMyTabIconAlpha((int) ((1-positionOffset) * 255));
                    mIcons[position + 1].setMyTabIconAlpha((int) (positionOffset * 255));
                }
            }

            @Override
            public void onPageSelected(int position) {
                // 当页面选定后，应该让当前页面对应的按钮是蓝色，其余为白色
                for (int i = 0; i < mIcons.length; i++) {
                    mIcons[i].setMyTabIconAlpha(0);
                }
                mIcons[position].setMyTabIconAlpha(255);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @OnClick({
                    R.id.activity_friends_button_call_logs,
                    R.id.activity_friends_button_contacts,
                    R.id.activity_friends_button_sms,
                    R.id.activity_friends_button_dial
            })
    public void setCurrentVisibleFragment(View v) {
        switch (v.getId()) {
            case R.id.activity_friends_button_call_logs:
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.activity_friends_button_contacts:
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.activity_friends_button_sms:
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.activity_friends_button_dial:
                mViewPager.setCurrentItem(3, false);
        }
    }

    /**
     * 双击返回键退出
     */
    @Override
    public void onBackPressed() {
        // 判断是不是连续按下
        long currentClickBackTimeMillis = System.currentTimeMillis();
        if (currentClickBackTimeMillis - lastClickBackTimeMillis > 2000) { // 第一次按键时间肯定大于1秒
            Toast.makeText(this, getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT).show();
            lastClickBackTimeMillis = currentClickBackTimeMillis;
            return;
        }
        super.onBackPressed();
    }
}
