package com.wj.test.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wj.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button9)
    Button button9;
    @BindView(R.id.button10)
    Button button10;
    @BindView(R.id.button11)
    Button button11;
    @BindView(R.id.button12)
    Button button12;
    @BindView(R.id.image1)
    ImageView image1;
    private static final float translation = 50;//平移距离
    private static final long duration = 100L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.button1, R.id.button9, R.id.button10, R.id.button11, R.id.button12})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                button9.setVisibility(View.VISIBLE);
                button10.setVisibility(View.VISIBLE);
                button11.setVisibility(View.VISIBLE);
                button12.setVisibility(View.VISIBLE);
                break;
            case R.id.button9:
//                image1.animate().translationYBy(-translation).setDuration(100);
                ObjectAnimator translationyby = ObjectAnimator.ofFloat(image1, "translationY", -100f);
                translationyby.start();
                break;
            case R.id.button10:
                image1.animate().translationYBy(translation).setDuration(100);
                break;
            case R.id.button11:
                image1.animate().translationXBy(-translation).setDuration(100);
                break;
            case R.id.button12:
                image1.animate().translationXBy(translation).setDuration(100).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        Log.e("button12", "右");
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
//                      animation.removeAllListeners();//这个不可以
                        image1.animate().setListener(null);//如果不溢出监听，会导致其他未设置监听的动画也会走这个监听，因为ViewPropertyAnimator是单例
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                break;
        }
    }
}
