package com.example.administrator.lesson19_xutilsdemo2;

import com.example.administrator.lesson19_xutilsdemo2.bean.DuanZiBean;
import com.example.administrator.lesson19_xutilsdemo2.config.MyApp;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */

public class DuanDao {
    //存储
    public static final void saveList(List<DuanZiBean> list) {
        //如果不存在就存储，存在就不管了
        DbManager manager = MyApp.getInstance().getDbManager();
        for (DuanZiBean duanZiBean : list) {
            try {
                manager.save(duanZiBean);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
        try {
            manager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static final List<DuanZiBean> getAllDuanZi() {
        DbManager manager = MyApp.getInstance().getDbManager();
        List<DuanZiBean> list = null;
        try {
            list = manager.findAll(DuanZiBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        try {
            manager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
