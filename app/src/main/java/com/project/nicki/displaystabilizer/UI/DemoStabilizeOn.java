package com.project.nicki.displaystabilizer.UI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.project.nicki.displaystabilizer.dataprocessor.*;
import com.project.nicki.displaystabilizer.R;
import com.project.nicki.displaystabilizer.stabilization.*;

import java.util.Random;

public class DemoStabilizeOn extends AppCompatActivity {
    private String TAG = "DemoStabilization";
    Path path;
    PathMeasure measure;
    float[] pos, tan;
    float speed, distance;
    MySurfaceView mySurfaceView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySurfaceView = new MySurfaceView(this);
        setContentView(mySurfaceView);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mySurfaceView.onResumeMySurfaceView();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mySurfaceView.onPauseMySurfaceView();
    }

    class MySurfaceView extends SurfaceView implements Runnable{

        Thread thread = null;
        SurfaceHolder surfaceHolder;
        volatile boolean running = false;

        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Random random;

        public MySurfaceView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
            surfaceHolder = getHolder();
            random = new Random();
        }

        public void onResumeMySurfaceView(){
            running = true;
            thread = new Thread(this);
            thread.start();
        }

        public void onPauseMySurfaceView(){
            boolean retry = true;
            running = false;
            while(retry){
                try {
                    thread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while(running){

                if(surfaceHolder.getSurface().isValid()){
                    /*
                    path = new Path();
                    path.moveTo(proAccelerometer.preAcceX, proAccelerometer.preAcceY);
                    path.lineTo(proAccelerometer.proAcceX, proAccelerometer.proAcceY);
                    measure = new PathMeasure(path, false);
                    speed = measure.getLength() / 2;
                    while(distance < measure.getLength()) {
                        measure.getPosTan(distance, pos, tan);
                        distance += speed;   // Traversal
                        Canvas canvas = surfaceHolder.lockCanvas();
                        canvas.drawColor(Color.BLACK);
                        paint.setColor(Color.WHITE);
                        //canvas.drawCircle(350 + pos[0], 450 + pos[1], 30, paint);
                        canvas.drawCircle(350 + proAccelerometer.proAcceX, 450 + proAccelerometer.proAcceY, 30, paint);
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        Log.d(TAG, String.valueOf(proAccelerometer.proAcceX));
                    }
                    */
                    Canvas canvas = surfaceHolder.lockCanvas();
                    canvas.drawColor(Color.BLACK);
                    paint.setColor(Color.WHITE);
                    canvas.drawCircle(onlyAcceXY.staPosX,onlyAcceXY.staPosY, 30, paint);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                    //Log.d(TAG, String.valueOf(proAccelerometer.proAcceX));


                }
            }
        }

    }
}
