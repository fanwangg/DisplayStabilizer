package com.project.nicki.displaystabilizer.stabilization;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Created by nicki on 11/15/2015.
 */
public class stabilize_v1 implements Runnable {
    private static final String TAG = "stabilize_v1";
    public static Handler getDatas;
    private Context mContext;

    public stabilize_v1(Context context) {
        mContext = context;
    }
    @Override
    public void run() {
        Looper.prepare();
        getDatas = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d(TAG,"GOT BUNDLE");
            }
        };
        Looper.loop();
    }
}
