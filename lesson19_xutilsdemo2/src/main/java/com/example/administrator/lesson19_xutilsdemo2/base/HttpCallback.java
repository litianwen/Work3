package com.example.administrator.lesson19_xutilsdemo2.base;

import android.util.Log;

import org.xutils.common.Callback;

/**
 * Created by Administrator on 2016/11/16.
 */

public abstract class HttpCallback implements Callback.CommonCallback<String> {


//    @Override
//    public void onError(Throwable ex, boolean isOnCallback) {
//        Log.e("TAG", "---->" + ex.getMessage());
//    }

    @Override
    public void onCancelled(CancelledException cex) {
        Log.e("TAG", "---->用户取消");
    }

}
