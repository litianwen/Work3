package com.example.administrator.lesson19_okhttpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void net1(View v) {
        //可以通过构造方法来使用
        //缓存的请求的数据
        //Cache questCache = new Cache(new File("cache"), 1024);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                //.cache(questCache)
                .build();
        //默认是get请求
        Request request = new Request.Builder()
                .url("https://www.baidu.com").build();

        //call就是我们可以执行的请求类
        Call call = client.newCall(request);
        //异步方法
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //成功
                //子线程
                //main thread1
                Log.e("TAG", Thread.currentThread().getName() + "结果  " + response.body().string());
            }
        });
//        call.cancel();

//
//        //同步方法
//        try {
//            Response execute = call.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void net2(View v) {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                //.cache(questCache)
                .build();
        //post请求
        //键值对
        RequestBody body = new FormBody.Builder()
                .add("showapi_appid", "13074")
                .add("showapi_sign", "ea5b4bf2e140498bb772d1bf2a51a7a0").build();
        Request request = new Request.Builder()
                .url("http://route.showapi.com/341-3")
                .post(body).build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("TAG", "-------------->>>" + response.body().string());
            }
        });

    }

    public void net3(View v) {

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                //.cache(questCache)
                .build();
        //
        RequestBody fileBody = MultipartBody.create(MediaType.parse("application/octet-stream"), new File("上传的文件地址"));
        RequestBody body = new MultipartBody.Builder()
                .addFormDataPart("image", "name", fileBody)
                .build();

        Request request = new Request.Builder()
                .url("")
                .post(body)
                .build();
        Call call = client.newCall(request);
    }
}
