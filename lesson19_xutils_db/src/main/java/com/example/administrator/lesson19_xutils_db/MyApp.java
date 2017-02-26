package com.example.administrator.lesson19_xutils_db;

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
        DbManager.DaoConfig daoconfig = new DbManager.DaoConfig();
        //默认在data/data/包名/database/数据库名称
//        daoconfig.setDbDir()
        daoconfig.setDbName("user.db");
//        daoconfig.setDbVersion(1);//默认1
        //通过manager进行增删改查
        return x.getDb(daoconfig);
    }
}
