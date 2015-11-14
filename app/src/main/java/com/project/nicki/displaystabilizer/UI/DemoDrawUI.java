package com.project.nicki.displaystabilizer.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.nicki.displaystabilizer.R;
import com.project.nicki.displaystabilizer.contentprovider.*;
public class DemoDrawUI extends AppCompatActivity {
    DemoDraw DD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DD = new DemoDraw(this);
        setContentView(DD);
    }
}