package com.sagiller.renyugang.chapter4.section4.topic3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.sagiller.renyugang.R;

/**
 * Created by sagiller on 16/5/31.
 */
public class CircleView extends View{
    private int mColor = Color.RED;
    private int mWidth = 200;
    private int mHeight = 200;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor = a.getColor(R.styleable.CircleView_circle_color, Color.RED);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CircleView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint.setColor(mColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, mHeight);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, mHeight);
        }
    }
    public void setCircleColor(int color) {
        mColor = color;
        init();
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddintTop = getPaddingTop();
        final int paddingBotton = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - getPaddingTop() - paddingBotton;
        int radius = Math.min(width, height) / 2;
        canvas.drawCircle(paddingLeft + width / 2,getPaddingTop() + height / 2,radius,mPaint);
    }
}
