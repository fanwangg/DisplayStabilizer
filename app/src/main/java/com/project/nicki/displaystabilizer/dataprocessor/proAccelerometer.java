package com.project.nicki.displaystabilizer.dataprocessor;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import com.project.nicki.displaystabilizer.dataprocessor.proDataFlow;
import com.project.nicki.displaystabilizer.dataprovider.getAccelerometer;

/**
 * Created by nicki on 10/27/2015.
 */
public class proAccelerometer implements Runnable {
    public static float proAcceX, proAcceY, proAcceZ;
    public static float proGravX = 0, proGravY = 0, proGravZ = 0;
    private float[] data;
    public static long proTime = 1;
    public static float proPosX = 0, proPosY = 0, proPosZ = 0;
    private static boolean init = true;
    private String TAG = "proAccelerometer";
    private Context mContext;
    public proAccelerometer(Context context) {
        mContext = context;
    }
    @Override
    public void run() {
        long currTime = System.currentTimeMillis();
        if (init == true) {
            for (int t = 0; t < 1000; t++) {
                proGravX = proGravX + getAccelerometer.AcceX;
                proGravY = proGravX + getAccelerometer.AcceY;
                proGravZ = proGravX + getAccelerometer.AcceZ;
            }
            proGravX = proGravX / 1000;
            proGravY = proGravY / 1000;
            proGravZ = proGravZ / 1000;
            init = false;
        }

        proAcceX = getAccelerometer.AcceX - proGravX;
        proAcceY = getAccelerometer.AcceY - proGravY;
        proAcceZ = getAccelerometer.AcceZ - proGravZ;

        data = new float[3];
        data[0] = proAcceX;
        data[1] = proAcceY;
        data[2] = proAcceZ;
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putFloatArray("Acce",data);
        bundle.putLong("Time",currTime);
        msg.setData(bundle);
        proDataFlow.AcceHandler.sendMessage(msg);

        Log.d(TAG, String.valueOf(proAcceX) + " " + String.valueOf(proAcceY) + " " + String.valueOf(proAcceZ));

    }
}
