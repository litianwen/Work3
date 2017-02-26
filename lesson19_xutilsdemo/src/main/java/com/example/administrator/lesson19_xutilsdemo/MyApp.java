package com.example.administrator.lesson19_xutilsdemo;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2016/11/16.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
