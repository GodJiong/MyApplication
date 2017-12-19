package com.wj.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wj.test.R;
import com.wj.test.view.CompoundView;

/**
 * 外部类测试自定义控件
 * Created by wj on 2017/12/19 17:05
 */
public class CustomWidgetActivity extends AppCompatActivity {
    private CompoundView view;// 自定义控件对象
    private boolean isNotFinish = true;// 是否结束
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_widget);
        view = (CompoundView) findViewById(R.id.cv_progress);
        view.setMax(100);// 设置最大值
        view.setProgress(90);// 设置当前进度
        view.setBtnCancelListener(new View.OnClickListener() {// 取消监听
            @Override
            public void onClick(View v) {
                isNotFinish = false;
                i = 0;
                view.setProgress(i);
            }
        });
        Button down = (Button) findViewById(R.id.btn_down);
        down.setOnClickListener(new View.OnClickListener() {// 下载监听
            @Override
            public void onClick(final View v) {
                isNotFinish = true;
                // 子线程模拟进度
                new Thread() {
                    @Override
                    public void run() {
                        while (isNotFinish && i <= 100) {
                            try {
                                Thread.sleep(1000);
                                // 主线程更新UI
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        view.setProgress(i++);
                                    }
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
            }
        });
    }
}
