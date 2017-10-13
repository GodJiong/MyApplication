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
    private int mCircleWidth;
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

    public CircleView(Context context) {//第一个构造函数：当不需要使用xml声明或者不需要使用inflate动态加载时候，实现此构造函数即可
        this(context, null);//会接着执行第二个构造方法
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {//第二个构造函数: 当需要在xml中声明此控件，则需要实现此构造函数。并且在构造函数中把自定义的属性与控件的数据成员连接起来。
        this(context, attrs, 0);//会接着执行第三个构造方法
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {//第三个构造函数：接受一个style资源
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {//循环得到每一个属性
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CircleView_firstColor:
                    mFirstColor = a.getColor(attr, Color.BLACK);//获取设置的颜色，并提供默认值为黑色
                    break;
                case R.styleable.CircleView_secondColor:
                    mSecondColor = a.getColor(attr, Color.BLACK);///获取设置的颜色，并提供默认值为黑色
                    break;
                case R.styleable.CircleView_circleWith:
                    mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));//获取设置的宽度，并提供默认值
                    break;

                default:

                    break;
            }
        }
        a.recycle();//回收内存

        mPaint = new Paint();//定于固定圆和圆弧公用画笔
        mTextPaint = new Paint();//定义文字画笔
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
        mCenter = getWidth() / 2;//获取圆心的X坐标（坐标系的原点是 View 左上角的那个点；水平方向是 x 轴，右正左负；竖直方向是 y 轴，下正上负）
        mRadius = mCenter - mCircleWidth / 2;//圆的半径(mCircleWith是圆环宽度)
//        canvas.drawColor(Color.parseColor("#88880000"));//颜色填充方法一般用于在绘制之前设置底色，或者在绘制之后为界面设置半透明蒙版
        //画圆
        drawCircle(canvas);
        //绘制进度
        drawTextView(canvas);
        //画圆弧
        drawArc(canvas);
    }

    private void drawCircle(Canvas canvas) {
        //设置画笔线宽
        mPaint.setStrokeWidth(mCircleWidth);//在 STROKE 和 FILL_AND_STROKE 下，还可以使用 paint.setStrokeWidth(float width)来设置线条的宽度：
        //设置画笔为空心
        mPaint.setStyle(Paint.Style.STROKE);//默认是fill填充模式
        //消除锯齿
        mPaint.setAntiAlias(true);//去毛边 或者在 new Paint() 的时候传入一个 ANTI_ALIAS_FLAG 参数也行
        //设置画笔颜色
        mPaint.setColor(mFirstColor);
        canvas.drawCircle(mCenter, mCenter, mRadius, mPaint);
    }

    private void drawTextView(Canvas canvas) {
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(sptopx(20.0f));
        mTextPaint.setAntiAlias(true);
        String text = (int) (100 * ((getProgress() / (1.0f * mMaxProgress)))) + "%";

       //计算出当前绘制出来的字符串有多宽，可以这么来！
        Rect mTextRect = new Rect();
        //返回包围整个字符串的最小的一个Rect区域
        mTextPaint.getTextBounds(text, 0, text.length(), mTextRect);
        //第二个参数x科普：x默认是这个字符串的左边在屏幕的位置，如果设置了paint.setTextAlign(Paint.Align.CENTER);那就是字符的中心；第三个参数y科普：y是指定这个字符baseline在屏幕上的位置
        canvas.drawText(text, mCenter - (mTextRect.width() / 2), mCenter + (mTextRect.height() / 2), mTextPaint);
    }

    private void drawArc(Canvas canvas) {//这个时候mPaint的setStrokeWidth还是上面的mCircleWith
        mPaint.setColor(mSecondColor);
        //设置画笔的笔触风格（ROUND，表示圆角的笔触）
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        float sweepAngle = 360 * (getProgress() / (1.0f * mMaxProgress));
        RectF rect = new RectF(mCenter - mRadius, mCenter - mRadius, mCenter + mRadius, mCenter + mRadius);//其实得到的就是里面圆环内的那个矩形。为啥效果是外接矩形，那是因为画线太粗挡住了

        //第一个参数oval科普：圆弧外接矩形；第二个参数startAngle科普：x 轴的正向，即正右的方向，是 0 度的位置；
        // 第三个参数sweepAngle科普：正角度为顺时针，负角度为逆时针;第四个参数useCenter科普：表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形。
        canvas.drawArc(rect, -90, sweepAngle, false, mPaint);
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
        invalidate(); //方法在 UI 线程中调用，重绘当前 UI
    }

    /**
     * 暴露给使用者的方法：获取进度值
     */
    public int getProgress() {
        return mProgress;
    }

}
