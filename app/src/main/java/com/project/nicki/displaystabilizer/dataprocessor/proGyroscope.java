package com.project.nicki.displaystabilizer.dataprocessor;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.project.nicki.displaystabilizer.dataprovider.getGyroscope;
/**
 * Created by nicki on 11/15/2015.
 */
public class proGyroscope implements Runnable {
    private Context mContext;
    private float proGyroX,proGyroY,proGyroZ;
    private float[] data;
    private String TAG = "proGyroscope";
    public proGyroscope(Context context) {
        mContext = context;
    }
    @Override
    public void run() {
        long currTime = System.currentTimeMillis();
        proGyroX = getGyroscope.GyroX;
        proGyroY = getGyroscope.GyroY;
        proGyroZ = getGyroscope.GyroZ;


        data = new float[3];
        data[0] = proGyroX;
        data[1] = proGyroY;
        data[2] = proGyroZ;
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putFloatArray("Gyro",data);
        bundle.putLong("Time",currTime);
        msg.setData(bundle);
        proDataFlow.GyroHandler.sendMessage(msg);
        Log.d(TAG, "GYROTIME " + String.valueOf(data[0]) + "  " + String.valueOf(System.currentTimeMillis() + "  " + String.valueOf((int) System.currentTimeMillis())));
        Log.d(TAG, String.valueOf(proGyroX) + " " + String.valueOf(proGyroY) + " " + String.valueOf(proGyroZ));
    }
}
