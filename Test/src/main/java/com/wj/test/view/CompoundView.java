package com.wj.test.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.wj.test.R;

/**
 * 自定义组件
 * Created by wangjiong on 2017/12/18.
 */

public class CompoundView extends LinearLayout {
    private ProgressBar pbar;
    private Button btnCancel;
    private int max = 100;// 最大进度（这里默认为100，自己可以随便设置，看业务）
    private int progress = 0;// 当前进度（这里默认为0，自己可以随便设置，看业务）

    /**
     * 设置最大值
     *
     * @param max 最大值
     */
    public void setMax(int max) {
        this.max = max;
        pbar.setMax(max);
    }

    /**
     * 设置当前进度
     *
     * @param progress 进度
     */
    public void setProgress(int progress) {
        if (progress > max) {
            this.progress = max;
        } else {
            this.progress = progress;
        }
        pbar.setProgress(this.progress);
    }

    public void setBtnCancelListener(OnClickListener lickListener) {
        btnCancel.setOnClickListener(lickListener);// 给取消按钮设置监听（这里也可以用接口回调供外部类调用）
    }

    /**
     * 子控件均被映射成XML文件才触发的（在xml静态设置属性之后，动态设置属性之前）
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        pbar.setMax(max);
        pbar.setProgress(progress);
    }

    public CompoundView(Context context) {
        super(context);
        // 初始化布局
        initView(context);
    }

    public CompoundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 初始化布局
        initView(context);
        // 自定义布局静态设置属性。这一步会将你引用此自定义布局时在xml设置的值取出来
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CompoundView);
        max = typedArray.getInt(R.styleable.CompoundView_max, 100);
        progress = typedArray.getInt(R.styleable.CompoundView_progress, 10);
        typedArray.recycle();
    }

    /**
     * 初始化布局
     */
    private void initView(Context context) {
        // 将布局导入到LinearLayout中
        View view = LayoutInflater.from(context).inflate(R.layout.view_compound, this);
        pbar = (ProgressBar) view.findViewById(R.id.pbar_progress);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
    }
}
