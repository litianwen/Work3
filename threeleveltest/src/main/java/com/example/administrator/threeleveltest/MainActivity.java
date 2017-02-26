package com.example.administrator.threeleveltest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.mylib.utils.ImageLoader;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    ImageLoader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        loader = new ImageLoader(this, "test");
    }

    public void start(View v) {
        loader.loadImage("https://www.2345.com/i/banner1_20161005.jpg", new ImageLoader.ImageLoadListener() {
            @Override
            public void loadImage(Bitmap bmp) {
                iv.setImageBitmap(bmp);
            }
        });
    }
}
