package com.project.nicki.displaystabilizer.dataprocessor;

import android.content.Context;
import android.util.Log;

import com.project.nicki.displaystabilizer.dataprovider.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.floor;

/**
 * Created by nicki on 10/27/2015.
 */
public class proAccelerometer implements  Runnable{
    private String TAG = "proAccelerometer";
    private Context mContext;
    public static float proAcceX,proAcceY,proAcceZ;
    public static float proGravX=0,proGravY=0,proGravZ=0;
    public static long proTime = 1;
    public static float proPosX = 0,proPosY = 0,proPosZ = 0;
    private static boolean init = true;
    public proAccelerometer(Context context) {
        mContext = context;
    }
    @Override
    public void run() {
        while (true) {
            if(init==true){
                for(int t=0;t<1000;t++){
                    proGravX = proGravX+getAccelerometer.AcceX;
                    proGravY = proGravX+getAccelerometer.AcceY;
                    proGravZ = proGravX+getAccelerometer.AcceZ;
                }
                proGravX = proGravX/1000;
                proGravY = proGravY/1000;
                proGravZ = proGravZ/1000;
                init = false;
            }

            proAcceX = getAccelerometer.AcceX - proGravX;

            proAcceY = getAccelerometer.AcceY - proGravY;

            proAcceZ = getAccelerometer.AcceZ - proGravZ;

            try {
                Thread.sleep(proTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, String.valueOf(proAcceX)+" "+String.valueOf(proAcceY)+" "+String.valueOf(proAcceZ));
        }
    }
}
