package com.example.administrator.lesson19_xutilsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
    }

    public void net(View v) {
        //初始化后
        //请求必须在主线程
        RequestParams params = new RequestParams("http://route.showapi.com/26-4");
        //带参
        params.addBodyParameter("showapi_appid", "13074");
        params.addBodyParameter("showapi_sign", "ea5b4bf2e140498bb772d1bf2a51a7a0");

//        //如果服务器需要的是json  application/json
//        params.setAsJsonContent(true);
//        params.setBodyContent("");//json字符串);

        x.http().get(params, new Callback.CommonCallback<String>() {//String JSONObject JSONArray File  byte[]

            @Override
            public void onSuccess(String result) {
                //请求成功
                //主线程
                Log.e("TAG", "result:  " + result);
                //拿到JSON去转换对象
                Gson gson = new Gson();
                ImageVerify ive = gson.fromJson(result, ImageVerify.class);
                String image = ive.getShowapi_res_body().getImg_path();
                //绑定一个网络地址
                x.image().bind(iv, image);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //客户端错误
                //并没有请求到服务器就报错了

                //请求完毕后，onSuccess报错了
            }

            @Override
            public void onCancelled(CancelledException cex) {
                //用户取消了
            }

            @Override
            public void onFinished() {
                //完成，必须调用的方法，不管报不报错
            }
        });

    }


    public void download(View v) {
        RequestParams params = new RequestParams("http://skycnxz1.wy119.com/BatchXLS37.exe");
//        x.http().get(params, new Callback.CommonCallback<File>() {
//            @Override
//            public void onSuccess(File result) {
//                Log.e("TAG", "----" + result.getAbsolutePath());
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
        params.setCacheDirName("xxx");
        x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                Log.e("TAG", "---->" + current + "     " + total);
            }

            @Override
            public void onSuccess(File result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}
