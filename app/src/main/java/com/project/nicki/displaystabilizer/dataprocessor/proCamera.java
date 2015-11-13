package com.project.nicki.displaystabilizer.dataprocessor;
import android.content.Context;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.project.nicki.displaystabilizer.dataprovider.getFrontcam;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgproc.Imgproc;
import org.opencv.utils.Converters;
import org.opencv.video.Video;

import java.lang.reflect.Array;

import javax.security.auth.callback.Callback;

/**
 * Created by nicki on 11/12/2015.
 */
public class proCamera implements Runnable{
    private MatOfKeyPoint       preKeypoints,nxtKeypoints;
    public static Mat nxtMat,preMat,proMat;
    private  Context mContext;
    private String TAG = "proCamera";
    /*
    @Override
    public boolean handleMessage(Message msg) {
        //打印线程的名称
        System.out.println(" handleMessage CurrentThread = " + Thread.currentThread().getName());
        return true;
     }
    */
    Mat mask;
    MatOfPoint initial;
    MatOfByte status;
    MatOfFloat err;
    MatOfPoint2f prevPts;
    MatOfPoint2f nextPts;
    public double deltaX;
    public double deltaY;
    public int runNum = 0;

    @Override
    public void run() {
        Log.d(TAG,"Runnable start");
        //while(true){
            if(getFrontcam.curMat != null){
                FeatureDetector detector = FeatureDetector.create(FeatureDetector.ORB);
                preMat = new Mat();
                preMat = nxtMat;
                nxtMat = new Mat();
                nxtMat = getFrontcam.curMat;
                Log.d(TAG,"Runnable Processing 1");
                if(nxtMat != null){
                    Log.d(TAG,"Runnable Processing 2");
                    status = new MatOfByte();
                    err = new MatOfFloat();
                    prevPts = new MatOfPoint2f();
                    if(nextPts != null){
                        prevPts = nextPts;
                    }
                    initial = new MatOfPoint();
                    //prevPts = nextPts;
                    if(runNum<5){
                        Imgproc.goodFeaturesToTrack(nxtMat, initial, 500, 0.01, 0.01);
                    }
                    runNum++;
                    if(initial != null){
                        initial.convertTo(prevPts, CvType.CV_32FC2);
                    }

                    if(prevPts.toArray().length > 50 && preMat != null && nxtMat != null) {
                        Log.d(TAG,"Runnable Processing 3");
                        nextPts = new MatOfPoint2f();
                        //Log.d(TAG, "lenght = " + String.valueOf(prevPts.toArray().length));
                        Video.calcOpticalFlowPyrLK(preMat, nxtMat, prevPts, nextPts, status, err);
                        Point[] pointp = prevPts.toArray();
                        Point[] pointn = nextPts.toArray();

                        deltaX = pointn[5].x - pointp[5].x;
                        deltaY = pointn[5].y - pointp[5].y;
                        Log.d(TAG, "deltaX,Y = " + String.valueOf(deltaX)+" "+String.valueOf(deltaY));



                    }

                }






                proMat = nxtMat;
            }
        Log.d(TAG,"Runnable stop");
        //}

    }
}
