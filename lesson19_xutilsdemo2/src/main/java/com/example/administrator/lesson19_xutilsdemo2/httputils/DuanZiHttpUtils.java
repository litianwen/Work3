package com.example.administrator.lesson19_xutilsdemo2.httputils;

import com.example.administrator.lesson19_xutilsdemo2.DuanDao;
import com.example.administrator.lesson19_xutilsdemo2.base.BeanCallback;
import com.example.administrator.lesson19_xutilsdemo2.base.HttpCallback;
import com.example.administrator.lesson19_xutilsdemo2.bean.DuanZiBean;
import com.example.administrator.lesson19_xutilsdemo2.params.DuanziParams;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;

import java.util.List;


/**
 * Created by Administrator on 2016/11/16.
 */

public class DuanZiHttpUtils {

    //所有的请求都在这里
    //获取所有数据
    public static final void getDuanZiFromPager(int page, final BeanCallback<List<DuanZiBean>> callback) {

        x.http().post(new DuanziParams(page), new HttpCallback() {
            @Override
            public void onSuccess(String result) {
                //手动解析前面的
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject showapi_res_body = jsonObject.optJSONObject("showapi_res_body");
                JSONObject pagebean = showapi_res_body.optJSONObject("pagebean");
                JSONArray contentlist = pagebean.optJSONArray("contentlist");
                Gson gson = new Gson();
                //解析array成集合
                List<DuanZiBean> list = gson.fromJson(contentlist.toString(), new TypeToken<List<DuanZiBean>>() {
                }.getType());
                //将数据缓存在本地
                //如果存在就不管了
                DuanDao.saveList(list);
                callback.onSuccess(list);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //没有网络
                callback.onError("网络连接失败");
            }

            @Override
            public void onFinished() {
                callback.onFinish();
            }
        });
    }

}
