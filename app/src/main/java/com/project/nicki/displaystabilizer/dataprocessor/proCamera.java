package com.project.nicki.displaystabilizer.dataprocessor;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.project.nicki.displaystabilizer.UI.DemoDrawUI;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.Video;

/**
 * Created by nicki on 11/12/2015.
 */
public class proCamera implements Runnable {
    public static Mat nxtMat, preMat, proMat;
    public static long currTime;
    public static double[] data;
    public double deltaX;
    public double deltaY;
    public int runNum = 0;
    Mat mask;
    Mat[] mats;
    MatOfPoint initial;
    MatOfByte status;
    MatOfFloat err;
    MatOfPoint2f prevPts;
    MatOfPoint2f nextPts;
    private MatOfKeyPoint preKeypoints, nxtKeypoints;
    private Context mContext;
    private String TAG = "proCamera";

    @Override
    public void run() {
        Log.d(TAG, "Runnable start");
        if (DemoDrawUI.curMat != null) {
            currTime = System.currentTimeMillis();
            preMat = new Mat();
            preMat = nxtMat;
            nxtMat = new Mat();
            nxtMat = DemoDrawUI.curMat;
            Log.d(TAG, "Runnable Processing 1");
            if (nxtMat != null) {
                Log.d(TAG, "Runnable Processing 2");
                status = new MatOfByte();
                err = new MatOfFloat();
                prevPts = new MatOfPoint2f();
                prevPts = nextPts;
                if (prevPts == null || nxtMat != null) {
                    initial = new MatOfPoint();
                    Imgproc.goodFeaturesToTrack(nxtMat, initial, 500, 0.01, 0.01);
                    prevPts = new MatOfPoint2f();
                    initial.convertTo(prevPts, CvType.CV_32FC2);

                }
                if (preMat != null && nxtMat != null && prevPts != null) {
                    Log.d(TAG, "Runnable Processing 3");
                    nextPts = new MatOfPoint2f();
                    //Log.d(TAG, "lenght = " + String.valueOf(prevPts.toArray().length));
                    Video.calcOpticalFlowPyrLK(preMat, nxtMat, prevPts, nextPts, status, err);
                    Point[] pointp = prevPts.toArray();
                    Point[] pointn = nextPts.toArray();


                    deltaX = 0;
                    deltaY = 0;
                    for (int i = 0; i < pointn.length; i++) {
                        deltaX = deltaX + pointn[i].x - pointp[i].x;
                        deltaY = deltaY + pointn[i].y - pointp[i].y;
                    }
                    deltaX = deltaX / pointn.length;
                    deltaY = deltaY / pointn.length;

                    /*
                    deltaX = pointn[5].x - pointp[5].x;
                    deltaY = pointn[5].y - pointp[5].y;
                    */

                    data = new double[3];
                    data[0] = currTime;
                    data[1] = deltaX;
                    data[2] = deltaY;
                    Bundle bundle = new Bundle();
                    bundle.putDoubleArray("Movement", data);
                    Message msg = new Message();
                    msg.setData(bundle);
                    proDataFlow.CameraHandler.sendMessage(msg);

                    Log.d(TAG, "deltaX,Y = " + String.valueOf(deltaX) + " " + String.valueOf(deltaY));


                }

            }


            proMat = nxtMat;
        }
        Log.d(TAG, "Runnable stop");


    }
}
