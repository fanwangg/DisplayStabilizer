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
                    case 0:
                    case 1:
                        Log.d(TAG, "Start");
                        Bundle DrawBundle = new Bundle();
                        DrawBundle = msg.getData();
                        float[] DrawData = DrawBundle.getFloatArray("Draw");
                        long DrawTime = DrawBundle.getLong("Time");
                        Log.d(TAG, "DrawDATA@ " + "Time:" + String.valueOf(DrawTime) + " X:" + String.valueOf(DrawData[0]) + " Y:" + String.valueOf(DrawData[1]));
                    case 2:
                        Log.d(TAG, "Stop");
                }

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
                long CameraTime = MovementBundle.getLong("Time");
                Log.d(TAG, "CameraDATA@ " + "Time:" + String.valueOf(CameraTime) + " X:" + String.valueOf(MovementData[0]) + " Y:" + String.valueOf(MovementData[1]));
            }
        };
        AcceHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle AcceBundle = new Bundle();
                AcceBundle = msg.getData();
                float[] AcceData = new float[3];
                long AcceTime ;
                AcceData = AcceBundle.getFloatArray("Acce");
                AcceTime = AcceBundle.getLong("Time");
                Log.d(TAG, "AcceDATA@ " + "Time:" + String.valueOf(AcceTime) + " X:" + String.valueOf(AcceData[0]) + " Y:" + String.valueOf(AcceData[1]) + " Z:"+String.valueOf(AcceData[2]));
            }
        };
        GyroHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle GyroBundle = new Bundle();
                GyroBundle = msg.getData();
                float[] GyroData = new float[3];
                long GyroTime;
                GyroData = GyroBundle.getFloatArray("Gyro");
                GyroTime = GyroBundle.getLong("Time");
                Log.d(TAG, "GyroDATA@ " + "Time:" + String.valueOf(GyroTime) + " X:" + String.valueOf(GyroData[0]) + " Y:" + String.valueOf(GyroData[1]) + " Z:" + String.valueOf(GyroData[2]));
            }
        };

        Looper.loop();


    }
}
