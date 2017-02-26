package com.example.administrator.mvc.modle;

import android.util.Log;

import com.example.administrator.mvc.bean.CangTouShiBean;
import com.example.administrator.mvc.callback.BeanCallback;
import com.example.administrator.mvc.interfaces.ICangTouShi;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/22.
 */

public class CangTouShiModle implements ICangTouShi {


    @Override
    public void doquest(String num, String type, String yayuntype, String key, final BeanCallback<CangTouShiBean> callback) {
        //请求数据
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("showapi_appid", "13074")
                .add("showapi_sign", "ea5b4bf2e140498bb772d1bf2a51a7a0")
                .add("num", num)
                .add("type", type)
                .add("yayuntype", yayuntype)
                .add("key", key)
                .build();
        Request request = new Request.Builder().url("http://route.showapi.com/950-1")
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "--------------" + e.getMessage());
                callback.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //  Log.e("TAG", "-------------->>" + response.body().string());
                String json = response.body().string();
                Gson gson = new Gson();
                CangTouShiBean bean = gson.fromJson(json, CangTouShiBean.class);
                callback.onSuccess(bean);
            }
        });

    }
}
