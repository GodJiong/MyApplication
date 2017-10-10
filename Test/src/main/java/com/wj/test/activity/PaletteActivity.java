package com.wj.test.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wj.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaletteActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_girl)
    ImageView ivGirl;
    @BindView(R.id.item1)
    TextView item1;
    @BindView(R.id.item2)
    TextView item2;
    @BindView(R.id.item3)
    TextView item3;
    @BindView(R.id.item4)
    TextView item4;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        ButterKnife.bind(this);
        toolbar.setTitle("划出侧滑菜单");
    }

    @OnClick({R.id.item1, R.id.item2, R.id.item3, R.id.item4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item1:
                palette(R.drawable.spring);//根据传入的资源id给UI界面调色
                Toast.makeText(PaletteActivity.this, "spring", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2:
                palette(R.drawable.summer);
                Toast.makeText(PaletteActivity.this, "summer", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item3:
                palette(R.drawable.autumn);
                Toast.makeText(PaletteActivity.this, "autumn", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item4:
                palette(R.drawable.winter);
                Toast.makeText(PaletteActivity.this, "winter", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 根据传入的资源id给UI界面调色
     * @param id 资源id
     */
    private void palette(int id) {
        ivGirl.setBackgroundResource(id);//将主布局图片设置为对应的季节图片
        //根据id得到一个bitmap对象
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
        //同步方法，因为处理图片比较耗时，所以可能会阻塞主线程
//        Palette palette = Palette.from(bitmap).generate();
        //异步方法
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //返回一个 Swatch 样本对象，这个样本对象是Palette的一个内部类，它提供了一些获取最终颜色的方法。
                Palette.Swatch swatch = palette.getVibrantSwatch();//获取暗的活力颜色（提取的6种色调中的某一个）
                if (swatch != null) {//Palette的回调颜色中，其中 获得活力颜色（getVibrantSwatch()），是全部都可以返回值的，而其他的颜色对象，可能会返回null，不进行判断将会出现空指针异常。其次，还有获得亮活力颜色（getLightVibrantSwatch）返回null的几率小于其它返回的颜色，当然除了上面说的活力颜色。
                    int rgb = swatch.getRgb();//颜色的RBG值
                    int titleTextColor = swatch.getTitleTextColor();//标题文字的颜色值
                    int bodyTextColor = swatch.getBodyTextColor();//主体文字的颜色值
                    float[] hsl = swatch.getHsl();//颜色的HSL值
                    toolbar.setTitleTextColor(titleTextColor);
                    if (Build.VERSION.SDK_INT >= 21) {
                        Window window = getWindow();
                        window.setStatusBarColor(rgb);//顶部状态栏（要求API>=21）
                        window.setNavigationBarColor(rgb);//底部导航栏（要求API>=21）
                        toolbar.setBackgroundColor(rgb);//标题栏
                        drawerLayout.closeDrawer(Gravity.START);//关闭侧滑菜单
                    }
                }
            }
        });
    }
}
