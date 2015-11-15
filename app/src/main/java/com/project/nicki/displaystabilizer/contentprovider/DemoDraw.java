package com.project.nicki.displaystabilizer.contentprovider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.project.nicki.displaystabilizer.dataprocessor.proDataFlow;

public class DemoDraw extends View {
    private static final String TAG = "DemoDraw";
    public static boolean drawing = false;
    public static Handler DrawStabilizerHandler;
    public HandlerThread DrawStabilizerHandlerThread;
    protected Context mContext;
    private Paint paint = new Paint();
    private Path path = new Path();

    public DemoDraw(Context context) {
        super(context);
        this.mContext = context.getApplicationContext();
    }

    public DemoDraw(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint.setAntiAlias(true);
        paint.setStrokeWidth(5f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        DrawStabilizerHandlerThread = new HandlerThread("handlerthread");
        DrawStabilizerHandlerThread.start();
        DrawStabilizerHandler = new Handler(DrawStabilizerHandlerThread.getLooper());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        if((event.getAction() == MotionEvent.ACTION_DOWN) || (event.getAction() == MotionEvent.ACTION_MOVE)){

        }


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                Message msgSTART = new Message();
                msgSTART.what = 0;

                float[] dataSTART = new float[2];
                long currTimeSTART = System.currentTimeMillis();
                Bundle drawposBundleSTART =new Bundle();
                dataSTART[0]=eventX;
                dataSTART[1]=eventY;
                drawposBundleSTART.putFloatArray("Draw",dataSTART);
                drawposBundleSTART.putLong("Time", currTimeSTART);
                msgSTART.setData(drawposBundleSTART);

                proDataFlow.drawHandler.sendMessage(msgSTART);

                drawing = true;
                path.moveTo(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "Drawing");
                Message msgDRAWING = new Message();
                msgDRAWING.what = 1;

                float[] dataDRAWING = new float[2];
                long currTimeDRAWING = System.currentTimeMillis();
                Message msgDrawing = new Message();
                Bundle drawposBundleDRAWING =new Bundle();
                dataDRAWING[0]=eventX;
                dataDRAWING[1]=eventY;
                drawposBundleDRAWING.putFloatArray("Draw",dataDRAWING);
                drawposBundleDRAWING.putLong("Time", currTimeDRAWING);
                msgDRAWING.setData(drawposBundleDRAWING);

                proDataFlow.drawHandler.sendMessage(msgDRAWING);

                drawing = true;
                path.lineTo(eventX, eventY);

                break;
            case MotionEvent.ACTION_UP:

                Message msgSTOP = new Message();
                msgSTOP.what = 2;
                proDataFlow.drawHandler.sendMessage(msgSTOP);

                drawing = false;
                // nothing to do
                break;
            default:
                drawing = false;
                return false;
        }

        // Schedules a repaint.
        invalidate();
        Log.d("aaaaaa", "aaaaaaaaaaaaaaaaa");
        return true;
    }
}