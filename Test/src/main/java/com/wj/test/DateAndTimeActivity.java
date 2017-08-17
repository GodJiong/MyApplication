package com.wj.test;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DateAndTimeActivity extends AppCompatActivity {

    @BindView(R.id.date_dialog)
    Button dateDialog;
    @BindView(R.id.time_dialog)
    Button timeDialog;
    private int year, month, day, hour, minute;//根据日历获取系统年月日时分

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_and_time);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.date_dialog, R.id.time_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.date_dialog:
                getCalendar();//获取当前日期和时间
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {//月份从0开始所以要+1，比如显示的是4月，你获取的实际数字是3
                        Toast.makeText(DateAndTimeActivity.this, year + "年" + (month + 1) + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
                    }
                }
                        , year
                        , month//（月份从0开始，如果设置固定值需要减1,比如我想显示4月那就在这里填3）
                        , day);
                datePickerDialog.show();
                break;
            case R.id.time_dialog:
                getCalendar();//获取当前日期和时间
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(DateAndTimeActivity.this, hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
                    }
                }
                        , hour
                        , minute
                        , true);//是否为24小时制
                timePickerDialog.show();
                break;
        }
    }

    /**
     * 根据日历获取当前年月日时分
     */
    public void getCalendar() {
        //创建日历对象
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(new Date());
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }
}
