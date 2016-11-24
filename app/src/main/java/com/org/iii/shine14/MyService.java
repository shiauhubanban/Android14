package com.org.iii.shine14;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private Timer timer;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        timer.schedule(new MyTask(), 0, 500);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            queryCommand();
        }
    }

    private void queryCommand(){
        try{
            MultipartUtility mu =
                    new MultipartUtility("http://10.0.3.2/cmd2.php?id=1","UTF-8");
            List<String> ret = mu.finish();
            String command = ret.get(0);
            Intent it = new Intent("brad");
            it.putExtra("cmd", command);
            sendBroadcast(it);

        }catch(Exception e){

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}