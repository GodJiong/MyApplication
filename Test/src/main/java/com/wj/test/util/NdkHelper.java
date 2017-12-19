package com.wj.test.util;

/**
 * Created by wangjiong on 2017/10/18.
 */

public class NdkHelper {


    static {
        System.loadLibrary("HelloWorld");
    }

    public static native String GetStringFromC(String str);
}

