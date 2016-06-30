package com.sagiller.renyugang.chapter3.section2.topic4;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by sagiller on 16/5/16.
 * View会跟着手指的移动全屏移动
 */
public class MyScrollView extends TextView {
    private static final String TAG = "MyScrollView";
    int mLastX = 0, mLastY = 0;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                break;
            }
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.d(TAG,"move,deltaX:" + deltaX + " deltaY:" + deltaY);
                int translationX = (int) ViewHelper.getTranslationX(this) + deltaX;
                int translationY = (int) ViewHelper.getTranslationY(this) + deltaY;
                ViewHelper.setTranslationX(this,translationX);
                ViewHelper.setTranslationY(this, translationY);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;

    }
}
