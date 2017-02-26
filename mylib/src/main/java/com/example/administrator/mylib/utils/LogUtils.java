package com.example.administrator.mylib.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/11/8.
 */

public class LogUtils {

    //各种打印
    public static final boolean DEBUG = true;


    public static void e(Object o) {
        if (DEBUG)
            Log.e("TAG", "打印：------      " + o.toString());
    }

    public static void e(String o) {
        if (DEBUG)
            Log.e("TAG", "打印：------      " + o.toString());
    }

    public  static void e(int i) {
        if (DEBUG)
            Log.e("TAG", "打印：------      " + i);
    }

    public static void e(float i) {
        if (DEBUG)
            Log.e("TAG", "打印：------      " + i);
    }

    public static void e(boolean b) {
        if (DEBUG)
            Log.e("TAG", "打印：------      " + b);
    }

}
