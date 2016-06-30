package com.sagiller.renyugang.chapter3.section5.topic3;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by sagiller on 16/5/30.
 */
public class StickyLayout extends LinearLayout {
    private int mTouchSlop;
    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;
    // 分别记录上次滑动的坐标(onInterceptTouchEvent)
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;

    public StickyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//        int intercepted = 0;
//        int x = (int) event.getX();
//        int y = (int) event.getY();
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mLastXIntercept = x;
//                mLastYIntercept = y;
//                mLastX = x;
//                mLastY = y;
//                intercepted = 0;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int deltaX = x - mLastXIntercept;
//                int deltaY = y - mLastYIntercept;
//                if (mDisAllowInterceptTouchEventOnHeader && y <= getHeaderHeight()) {
//                    intercepted = 0;
//                } else if (Math.abs(deltaX) <= Math.abs(deltaY)) {
//                    intercepted = 0;
//                } else if (mStatus == STATUS_EXPANDED && deltaY <= -mTouchSlop) {
//                    intercepted = 1;
//                } else if (mGiveUpTouchEventListener != null) {
//                    if (mGiveUpTouchEventListener.giveUpTouchEvent(event) && deltaY >= mTouchSlop) {
//                        intercepted = 1;
//                    }
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                intercepted = 0;
//                mLastXIntercept = mLastYIntercept = 0;
//                break;
//            default:
//                break;
//        }
//        return intercepted != 0 && mIsSticky;
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (!mIsSticky) {
//            return true;
//        }
//        int x = (int) event.getX();
//        int y = (int) event.getY();
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN: {
//                break;
//            }
//            case MotionEvent.ACTION_MOVE:
//                int deltaX = x - mLastX;
//                int deltaY = y - mLastY;
//                Log.d(TAG, "mHeaderHeight=" + mHeaderHeight + " deltaY=" + deltaY + " mLastY");
//                break;
//            case MotionEvent.ACTION_UP:
//                // 这里做了一下判断，当松开手的时候，会自动向两边滑动，具体向哪边滑，要看当前所处的位置
//                int destHeight = 0;
//                if (mHeaderHeight <= mOriginalHeaderHeight * 0.5) {
//                    destHeight = 0;
//                    mStatus = STATUS_COLLAPSED;
//                } else {
//                    destHeight = mOriginalHeaderHeight;
//                    mStatus = STATUS_EXPANDED;
//                }
//
//                //慢慢滑向终点
//                this.smoothSetHeaderHeight(mHeaderHeight, destHeight, 500);
//                break;
//            default:
//                break;
//            mLastX = x;
//            mLastY = y;
//            return true;
//        }
//        return super.onTouchEvent(event);
//    }
}
