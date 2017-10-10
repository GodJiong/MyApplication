package com.wj.test.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.wj.test.R;

import java.lang.reflect.Field;

public class BottomNavigationViewActivity extends AppCompatActivity {
    private TextView mTextMessage;

    //BottomNavigationView监听item
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(item.getTitle());//获取item对应文字描述（menu里头设置的）
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(item.getTitle());
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(item.getTitle());
                    return true;
                case R.id.navigation_friends://如果你设置了监听，那么一定要对每个都返回true，或者最后返回true，不然点击无响应
                    mTextMessage.setText(item.getTitle());
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_view);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        disableShiftMode(navigation);//取消位移动画（根据需求）
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);//监听item
    }


    /**
     * 取消位移动画
     * 混淆时候要保持boolean mShiftingMode这个变量不被混淆
     *
     * @param view BottomNavigationView
     */
    private void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("WJ", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("WJ", "Unable to change value of shift mode", e);
        }
    }
}
