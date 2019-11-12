package com.cham.helx.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Hello World
 * Date: 2019/11/5
 * Author: Cham
 *
 * 单柱状view
 */
public class PillarView extends View {

    private Paint mChartPaint;
    private int mHeight =0;
    private int mWidth =0;

    public PillarView(Context context) {
        this(context,null);
    }

    public PillarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PillarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mChartPaint = new Paint();
        mChartPaint.setAntiAlias(true);
        mChartPaint.setColor(Color.RED);
        mChartPaint.setStyle(Paint.Style.FILL);
    }


    public void setmHeight(int height){
       this.mHeight = height;
        invalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = 50;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF oval3 = new RectF(0, 10, mWidth, mHeight);// 设置个新的长方形
        canvas.drawRoundRect(oval3, 10, 10, mChartPaint);//第二个参数是x半径，第三个参数是y半径

    }
}
