package com.project.nicki.displaystabilizer.stabilization;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Handler;
        import android.os.Message;
        import android.util.Log;

        import com.project.nicki.displaystabilizer.contentprovider.DemoDraw;
import  com.project.nicki.displaystabilizer.dataprocessor.proCamera;

/**
 * Created by nicki on 11/14/2015.
 */
public class DrawStabilizer implements Runnable {
    private static final String TAG = "DrawStabilizer";
    private Context mContext;
    public double[][][] data;
    public DrawStabilizer(Context context){
        mContext = context;
    }
    public static Handler mHandler;



    @Override
    public void run() {
        try{
            Log.d(TAG,"run start");
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
        }catch (Exception ex){

        }

    }

    Runnable run = new Runnable() {
        @Override
        public void run() {

            try {
                Log.d(TAG,"data "+"Time="+proCamera.data[0]+" deltaX="+proCamera.data[1]+" deltaY="+proCamera.data[2]);
            }catch (Exception ex){
            }


        }
    };
}
