package com.project.nicki.displaystabilizer.dataprocessor;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Created by nicki on 11/14/2015.
 */
public class proDataFlow implements Runnable {
    private static final String TAG = "proDataFlow";
    public static Handler drawHandler;
    public static Handler CameraHandler;
    public static Handler AcceHandler;
    public static Handler GyroHandler;
    public double[][][] data;
    Runnable run = new Runnable() {
        @Override
        public void run() {

            try {
                Log.d(TAG, "data " + "Time=" + proCamera.data[0] + " deltaX=" + proCamera.data[1] + " deltaY=" + proCamera.data[2]);
            } catch (Exception ex) {
            }


        }
    };
    private Context mContext;

    public proDataFlow(Context context) {
        mContext = context;
    }

    @Override
    public void run() {
        try {
            Log.d(TAG, "run start");
            /*
            while(DemoDraw.drawing == true){
                //Log.d(TAG,"drawing");
                run.run();
            }

            long endTime = System.currentTimeMillis();
            long currproCam = (long) proCamera.data[0];
            while(currproCam < endTime) {
                run.run();
                currproCam  =  proCamera.currTime;
            }
            */
        } catch (Exception ex) {
        }

        ///Handlers
        Looper.prepare();
        drawHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        Log.d(TAG, "Start");

                    case 0:
                        Log.d(TAG, "Stop");
                }
                Log.d(TAG, "X: " + msg.arg1 + " Y:" + msg.arg2);
                Log.d(TAG, String.valueOf(System.currentTimeMillis()));
            }
        };
        CameraHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle MovementBundle = new Bundle();
                MovementBundle = msg.getData();
                double[] MovementData = new double[3];
                MovementData = MovementBundle.getDoubleArray("Movement");
                Log.d(TAG,"DATA@ "+"Time:"+MovementData[0]+" X:"+MovementData[1]+" Y:"+MovementData[2]);
            }
        };
        AcceHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        Log.d(TAG, "Start");
                    case 0:
                        Log.d(TAG, "Stop");
                }
            }
        };
        GyroHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        Log.d(TAG, "Start");
                    case 0:
                        Log.d(TAG, "Stop");
                }
            }
        };

        Looper.loop();


    }
}
