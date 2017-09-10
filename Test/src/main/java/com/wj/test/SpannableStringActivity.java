package com.wj.test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpannableStringActivity extends AppCompatActivity {

    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.text5)
    TextView text5;
    @BindView(R.id.text6)
    TextView text6;
    @BindView(R.id.text7)
    TextView text7;
    @BindView(R.id.text8)
    TextView text8;
    @BindView(R.id.text9)
    TextView text9;
    @BindView(R.id.text10)
    TextView text10;
    @BindView(R.id.text11)
    TextView text11;
    @BindView(R.id.text12)
    TextView text12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_string);
        ButterKnife.bind(this);
        foregroundColorSpan();//设置文字颜色（前景色）
        backgroundColorSpan();//设置文字背景色
        relativeSizeSpan();//设置文字相对大小
        strikeThroughSpan();//设置文字删除线
        underlineSpan();//设置文字下划线
        superscriptSpan();//设置文字上标
        subscriptSpan();//设置文字下标
        StyleSpan();//设置文字风格（粗体，斜体，粗斜体）
        imageSpan();//设置文本图片
        clickableSpan();//设置文本可点击
        uRLSpan();//设置文本超链接
        spannableStringBuilder();//实现字符串拼接
    }

    /**
     * 设置文字颜色（前景色）
     */
    private void foregroundColorSpan() {
        SpannableString spannableString = new SpannableString("设置文本文字颜色为红色");
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FF1D25"));
        spannableString.setSpan(foregroundColorSpan, 9, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        text1.setText(spannableString);
    }

    /**
     * 设置文字背景色
     */
    private void backgroundColorSpan() {
        SpannableString spannableString = new SpannableString("设置文本文字背景色为红色");
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.parseColor("#FF1D25"));
        spannableString.setSpan(backgroundColorSpan, 10, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        text2.setText(spannableString);
    }

    /**
     * 设置文字相对大小
     */
    private void relativeSizeSpan() {
        SpannableString spannableString = new SpannableString("设置文本字体变大变小");
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(1.5f);
        RelativeSizeSpan relativeSizeSpan1 = new RelativeSizeSpan(0.5f);
        spannableString.setSpan(relativeSizeSpan, 6, 8, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(relativeSizeSpan1, 8, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        text3.setText(spannableString);
    }

    /**
     * 设置文字删除线
     */
    private void strikeThroughSpan() {
        SpannableString spannableString = new SpannableString("设置文本文字有删除线");
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, 7, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        text4.setText(spannableString);
    }

    /**
     * 设置文字下划线
     */
    private void underlineSpan() {
        SpannableString spannableString = new SpannableString("设置文本文字有下划线");
        UnderlineSpan underlineSpan = new UnderlineSpan();
        spannableString.setSpan(underlineSpan, 7, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        text5.setText(spannableString);
    }

    /**
     * 设置文字上标
     */
    private void superscriptSpan() {
        SpannableString spannableString = new SpannableString("设置文本文字有上标");
        SuperscriptSpan superscriptSpan = new SuperscriptSpan();
        spannableString.setSpan(superscriptSpan, 7, spannableString.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        text6.setText(spannableString);
    }

    /**
     * 设置文字下标
     */
    private void subscriptSpan() {
        SpannableString spannableString = new SpannableString("设置文本文字有下标");
        SubscriptSpan subscriptSpan = new SubscriptSpan();
        spannableString.setSpan(subscriptSpan, 7, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        text7.setText(spannableString);
    }

    /**
     * 设置文字风格（粗体，斜体，粗斜体）
     */
    private void StyleSpan() {
        SpannableString spannableString = new SpannableString("设置文本文字粗体、斜体、粗斜体");
        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
        StyleSpan styleSpan1 = new StyleSpan(Typeface.ITALIC);
        StyleSpan styleSpan2 = new StyleSpan(Typeface.BOLD_ITALIC);
        spannableString.setSpan(styleSpan, 6, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(styleSpan1, 9, 11, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(styleSpan2, 12, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        text8.setText(spannableString);
    }

    /**
     * 设置文本图片
     */
    private void imageSpan() {
        SpannableString spannableString = new SpannableString("设置文本文字有图片");
        //为了适配5.1以上系统,官方新提供了这么一个ContextCompat类来替换 getResources().getDrawable()
        Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_launcher_round);
        drawable.setBounds(0, 0, 65, 65);//设置范围
        ImageSpan imageSpan = new ImageSpan(drawable);
        spannableString.setSpan(imageSpan, 7, spannableString.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        text9.setText(spannableString);
    }

    /**
     * 设置文本可点击
     */
    private void clickableSpan() {
        SpannableString spannableString = new SpannableString("设置文本文字可点击");
        MyClickableSpan myClickableSpan = new MyClickableSpan();
        spannableString.setSpan(myClickableSpan, 6, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        text10.setMovementMethod(LinkMovementMethod.getInstance());//不设置点击无响应
        text10.setHighlightColor(Color.parseColor("#FF1D25"));//设置点击时背景色。
        text10.setText(spannableString);
    }

    /**
     * 设置文本超链接
     */
    private void uRLSpan() {
        SpannableString spannableString = new SpannableString("设置文本超链接");
        MyURLSpan urlSpan = new MyURLSpan("https://www.baidu.com");
        spannableString.setSpan(urlSpan, 4, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        text11.setMovementMethod(new LinkMovementMethod());//不设置点击无响应
        text11.setHighlightColor(Color.parseColor("#FF1D25"));//设置点击时背景色
        text11.setText(spannableString);
    }

    /**
     * 实现字符串拼接
     */
    private void spannableStringBuilder() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append("第一句变大");
        spannableStringBuilder.append("第二句粗体");
        spannableStringBuilder.append("第三句字体为红色");
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(1.8f);
        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FF1D25"));
        spannableStringBuilder.setSpan(relativeSizeSpan, 3, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.setSpan(styleSpan, 8, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.setSpan(foregroundColorSpan, 16, spannableStringBuilder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        text12.setText(spannableStringBuilder);
    }

    /**
     * 点击事件
     */
    private class MyClickableSpan extends ClickableSpan {
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(true);//控制是否让可点击文本显示下划线
            ds.setColor(Color.GRAY);//设置字体颜色
        }

        @Override
        public void onClick(View widget) {
            Intent intent = new Intent(SpannableStringActivity.this, BottomNavigationViewActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 超链接
     */
    @SuppressLint("ParcelCreator")
    private class MyURLSpan extends URLSpan {
        MyURLSpan(String url) {
            super(url);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);//控制是否让可点击文本显示下划线
            ds.setColor(Color.BLACK);//设置字体颜色
        }
    }
}
