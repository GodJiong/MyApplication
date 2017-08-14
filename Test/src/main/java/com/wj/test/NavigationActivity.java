package com.wj.test;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by wj on 2017/8/10 23:17
 */
public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;//侧滑菜单
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);
        //初始化toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("侧滑菜单");
        setSupportActionBar(toolbar);//如果去掉那么Toolbar右侧的menu就没了

        //初始化DrawerLayout
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //创建一个Drawerlayout和Toolbar联动的开关
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //保证左上角导航键有特效的同时可以监听侧滑菜单状态，那么你需要写两遍监听。为了解决这个差点秃顶
        drawer.addDrawerListener(new MyDrawerListener());//监听抽屉的打开，关闭等状态（setDrawerListener方法是过期的不要用）
        drawer.addDrawerListener(toggle);//设置左上角导航键特效
        toggle.syncState();//初始化，标识绑定成功

        // 监听导航栏点击事件
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //获取某个menu中的item
//        MenuItem item=navigationView.getMenu().getItem(0);//获取某个menu
//        item.getItemId();//获取对应id
//        item.setChecked(true);//设置 item 选中状态
//        Toast.makeText(NavigationActivity.this, "id:"+id, Toast.LENGTH_SHORT).show();
        //设置导航栏头部布局的方法
//        View view = navigationView.getHeaderView(0);
//        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);//找到headerLayout中的图片
//        imageView.setBackgroundResource(R.drawable.a);//图片千万别设置太大，不然会很卡的

        //这也是MD风格的一个控件，江湖人称fab，一个浮动的具有立体感的按钮
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MD风格的控件Snackbar，比Toast高大上，装逼必备
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //可以手动打开侧滑菜单
        Button button = (Button) findViewById(R.id.openDrawer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start代表从左侧打开，要和xml布局中设置的一致，否则会报错 No drawer view found with gravity RIGHT
                //附上关闭侧滑菜单的代码 drawer.closeDrawer(Gravity.START);
                drawer.openDrawer(Gravity.START);
            }
        });
    }

    /*重写back返回事件，按下back，如果侧滑菜单处于打开状态，则back键先关闭菜单*/
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*加载Menu布局（右上角那三个点）*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_view, menu);
        return true;
    }

    /*响应右上角Menu菜单的点击事件*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(NavigationActivity.this, "settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_photos:
                Toast.makeText(NavigationActivity.this, "photos", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    /*监听侧滑菜单的每个item*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                Toast.makeText(NavigationActivity.this, "import", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_gallery:
                Toast.makeText(NavigationActivity.this, "gallery", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_slideshow:
                Toast.makeText(NavigationActivity.this, "slideshow", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_manage:
                Toast.makeText(NavigationActivity.this, "tools", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_share:
                Toast.makeText(NavigationActivity.this, "share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_send:
                Toast.makeText(NavigationActivity.this, "send", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START); //点击后关闭
        return true;
    }

    /**
     * 监听侧滑菜单状态
     */
    private class MyDrawerListener implements DrawerLayout.DrawerListener {

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            //这个就不演示了，只要打开抽屉会一直走这个方法
//            Toast.makeText(NavigationActivity.this, "onDrawerSlide", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            //打开状态
            Toast.makeText(NavigationActivity.this, "onDrawerOpened", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            //关闭状态
            Toast.makeText(NavigationActivity.this, "onDrawerClosed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            //无论关闭还是打开，这个方法都会分别走两次
//            Toast.makeText(NavigationActivity.this, "onDrawerStateChanged", Toast.LENGTH_SHORT).show();
        }
    }
}
