package com.bignerdranch.android.friends.ui;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.fragment.AddContactFragment;
import com.bignerdranch.android.friends.util.ShowDialog;

public class AddContactActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return AddContactFragment.newInstance();
    }

    @Override
    public void onBackPressed() {
        showConfirmDialog();
    }

    private void showConfirmDialog() {
        ShowDialog showDialog = new ShowDialog(this).invoke(R.layout.dialog_confirm_content);
        
        View dialogView = showDialog.getDialogView();
        final AlertDialog dialog = showDialog.getDialog();

        TextView mConfirm = (TextView) dialogView
                .findViewById(R.id.dialog_confirm_ok);
        TextView mCancel = (TextView) dialogView
                .findViewById(R.id.dialog_confirm_cancel);

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
