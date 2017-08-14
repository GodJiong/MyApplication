package com.wj.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomSheetDialogActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.show)
    Button show;
    private BottomSheetDialog bottomSheetDialog;
    TextView album, photo, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showDialog();
    }

    /**
     * 底部弹出BottomSheetDialog
     */
    private void showDialog() {
        if (bottomSheetDialog == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_bottom_sheet, null);
            //下面没有用到ButterKnife是因为，ButterKnife不能一个布局里同时绑定多个view
            album = (TextView) view.findViewById(R.id.album);
            photo = (TextView) view.findViewById(R.id.photo);
            cancel = (TextView) view.findViewById(R.id.cancel);
            album.setOnClickListener(this);
            photo.setOnClickListener(this);
            cancel.setOnClickListener(this);
            bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(view);
            // 解决下滑隐藏dialog 后，再次调用show 方法显示时，不能弹出Dialog
            //通过获得design_bottom_sheet的视图来获取behavior，修改状态
            View view1 = bottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
            final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(view1);
            //实现对状态改变的监听
            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        bottomSheetDialog.dismiss();
                        //设置BottomSheetBehavior状态为STATE_COLLAPSED
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }
                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });
        }
    }

    @OnClick(R.id.show)
    public void onViewClicked() {
        bottomSheetDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.album:
                Toast.makeText(BottomSheetDialogActivity.this, "相册选取", Toast.LENGTH_LONG).show();
                bottomSheetDialog.dismiss();
                break;
            case R.id.photo:
                Toast.makeText(BottomSheetDialogActivity.this, "相机拍照", Toast.LENGTH_LONG).show();
                bottomSheetDialog.dismiss();
                break;
            case R.id.cancel:
                Toast.makeText(BottomSheetDialogActivity.this, "取消", Toast.LENGTH_LONG).show();
                bottomSheetDialog.dismiss();
                break;
        }
    }
}
