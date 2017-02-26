package com.example.administrator.lesson19_xutils_db;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void query(View v) {
        //查询所有

        DbManager manager = MyApp.getInstance().getDbManager();
        try {
            List<User> list = manager.findAll(User.class);
            Log.e("TAG", list.toString());
            //获取一条记录
//            manager.findById()
            //按条件查找
//            List<User> list2 = manager.selector(User.class).where("username", "=", "王五").findAll();


        } catch (DbException e) {
            e.printStackTrace();
        }

        try {
            manager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(View v) {

        DbManager manager = MyApp.getInstance().getDbManager();

        //ContentValues//SQlitedatabase insertinto
        KeyValue pairs = new KeyValue("age", "50");
        KeyValue pairs2 = new KeyValue("username", "赵六");
        try {
            manager.update(User.class, WhereBuilder.b("age", "<", "50"), pairs, pairs2);
        } catch (DbException e) {
            e.printStackTrace();
        }


        try {
            manager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(View v) {
        //获取数据库
        //数据库的名称，版本，路径
        DbManager manager = MyApp.getInstance().getDbManager();
        //存储
        User user = new User();
        user.setAge(1);
        user.setId(1);
        user.setPassword("123654");
        user.setSex("男12312");
        user.setUsername("王五1231");
        try {
            manager.save(user);
        } catch (DbException e) {
            e.printStackTrace();
        }
        //配合注解来告诉xutils怎么去建立表和字段
//
//        //如果不存在就存储，如果存在了，就修改 ,,修改也是根据id来的 id必须非自动增长
//        try {
//            //如果用户存在，抛出异常
//            manager.save(user); //如果id非自动增长 必须指定id，必须id和数据库中所有字段的id不一样
//        } catch (DbException e) {
//            e.printStackTrace();
//            try {
//                manager.saveOrUpdate(user);
//            } catch (DbException e1) {
//                e1.printStackTrace();
//            }
//        }

//        //如果不存在就存储，如果存在了，就修改 ,,修改也是根据id来的 id自动增长
//        try {
//            User user2 = manager.findById(User.class, user.getId());
//            if (user2 == null) {
//                manager.save(user);
//            } else {
//                manager.saveOrUpdate(user);
//            }
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
//        try {
//            manager.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void delete(View v) {
        DbManager manager = MyApp.getInstance().getDbManager();
        //删除整个表的所有数据
        //manager.delete(User.class);
        //删除指定id的记录
        //manager.deleteById(User.class,1);
        //删除某一类数据  where name="张三"
        try {
            manager.delete(User.class, WhereBuilder.b("username", "=", "王五").and("age", "<", "100"));
        } catch (DbException e) {
            e.printStackTrace();
        }
        try {
            manager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//        try {
//            //如果存在就修改，如果不存在，不处理
//            manager.saveOrUpdate(user);
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
}

//    /*
//     一般在MyApp
//     */
//    private DbManager getDbManager() {
//        DbManager.DaoConfig daoconfig = new DbManager.DaoConfig();
//        //默认在data/data/包名/database/数据库名称
////        daoconfig.setDbDir()
//        daoconfig.setDbName("user.db");
////        daoconfig.setDbVersion(1);//默认1
//        //通过manager进行增删改查
//        return x.getDb(daoconfig);
//    }
