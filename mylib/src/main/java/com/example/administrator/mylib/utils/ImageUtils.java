package com.example.administrator.mylib.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/8.
 */

public class ImageUtils {
    //图片相关都丢在这里
    //三级缓存
    //图像裁剪，图像压缩，图像缩放。。。。

    //异步下载图片并且显示在ui上面
    public static final void bindView(String url, ImageView iv) {
        //线程池
        //三级缓存
        ImageAsyncTask task = new ImageAsyncTask(iv);
        task.execute(url);
    }


    static class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

        private ImageView iv;

        public ImageAsyncTask(ImageView iv) {
            this.iv = iv;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //下载
            try {
                URL url = new URL(params[0]);
                InputStream inputStream = url.openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                //开始绑定UI
                iv.setImageBitmap(bitmap);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setAdjustViewBounds(true);
            }
        }
    }
}
