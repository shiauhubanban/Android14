package com.org.iii.shine14;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.mesg);
        receiver = new MyReceiver();
        registerReceiver(receiver, new IntentFilter("brad"));

        Intent it = new Intent(this, MyService.class);
        startService(it);
    }

    @Override
    public void finish() {
        unregisterReceiver(receiver);
        super.finish();
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String command = intent.getStringExtra("cmd");
            textView.setText(command);
        }
    }
}