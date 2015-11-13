package com.project.nicki.displaystabilizer.dataprovider;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;

/**
 * Created by nicki on 10/26/2015.
 */
public class getGyroscope implements Runnable{
    public static float GyroX,GyroY,GyroZ;
    private String TAG = "getGyroscope";
    private Context mContext;
    private SensorManager mSensorManager = null;
    private Sensor mSensor;
    private SensorEventListener mListener;
    private HandlerThread mHandlerThread;
    String GYROBROADCAST_STRING = "GYROBROADCAST";
    public getGyroscope(Context context) {
        mContext = context;
    }
    @Override
    public void run() {
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mHandlerThread = new HandlerThread("GyroscopeLogListener");
        mHandlerThread.start();
        Handler handler = new Handler(mHandlerThread.getLooper());
        mListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                GyroX = event.values[0];
                GyroY = event.values[1];
                GyroZ = event.values[2];
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        mSensorManager.registerListener(mListener, mSensor, SensorManager.SENSOR_DELAY_FASTEST,
                handler);
    }
}
