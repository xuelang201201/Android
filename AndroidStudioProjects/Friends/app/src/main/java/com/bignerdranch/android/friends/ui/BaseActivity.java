package com.bignerdranch.android.friends.ui;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.constant.Constant.Position;
import com.bignerdranch.android.friends.util.ShowDialog;

public class BaseActivity extends AppCompatActivity {

    Toast toast;
    private TextView mTitle, mNumber;
    private ImageView mImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // AppCompatActivity去掉标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // Activity去掉标题栏
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
    }

    // Toast和Log的输出
    public void toast(String text) {
        toast.setText(text);
        toast.show();
    }

    public void log(String log) {
        Log.d("TAG", this.getClass().getName() + "输入：" + log);
    }

    public void toastAndLog(String text, String log) {
        toast(text);
        log(log);
    }

    // 界面的跳转
    public void jump(Class<?> clazz, boolean isFinish) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        if (isFinish) {
            this.finish();
        }
    }

    public void jump(Intent intent, boolean isFinish) {
        startActivity(intent);
        if (isFinish) {
            this.finish();
        }
    }

    public void setHeaderTitle(View headerView, String text) {
        setHeaderTitle(headerView, text, Position.LEFT);
    }

    // 设置头部标题的重载方法，可以明确指定标题的位置
    public void setHeaderTitle(View headerView, String text, Position pos) {
        mTitle = (TextView) headerView.findViewById(R.id.header_view_title_text_view);

        switch (pos) {
            case LEFT:
                mTitle.setGravity(Gravity.LEFT);
                break;

            case RIGHT:
                mTitle.setGravity(Gravity.RIGHT);
                break;

            case CENTER:
                mTitle.setGravity(Gravity.CENTER);
                break;

        }
        if (text == null) {
            mTitle.setText("");
        } else {
            mTitle.setText(text);
        }
    }

    /**
     * 用来设置头部左侧或右侧的图像
     *
     * @param headerView 头部的headerView
     * @param resId      显示图像的资源id
     * @param pos        pos如果是LEFT就设置左侧的图像，如果是CENTER或RIGHT均为右侧的图像
     */
    public void setHeaderImage(View headerView, int resId, Position pos, View.OnClickListener listener) {
        switch (pos) {
            case LEFT:
                mImage = (ImageView) headerView.findViewById(R.id.header_view_left_image_view);
                break;

            case CENTER:
            case RIGHT:
                mImage = (ImageView) headerView.findViewById(R.id.header_view_right_image_view);
                break;
        }

        mImage.setImageResource(resId);
        mImage.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        if (listener != null) {
            mImage.setOnClickListener(listener);
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
                // 所以，更换一下参数的类型，不是用标准的String，而是使用
                // 可扩展String，为可扩展String指定一个不同于提示框背景色的字体颜色
                //<font color="颜色值">
                editText.setError(Html.fromHtml("<font color=#ff0000>请输入完整</font>"));
                return true;
            }
        }

        return false;
    }


    /**
     * 显示弹窗，复制文本操作
     * @param text 要复制的文本
     * @return
     */
    public boolean showDialogToCopyText(Context context, final String text, final String toast) {
        ShowDialog showDialog = new ShowDialog(context).invoke(
                R.layout.dialog_copy, R.id.dialog_copy_title, text);

        View dialogView = showDialog.getDialogView();
        final AlertDialog dialog = showDialog.getDialog();

        TextView copy = (TextView) dialogView.findViewById(R.id.dialog_copy_copy);

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyAndToast(dialog, text, toast);
            }
        });
        return true;
    }

    public boolean showDialogToCopyText(Context context, final String text) {
        ShowDialog showDialog = new ShowDialog(context).invoke(
                R.layout.dialog_copy, R.id.dialog_copy_title, text);

        View dialogView = showDialog.getDialogView();
        final AlertDialog dialog = showDialog.getDialog();

        TextView copy = (TextView) dialogView.findViewById(R.id.dialog_copy_copy);

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyAndToast(dialog, text);
            }
        });
        return true;
    }

    /**
     * 复制并弹窗提示
     */
    public void copyAndToast(AlertDialog dialog, CharSequence text, String str) {
        copy(text);
        dialog.dismiss();
        toast(str + "已复制到剪贴板");
    }

    public void copyAndToast(AlertDialog dialog, CharSequence text) {
        copy(text);
        dialog.dismiss();
        toast("已复制到剪贴板");
    }

    /**
     * 真正的复制
     * @param text 要复制的文本
     */
    public void copy(CharSequence text) {
        ClipboardManager manager =
                (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Label", text);
        manager.setPrimaryClip(clipData);
    }

    /**
     * 格式化电话号码的方法
     * 去+86，去空格，去-
     *
     * @param number 传入用户输入的不规则的电话号码
     * @return 返回格式化后的电话号
     */
    private static String formatNumber(String number) {
        number = number.replace("+86", "");
        number = number.replace(" ", "");
        number = number.replace("-", "");
        return number;
    }
}