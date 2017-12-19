package com.wj.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.wj.test.ObserverModel.ChildObservable;
import com.wj.test.ObserverModel.ChildObserver;
import com.wj.test.R;
import com.wj.test.util.NdkHelper;
import com.wj.test.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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
    @BindView(R.id.button7)
    Button button7;
    @BindView(R.id.button8)
    Button button8;
    @BindView(R.id.button9)
    Button button9;
    @BindView(R.id.button10)
    Button button10;
    @BindView(R.id.button11)
    Button button11;
    @BindView(R.id.button12)
    Button button12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setTranslucent(this, 150);//设置状态栏透明度
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11, R.id.button12})
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
            case R.id.button7:
                startActivity(new Intent(MainActivity.this, SpannableStringActivity.class));
                break;
            case R.id.button8:
                startActivity(new Intent(MainActivity.this, AnimationActivity.class));

                //方法一
//                try {
//                    File file = new File("/sdcard/debug.keystore");
//                    FileInputStream fis = new FileInputStream(file);
//                    MessageDigest md = MessageDigest.getInstance("MD5");
//                    byte[] buffer = new byte[1024];
//                    int length = -1;
//                    while ((length = fis.read(buffer, 0, 1024)) != -1) {
//                        md.update(buffer, 0, length);
//                    }
//                    BigInteger bigInt = new BigInteger(1, md.digest());
//                    System.out.println("文件md5值：" + bigInt.toString(16));
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (NoSuchAlgorithmException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                //方法二
//                File file = new File("/sdcard/debug.keystore");
//                System.out.println("文件md5值：" +    MD5Util.getFileMD5String(file));

                ChildObserver observer = new ChildObserver();
                final ChildObservable observable = new ChildObservable();
                observable.add(observer);//类似于setOnClickListen
                observable.notifyObservers("新的状态");//类似于onclick
                break;
            case R.id.button9:
                //第一步：创建Observable
                final Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {//方法一
                    @Override
                    public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                        observableEmitter.onNext("大宝剑");
                        observableEmitter.onNext("夜店");
                        observableEmitter.onComplete();
                    }
                });
                Observable<String> just = Observable.just("男", "女");//方法二
                Observable<String> fromArray = Observable.fromArray("上天", "入地");//方法三
                Observable<String> fromCallable = Observable.fromCallable(new Callable<String>() {//方法四
                    @Override
                    public String call() throws Exception {
                        return "狐狸精";//只能传一个事件
                    }
                });

                //第二步：创建observer
                Observer<String> stringObserver = new Observer<String>() {
                    Disposable d;

                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        d = disposable;
                        Log.e("onSubscribe=", "onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        if ("大宝剑".equals(s)) {
                            d.dispose();//满足某个条件就取消订阅
                        }
                        Log.e("onNext=", s);
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        Log.e("onError=", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete=", "onComplete");
                    }
                };
                //第三步：订阅
                stringObservable.subscribe(stringObserver);
                just.subscribe(stringObserver);
                fromArray.subscribe(stringObserver);
                fromCallable.subscribe(stringObserver);
                stringObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("accept=", s);//对应onNext
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("accept=", "onError");//对应onError
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e("accept=", "onComplete");//对应onComplete
                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.e("accept=", "onSubscribe");//对应onSubscribe
                    }
                });
                //map可以用来类型转换
                Observable.just("11").map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(@NonNull String s) throws Exception {
                        int i = Integer.parseInt(s);
                        return i;
                    }
                }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        String s = "转换了=" + integer;
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });
                //flatmap返回一个observable
                Observable.just("1111").flatMap(new Function<String, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(@NonNull String s) throws Exception {
                        int i = Integer.parseInt(s) + 1;
                        return Observable.just(i);
                    }
                }).flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                        String s = "第三次转换为：" + integer;
                        return Observable.just(s);
                    }
                }).subscribe(new Consumer<String>() {//只要订阅了即subscribe，即使没有观察者被观察者的方法也会执行
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.button10:
                startActivity(new Intent(MainActivity.this, CircleViewActivity.class));
                break;
            case R.id.button11:
                Toast.makeText(this, NdkHelper.GetStringFromC("这句话"), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button12:
                startActivity(new Intent(MainActivity.this, CustomWidgetActivity.class));
                break;
        }
    }

    static class Cashier {
        public String userId;
        public String userName;

        public Cashier(String userName, String userId) {
            this.userName = userName;
            this.userId = userId;
        }


        @Override
        public String toString() {
            return userName;
        }
    }

    /**
     * Spinner
     */
    private void initSpinner() {
        ArrayList<Cashier> cashierList = new ArrayList<>();
        Spinner sp_cashier = (Spinner) findViewById(R.id.sp_cashier);
        cashierList.add(new Cashier("1", "1"));
        cashierList.add(new Cashier("2", "2"));
        cashierList.add(new Cashier("3", "3"));
        ArrayAdapter<Cashier> adapter = new ArrayAdapter<>(this, R.layout.spinner_simple_item, cashierList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_cashier.setAdapter(adapter);
        sp_cashier.setSelection(2);
    }
}
