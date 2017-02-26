package com.example.administrator.mvc.callback;

/**
 * Created by Administrator on 2016/11/22.
 */

public interface BeanCallback<T> {

    public void onSuccess(T t);

    public void onError(String msg);

}
