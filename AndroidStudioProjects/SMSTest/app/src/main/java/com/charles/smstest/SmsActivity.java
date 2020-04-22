package com.charles.smstest;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SmsActivity extends Activity {

    private TextView mSender, mContent;
    private EditText mTo, mMsgInput;
    private Button mSend;

    private IntentFilter mReceiveFilter, mSendFilter;
    private MessageReceiver mMessageReceiver;

    private SendStatusReceiver mSendStatusReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        mSender = (TextView) findViewById(R.id.sender);
        mContent = (TextView) findViewById(R.id.content);

        mReceiveFilter = new IntentFilter();
        mReceiveFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        mMessageReceiver = new MessageReceiver();
        registerReceiver(mMessageReceiver, mReceiveFilter);

        mTo = (EditText) findViewById(R.id.to);
        mMsgInput = (EditText) findViewById(R.id.msg_input);
        mSend = (Button) findViewById(R.id.send);

        mSendFilter = new IntentFilter();
        mSendFilter.addAction("SENT_SMS_ACTION");
        mSendStatusReceiver = new SendStatusReceiver();
        registerReceiver(mSendStatusReceiver, mSendFilter);

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager = SmsManager.getDefault();
                Intent sentIntent = new Intent("SENT_SMS_ACTION");
                PendingIntent pi = PendingIntent.getBroadcast(
                        SmsActivity.this, 0, sentIntent, 0);
                smsManager.sendTextMessage(mTo.getText().toString(),
                        null, mMsgInput.getText().toString(), pi, null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMessageReceiver);
        unregisterReceiver(mSendStatusReceiver);
    }

    /**
     * 接收短信的广播
     */
    class MessageReceiver extends BroadcastReceiver {

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            Object[] pdus = (Object[]) bundle.get("pdus"); // 提取短信消息
            SmsMessage[] messages = new SmsMessage[pdus.length];

            String format = intent.getStringExtra("format");

            for (int i = 0; i < messages.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
            }
            String address = messages[0].getOriginatingAddress(); // 获取发送方号码
            String fullMessage = "";
            for (SmsMessage message : messages) {
                fullMessage += message.getMessageBody(); // 获取短信内容
            }
            mSender.setText(address);
            mContent.setText(fullMessage);
        }
    }

    /**
     * 短信是否发送成功监听广播
     */
    class SendStatusReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (getResultCode() == RESULT_OK) {
                // 短信发送成功
                Toast.makeText(context, "Send succeeded", Toast.LENGTH_LONG).show();
            } else {
                // 短信发送失败
                Toast.makeText(context, "Send failed", Toast.LENGTH_LONG).show();
            }
        }
    }
}