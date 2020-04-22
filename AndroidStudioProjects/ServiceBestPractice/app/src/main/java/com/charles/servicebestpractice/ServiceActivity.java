package com.charles.servicebestpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ServiceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        Intent intent = new Intent(this, LongRunningService.class);
        startService(intent);
    }
}
