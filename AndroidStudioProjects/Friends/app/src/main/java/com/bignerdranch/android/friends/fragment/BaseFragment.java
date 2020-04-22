package com.bignerdranch.android.friends.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.constant.Constant.Position;

public class BaseFragment extends Fragment {

    public void toast(String text) {
        // 方法不能在Fragment的onCreate里面调用
        // 因为getActivity有可能在onCreate方法中会得到null
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    public void log(String log) {
        Log.d("TAG", this.getClass().getName() + "输出：" + log);
    }

    public void toastAndLog(String text, String log) {
        toast(text);
        log(log);
    }

    // 界面的跳转
    public void jump(Class<?> clazz, boolean isFinish) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
        if (isFinish) {
            getActivity().finish();
        }
    }

    public void jump(Intent intent, boolean isFinish) {
        startActivity(intent);
        if (isFinish) {
            getActivity().finish();
        }
    }

    // 设置头部布局
    // 设置头部标题，标题居中
    public void setHeaderTitle(View headerView, String text) {
        setHeaderTitle(headerView, text, Position.CENTER);
    }

    // 设置头部标题的重载方法，可以明确指定标题的位置
    public void setHeaderTitle(View headerView, String text, Position pos) {
        TextView tv = (TextView) headerView.findViewById(R.id.header_view_title_text_view);

        switch (pos) {
            case LEFT:
                tv.setGravity(Gravity.LEFT);
                break;

            case RIGHT:
                tv.setGravity(Gravity.RIGHT);
                break;

            case CENTER:
                tv.setGravity(Gravity.CENTER);
                break;

        }
        if (text == null) {
            tv.setText("");
        } else {
            tv.setText(text);
        }
    }

    /**
     * 用来设置头部左侧或右侧的图像
     * @param headerView 头部的headerView
     * @param resId 显示图像的资源id
     * @param pos pos如果是LEFT就设置左侧的图像，如果是CENTER或RIGHT均为右侧的图像
     */
    public void setHeaderImage(View headerView, int resId, Position pos, View.OnClickListener listener) {
        ImageView iv = null;
        switch (pos) {
            case LEFT:
                iv = (ImageView) headerView.findViewById(R.id.header_view_left_image_view);
                break;

            case CENTER:
            case RIGHT:
                iv = (ImageView) headerView.findViewById(R.id.header_view_right_image_view);
                break;
        }

        iv.setImageResource(resId);
        iv.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        if (listener != null) {
            iv.setOnClickListener(listener);
        }
    }

    /**
     * 判空
     * 返回true，就说明有EditText未输入内容
     * 返回false，就说明EditText都输入了
     */
    public boolean isEmpty(EditText... ets) {

        for (EditText editText : ets) {
            if (TextUtils.isEmpty(editText.getText().toString())) {
                // 如果直接为setError方法提供String类型的参数
                // 有可能出现提示文字使用的颜色，与错误提示框的背景色相同
                // 造成文字不可见
                // 但是，setError接收的参数类型是CharSequence类型
                // 所以，更换一下参数的类型，不是用标准的String，而是使用安卓提供的
                // 可扩展String，为可扩展String指定一个不同于提示框背景色的字体颜色
                //<font color="颜色值">
                editText.setError(Html.fromHtml("<font color=#ff0000>请输入完整</font>"));
                return true;
            }
        }

        return false;
    }

    /**
     * 格式化电话号码的方法
     * 去+86，去空格，去-
     *
     * @param number 传入用户输入的不规则的电话号码
     * @return 返回格式化后的电话号
     */
    public static String formatNumber(String number) {
        number = number.replace("+86", "");
        number = number.replace(" ", "");
        number = number.replace("-", "");
        return number;
    }
}
