package com.example.administrator.mylib.weiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.administrator.mylib.R;
import com.example.administrator.mylib.utils.ImageLoader;

/**
 * Created by Administrator on 2016/11/17.
 */

public class NetWorkImageView extends ImageView {

    ImageLoader loader;

    String url;

    public NetWorkImageView(Context context) {
        super(context);
        loader = new ImageLoader(getContext(), "network");
    }

    public NetWorkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NetWorkImageView);
        url = a.getString(R.styleable.NetWorkImageView_url);
        a.recycle();
        loader = new ImageLoader(getContext(), "network");
        if (url != null) {
            setUrl(url);
        }
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        //设置到图片上去
        loader.loadImage(url, new ImageLoader.ImageLoadListener() {
            @Override
            public void loadImage(Bitmap bmp) {
                setImageBitmap(bmp);
            }
        });
    }
}
