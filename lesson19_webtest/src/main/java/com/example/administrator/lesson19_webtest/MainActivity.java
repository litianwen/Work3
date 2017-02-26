package com.example.administrator.lesson19_webtest;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    EditText et_username, et_password, et_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        et_age = (EditText) findViewById(R.id.et_age);
    }

    public void register(View v) {

        RequestParams params = new RequestParams("http://192.168.18.250:8080/MyServer/Register");
        params.addBodyParameter("username", et_username.getText().toString());
        params.addBodyParameter("password", et_password.getText().toString());
        params.addBodyParameter("age", et_age.getText().toString());

        params.addBodyParameter("img",new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM/Camera/IMG_20161011_164152.jpg"));

        x.http().post(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                Log.e("TAG", "---" + result.toString());
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
