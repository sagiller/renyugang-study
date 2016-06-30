package com.sagiller.renyugang.chapter4.section4.topic3;

import android.app.Activity;
import android.os.Bundle;

import com.sagiller.renyugang.R;

/**
 * Created by sagiller on 16/5/31.
 */
public class DemoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_4_4_3);
        CircleView circle = (CircleView)findViewById(R.id.circle);
        circle.setCircleColor(getResources().getColor(R.color.colorPrimary));
    }
}
