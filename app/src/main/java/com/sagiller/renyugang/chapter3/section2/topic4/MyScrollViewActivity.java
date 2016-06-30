package com.sagiller.renyugang.chapter3.section2.topic4;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by sagiller on 16/5/16.
 */
public class MyScrollViewActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyScrollView view = new MyScrollView(this);
        view.setText("test");
        view.setClickable(true);
        setContentView(view);
    }
}
