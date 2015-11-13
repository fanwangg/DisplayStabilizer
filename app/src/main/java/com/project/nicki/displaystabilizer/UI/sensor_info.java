package com.project.nicki.displaystabilizer.UI;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.nicki.displaystabilizer.R;
import com.project.nicki.displaystabilizer.dataprovider.*;

public class sensor_info extends AppCompatActivity {
    private String TAG = "sensor_info";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_info);
        final TextView accelerometerdata = (TextView)findViewById(R.id.sensor_info_accelerometerdata);
        final TextView gyroscopedata = (TextView)findViewById(R.id.sensor_info_gyroscopedata);
        final Handler mUpdater = new Handler();
        final Runnable mUpdateView = new Runnable() {
            @Override
            public void run() {
                accelerometerdata.setText("X= " + getAccelerometer.AcceX + "\nY= " + getAccelerometer.AcceY + "\nZ= " + getAccelerometer.AcceZ);
                accelerometerdata.invalidate();
                gyroscopedata.setText("X= " + getGyroscope.GyroX + "\nY= " + getGyroscope.GyroY + "\nZ= " + getGyroscope.GyroZ);
                gyroscopedata.invalidate();
                mUpdater.postDelayed(this, 0);
            }
        };
        mUpdateView.run();
    }

}
