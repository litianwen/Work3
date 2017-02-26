package com.example.administrator.mvc.view;

/**
 * Created by Administrator on 2016/11/22.
 */

/**
 * 定义所有 View上面的操作
 */
public interface IMvpView {

    public String getNum();

    public String getType();

    public String getYY();

    public String getKey();

    public void showDialog();

    public void dismissDialog();

    public void setText(String text);

    public void showToast(String msg);
}
