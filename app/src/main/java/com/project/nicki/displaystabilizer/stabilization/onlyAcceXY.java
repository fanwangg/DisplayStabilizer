package com.project.nicki.displaystabilizer.stabilization;
import com.project.nicki.displaystabilizer.dataprocessor.*;
import com.project.nicki.displaystabilizer.dataprovider.getAccelerometer;

import android.content.Context;
import android.util.Log;

/**
 * Created by nicki on 10/28/2015.
 */
public class onlyAcceXY implements Runnable{
    private Context mContext;
    public static float staPosX,staPosY,staPosZ;
    private String TAG = "onlyAcceXY";
    private static boolean init = true;
    public static float proVeloX = 0,proVeloY = 0,proVeloZ = 0;
    public onlyAcceXY(Context context) {
        mContext = context;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true){
            if(init==true){
                staPosX = 350;
                staPosY = 400;
                init = false;
            }

            proVeloX = proVeloX + (float)0.5+proAccelerometer.proAcceX*proAccelerometer.proTime;
            proVeloY = proVeloY + proAccelerometer.proAcceY*proAccelerometer.proTime;
            proVeloZ = proVeloZ + proAccelerometer.proAcceZ*proAccelerometer.proTime;

            staPosX = staPosX+(float)(1*(proVeloX*proAccelerometer.proTime+(1 / 2)*proAccelerometer.proAcceX*Math.pow(proAccelerometer.proTime,2)));

            staPosY = staPosY+(float)(1*(proVeloY*proAccelerometer.proTime+(1 / 2)*proAccelerometer.proAcceY*Math.pow(proAccelerometer.proTime,2)));
            Log.d(TAG, "X= "+String.valueOf(staPosX)+" Y= "+String.valueOf(staPosY));
            Log.d("deltaXY", "X= "+String.valueOf(proVeloX*proAccelerometer.proTime+(1 / 2)*proAccelerometer.proAcceX*Math.pow(proAccelerometer.proTime,2))+
                    " Y= "+String.valueOf(proVeloY*proAccelerometer.proTime+(1 / 2)*proAccelerometer.proAcceY*Math.pow(proAccelerometer.proTime,2)));
            Log.d("Velo",String.valueOf(proVeloX));

            //Log.d(TAG, "X"+String.valueOf(staPosY));
        }
    }


}