package com.example.administrator.lesson19_xutilsdemo2.config;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * Created by Administrator on 2016/11/16.
 */

public class MyApp extends Application {


    private static MyApp instance;

    public static MyApp getInstance() {

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        instance = this;
    }

    public DbManager getDbManager() {
        DbManager.DaoConfig config = new DbManager.DaoConfig();
        config.setDbName("bsbdqj.db");
        return x.getDb(config);
    }


}
