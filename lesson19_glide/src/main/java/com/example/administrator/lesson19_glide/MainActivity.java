package com.example.administrator.lesson19_glide;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        //默认做了 压缩  RGB565
        Glide.with(this).load("http://p11.aipai.com/photo/935/31233935/photo/213/2904277/2904277_normal.jpg")
                .into(iv).onLoadStarted(new ColorDrawable(Color.BLUE));
    }
}
