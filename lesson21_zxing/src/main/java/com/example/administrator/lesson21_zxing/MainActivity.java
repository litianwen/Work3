package com.example.administrator.lesson21_zxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.EncodingUtils;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        //生成二维码
    }

    public void create(View v) {
        try {
            //http://
            Bitmap bitmap = EncodingUtils.createQRCode("http://www.baidu.com", 800);
            iv.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void start(View v) {
        startActivityForResult(new Intent(this, CaptureActivity.class), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String result = data.getStringExtra("result");
            //逻辑处理
            //判断前缀http:// pay://............
            //设计一套规则
            // 好友id
            if (result.startsWith("http://")) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(result));
                startActivity(intent);
            } else if (result.startsWith("firend://")) {
                //app加好友的方法
                //调用服务器接口
            } else if (result.startsWith("pay://")) {
                //启动支付宝
            }
            // firend://username
            //1 个app

        }
    }
}
