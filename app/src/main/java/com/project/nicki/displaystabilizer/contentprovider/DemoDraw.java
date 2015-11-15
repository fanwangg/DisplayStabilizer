package com.project.nicki.displaystabilizer.contentprovider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.project.nicki.displaystabilizer.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.project.nicki.displaystabilizer.stabilization.DrawStabilizer;
import com.project.nicki.displaystabilizer.dataprocessor.proDataFlow;


import java.util.logging.LogRecord;

public class DemoDraw extends View {
    private static final String TAG = "DemoDraw";
    private Paint paint = new Paint();
    private Path path = new Path();
    public static boolean drawing = false;
    public Handler DrawStabilizerHandler;
    public HandlerThread DrawStabilizerHandlerThread;
    protected Context mContext;

    public DemoDraw(Context context){
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

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawing = true;
                path.moveTo(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "Drawing");
                drawing = true;
                path.lineTo(eventX, eventY);
                break;
            case MotionEvent.ACTION_UP:
                drawing = false;
                // nothing to do
                break;
            default:
                drawing = false;
                return false;
        }

        // Schedules a repaint.
        invalidate();
        Log.d("aaaaaa","aaaaaaaaaaaaaaaaa");
        return true;
    }
}