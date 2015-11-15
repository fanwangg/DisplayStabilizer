package com.project.nicki.displaystabilizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.nicki.displaystabilizer.UI.DemoDrawUI;
import com.project.nicki.displaystabilizer.contentprovider.DemoDraw;
import com.project.nicki.displaystabilizer.dataprocessor.proDataFlow;
import com.project.nicki.displaystabilizer.dataprovider.getFrontcam;
import com.project.nicki.displaystabilizer.stabilization.DrawStabilizer;

public class init extends AppCompatActivity {
    String TAG = "init";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //new Thread(new getAccelerometer(getBaseContext())).start();
        //new Thread(new getGyroscope(getBaseContext())).start();
        //new Thread(new proAccelerometer(getBaseContext())).start();
        //new Thread(new onlyAcceXY(getBaseContext())).start();
        //new Thread(new getFrontcam(getBaseContext())).start();
        //new Thread(new proCamera(getBaseContext())).start();
        new Thread(new proDataFlow(getBaseContext())).start();

        Intent goto_DemoDrawUI = new Intent();
        goto_DemoDrawUI.setClass(init.this, DemoDrawUI.class);
        startActivity(goto_DemoDrawUI);

        /*
        Intent goto_DemoStabilizeOn = new Intent();
        goto_DemoStabilizeOn.setClass(init.this, DemoStabilizeOn.class);
        startActivity(goto_DemoStabilizeOn);
        */
        /*
        Intent goto_getFrontcam = new Intent();
        goto_getFrontcam.setClass(init.this, getFrontcam.class);
        startActivity(goto_getFrontcam);
        */
        /*
        Intent goto_getBackcam = new Intent();
        goto_getBackcam.setClass(init.this, getBackcam.class);
        startActivity(goto_getBackcam);
        */
        /*
        Intent goto_sensor_info = new Intent();
        goto_sensor_info.setClass(init.this, sensor_info.class);
        startActivity(goto_sensor_info);
        */


    }


}

