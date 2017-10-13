package com.wj.test.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wj.test.R;
import com.wj.test.view.CircleView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangjiong on 2017/10/9.
 */

public class CircleViewActivity extends AppCompatActivity {
    @BindView(R.id.circleView)
    CircleView circleView;
    private boolean flag = true;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            int progress = circleView.getProgress();
            if (progress < 100) {
                circleView.setProgress(++progress);
            } else
                flag = false;
        }

    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myview);
        ButterKnife.bind(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        Thread.sleep(100);
                        mHandler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
