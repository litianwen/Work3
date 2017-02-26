package com.example.administrator.lesson19_xutilsdemo2.params;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

/**
 * Created by Administrator on 2016/11/16.
 */
@HttpRequest(host = "http://route.showapi.com", path = "255-1")
public class DuanziParams extends RequestParams {

    public DuanziParams(int pager) {
        //添加所有参数
        this.addBodyParameter("showapi_appid", "13074");
        this.addBodyParameter("showapi_sign", "ea5b4bf2e140498bb772d1bf2a51a7a0");
        this.addBodyParameter("page", pager + "");
    }

}
