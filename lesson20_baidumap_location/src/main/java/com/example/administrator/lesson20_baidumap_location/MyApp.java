package com.example.administrator.lesson20_baidumap_location;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by Administrator on 2016/11/19.
 */

public class MyApp extends Application {

    //单例 全局共享数据


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化操作
        SDKInitializer.initialize(this);
    }
}
