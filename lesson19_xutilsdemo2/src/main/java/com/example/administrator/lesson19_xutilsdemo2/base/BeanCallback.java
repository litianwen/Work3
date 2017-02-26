package com.example.administrator.lesson19_xutilsdemo2.base;

/**
 * Created by Administrator on 2016/11/16.
 */

public interface BeanCallback<T> {

    public void onSuccess(T t);

    public void onFinish();

    public void onError(String msg);
}
