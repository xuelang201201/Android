package com.charles.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private SharedPreferences mSharedPreferences;

    private SharedPreferences.Editor mEditor;

    private EditText mAccountEdit, mPasswordEdit;
    private Button mLogin;
    private CheckBox mCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        mAccountEdit = (EditText) findViewById(R.id.account);
        mPasswordEdit = (EditText) findViewById(R.id.password);
        mCheckBox = (CheckBox) findViewById(R.id.remember_password);
        mLogin = (Button) findViewById(R.id.login);
        boolean isRemember = mSharedPreferences.getBoolean("remember_password", false);

        if (isRemember) {
            // 将账号和密码都设置到文本框中
            String account = mSharedPreferences.getString("account", "");
            String password = mSharedPreferences.getString("password", "");
            mAccountEdit.setText(account);
            mPasswordEdit.setText(password);
            mCheckBox.setChecked(true);
        }

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = mAccountEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();
                // 如果账号是admin且密码是123456，就认为登录成功
                if (account.equals("admin") && password.equals("123456")) {
                    mEditor = mSharedPreferences.edit();
                    if (mCheckBox.isChecked()) { // 检查复选框是否被选中
                        mEditor.putBoolean("remember_password", true);
                        mEditor.putString("account", account);
                        mEditor.putString("password", password);
                    } else {
                        mEditor.clear();
                    }
                    mEditor.commit();

                    Intent intent = new Intent(LoginActivity.this, BroadcastBestPracticeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,
                            "account or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
