package com.example.administrator.mvc.interfaces;

import com.example.administrator.mvc.bean.CangTouShiBean;
import com.example.administrator.mvc.callback.BeanCallback;

/**
 * Created by Administrator on 2016/11/22.
 */

public interface ICangTouShi {


    //请求数据
    public void doquest(String num, String type, String yayuntype, String key, BeanCallback<CangTouShiBean> callback);


}
