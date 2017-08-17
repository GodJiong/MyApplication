package com.wj.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.button6)
    Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                startActivity(new Intent(MainActivity.this, BottomSheetDialogActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(MainActivity.this, DrawerLayoutActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(MainActivity.this, NavigationViewActivity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(MainActivity.this, PaletteActivity.class));
                break;
            case R.id.button5:
                startActivity(new Intent(MainActivity.this, BottomNavigationViewActivity.class));
                break;
            case R.id.button6:
                startActivity(new Intent(MainActivity.this, DateAndTimeActivity.class));
                break;
        }
    }
}
