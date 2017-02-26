package com.example.administrator.mylib.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/4.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void jumpActivity(Class cls) {
        startActivity(new Intent(this, cls));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        //getIntentData();
        initView();
    }

    protected abstract void initView();

    protected abstract int getLayoutRes();

    /**
     * 获取前一个Activity携带的数据的方法
     */
    // protected abstract void getIntentData(); //在跳转activity获取数据
}
