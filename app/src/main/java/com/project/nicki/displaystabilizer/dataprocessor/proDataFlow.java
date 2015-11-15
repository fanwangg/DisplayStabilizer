package com.project.nicki.displaystabilizer.dataprocessor;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import org.opencv.core.Mat;
import org.opencv.video.Video;
import org.opencv.videoio.VideoCapture;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by nicki on 11/14/2015.
 */
public class proDataFlow implements Runnable{
    private static final String TAG = "proDataFlow";
    private Context mContext;
    public double[][][] data;
    public proDataFlow(Context context){
        mContext = context;
    }
    public static Handler mHandler;



    @Override
    public void run() {
        try{
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
