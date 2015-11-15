package com.project.nicki.displaystabilizer.stabilization;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import com.project.nicki.displaystabilizer.contentprovider.DemoDraw;

import java.util.ArrayList;

/**
 * Created by nicki on 11/15/2015.
 */
public class stabilize_v1 implements Runnable {
    private static final String TAG = "stabilize_v1";
    public static Handler getDatas;
    private Context mContext;
    public Bundle DataCollected ;
    public int LOGSTATUS;
    public stabilize_v1(Context context) {
        mContext = context;
    }


    @Override
    public void run() {
        DataCollected = new Bundle();
        Looper.prepare();
        getDatas = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d(TAG, "GOT BUNDLE");

                LOGSTATUS = msg.what;
                Bundle bundlegot = msg.getData();
                int bundlenum = 1;
                if( LOGSTATUS == 1 && msg.getData() != null){
                    DataCollected.putBundle(String.valueOf(bundlenum), bundlegot);
                    bundlenum = bundlenum +1 ;
                }else if(LOGSTATUS == 0 && DataCollected != null){
                    if(DataCollected != null){
                        bundlenum = 1;
                        Thread mthread = new Thread(new Stabilization(DataCollected));
                        new Thread(new Stabilization(DataCollected)).start();
                        Log.d(TAG,"Collect stopped");
                        DataCollected = new Bundle();
                    }

                }

            }
        };
        Looper.loop();
    }

    public class Stabilization implements Runnable{

        Bundle mbundle;
        public Stabilization(Bundle bundle){
            this.mbundle = bundle;
        }
        @Override
        public void run() {
            Log.d(TAG, "now on thread");
            if(mbundle != null){
                //int Length = mbundle.size();
                int Length = 100;
                float[][] a = new float[Length][2];
                for(int i = 0;i<Length;i++){
                    a[i][0] = i+3; //x
                    a[i][1] = i+5; //y
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("DrawPoints", a);
                Message msg2 = new Message();
                msg2.setData(bundle);
                DemoDraw.DrawStabilizerHandler.sendMessage(msg2);
                Log.d(TAG,"THREAD "+Length);
            }
        }
    }

}
