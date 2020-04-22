package com.bignerdranch.android.friends.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bignerdranch.android.friends.R;

public class ShowDialog {

    private Context mContext;
    private View mDialogView;
    private AlertDialog mDialog;
    private LayoutInflater inflater;

    public ShowDialog(Context context) {
        mContext = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getDialogView() {
        return mDialogView;
    }

    public AlertDialog getDialog() {
        return mDialog;
    }

    public ShowDialog invoke(int resDialog, int resTitle, String title) {
        mDialogView = inflater.inflate(resDialog, null);

        showDialog();

        TextView titleView = (TextView) mDialogView.findViewById(resTitle);
        titleView.setText(title);
        return this;
    }

    public ShowDialog invoke(int resDialog) {
        mDialogView = inflater.inflate(resDialog, null);

        showDialog();

        return this;
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.Dialog_Full_Screen);
        mDialog = builder.setView(mDialogView).create();
        Window dialogWindow = mDialog.getWindow();

        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(params);

        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.Dialog_Bottom);
        mDialog.show();
    }
}
