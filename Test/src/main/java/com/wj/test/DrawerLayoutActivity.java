package com.wj.test;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wj on 2017/8/10 16:11
 */

public class DrawerLayoutActivity extends AppCompatActivity {
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.item1)
    TextView item1;
    @BindView(R.id.item2)
    TextView item2;
    @BindView(R.id.item3)
    TextView item3;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_drawer_layout);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        drawerLayout.openDrawer(Gravity.START);
    }

    @OnClick({R.id.item1, R.id.item2, R.id.item3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item1:
                Toast.makeText(DrawerLayoutActivity.this, "item1", Toast.LENGTH_LONG).show();
                break;
            case R.id.item2:
                Toast.makeText(DrawerLayoutActivity.this, "item2", Toast.LENGTH_LONG).show();
                break;
            case R.id.item3:
                Toast.makeText(DrawerLayoutActivity.this, "item3", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
