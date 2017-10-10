package com.wj.test.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.wj.test.R;

/**
 * Created by wangjiong on 2017/10/9.
 */

public class CircleView extends View {
    //圆圈的颜色
    private int mFirstColor;
    //圆弧的颜色
    private int mSecondColor;
    //圆圈的宽度
    private int mCircleWith;
    //圆圈的画笔
    private Paint mPaint;
    //文字的画笔
    private Paint mTextPaint;
    //默认圆的大小
    private static final float CIRCLE_SIZE = 150;
    //圆的中心
    private int mCenter;
    //圆的半径
    private int mRadius;
    //圆弧的进度
    private int mProgress = 0;
    //圆弧最大进度
    private static final int mMaxProgress = 100;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CircleView_firstColor:
                    mFirstColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CircleView_secondColor:
                    mSecondColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CircleView_circleWith:
                    mCircleWith = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;

                default:

                    break;
            }
        }
        a.recycle();//回收内存

        mPaint = new Paint();
        mTextPaint = new Paint();
    }

    /**
     * 计算当前view的宽高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获得它的父容器为它设置的测量模式和大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int mWidth = 0;
        int mHeight = 0;
        Log.e("宽高为：", widthSize + "-" + heightSize);

        if (widthMode == MeasureSpec.AT_MOST) {
            //没有固定宽度，就取默认宽度
            mWidth = (int) dptopx(CIRCLE_SIZE);
        } else {
            //如果设置了固定宽度，就取宽高的最小值
            mWidth = Math.min(widthSize, heightSize);
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            //没有固定高度，就取默认高度
            mHeight = (int) dptopx(CIRCLE_SIZE);
        } else {
            //如果设置了固定的高度，就取宽高的最小值
            mHeight = Math.max(widthSize, heightSize);
        }
        //设置高宽
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {//onDraw()方法里不建议创建对象
        super.onDraw(canvas);
        mCenter = getWidth() / 2;//获取圆心的X坐标
        mRadius = mCenter - mCircleWith / 2;//圆的半径(mCircleWith是圆环宽度)
        //画圆
        drawCircle(canvas);
        //绘制进度
        drawTextView(canvas);
        //画圆弧
        drawArc(canvas);
    }

    private void drawCircle(Canvas canvas) {
        //设置画笔线宽
        mPaint.setStrokeWidth(mCircleWith);
        //设置画笔为空心
        mPaint.setStyle(Paint.Style.STROKE);
        //消除锯齿
        mPaint.setAntiAlias(true);
        //设置画笔颜色
        mPaint.setColor(mFirstColor);
        canvas.drawCircle(mCenter, mCenter, mRadius, mPaint);
    }

    private void drawTextView(Canvas canvas) {
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(sptopx(20.0f));
        mTextPaint.setAntiAlias(true);
        String text = (int) (100 * ((getProgress() / (1.0f * mMaxProgress)))) + "%";
        Rect mTextRect = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), mTextRect);
        canvas.drawText(text, mCenter - (mTextRect.width() / 2), mCenter + (mTextRect.height() / 2), mTextPaint);
    }

    private void drawArc(Canvas canvas) {//这个时候mPaint的setStrokeWidth还是上面的mCircleWith
        mPaint.setColor(mSecondColor);
        //设置画笔的笔触风格（ROUND，表示圆角的笔触）
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        float sweepAngle = 360 * (getProgress() / (1.0f * mMaxProgress));
        RectF rect = new RectF(mCenter - mRadius, mCenter - mRadius, mCenter + mRadius, mCenter + mRadius);//其实得到的就是里面那个矩形。为啥效果是外接矩形，那是因为画线太粗挡住了

        canvas.drawArc(rect, -90, sweepAngle, false, mPaint);//顺时针为正
    }

    /**
     * dp转px
     */
    private float dptopx(float dp) {
        float scale = getResources().getDisplayMetrics().density;
        return scale * dp + 0.5f;
    }

    /**
     * sp转px
     */
    private float sptopx(float sp) {
        float scale = getResources().getDisplayMetrics().scaledDensity;
        return scale * sp;
    }

    /**
     * 暴露给使用者的方法：设置进度值
     */
    public void setProgress(int Progress) {
        this.mProgress = Progress;
        if (mProgress > mMaxProgress || mProgress < 0) {
            return;
        }
        postInvalidate();//在非 UI 线程中调用，通知 UI 线程重绘
    }

    public int getProgress() {
        return mProgress;
    }

}
