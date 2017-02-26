package com.example.administrator.mylib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.LruCache;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/11/17.
 */

public class ImageLoader {

    //下载使用线程池
    ExecutorService threadLooper;


    //缓存类
    private static LruCache<String, Bitmap> cache;
    private FileUtils fileUtils;

    public ImageLoader(Context context, String dirName) {
        //获取系统分配的最大内存
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        cache = new LruCache<String, Bitmap>(maxSize) {
            //每一个键所对应的值的大小
            //自动释放低频率的文件
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
        fileUtils = new FileUtils(context, dirName);
        threadLooper = Executors.newFixedThreadPool(5);
    }

    //存储
    private void saveToCache(String key, Bitmap bmp) {
        cache.put(key, bmp);
    }

    //从缓存读取
    private Bitmap readFromCache(String key) {
        return cache.get(key);
    }


    public void loadImage(final String url, @NonNull final ImageLoadListener listener) {
        final String key = url.replaceAll("[\\W]", "");
        //第一级
        if (readFromCache(key) != null) {
            //直接拿出来
            LogUtils.e("从缓存中加载");
            listener.loadImage(readFromCache(key));
        } else {
            final Bitmap bitmap = fileUtils.readFromSDCard(key);
            if (bitmap != null) {
                //存储到缓存
                LogUtils.e("从SDCard中加载");
                saveToCache(key, bitmap);
                //返回
                listener.loadImage(fileUtils.readFromSDCard(key));
            } else {
                //下载
                final Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        LogUtils.e("从网络下载");
                        listener.loadImage((Bitmap) msg.obj);
                    }
                };

                threadLooper.execute(new Runnable() {
                    @Override
                    public void run() {
                        //开始下载
                        try {
                            URL u = new URL(url);
                            InputStream inputStream = u.openStream();
                            Bitmap bitmap1 = BitmapFactory.decodeStream(inputStream);
                            fileUtils.saveToSDCard(key, bitmap1);
                            saveToCache(key, bitmap1);

                            Message msg = handler.obtainMessage();
                            msg.obj = bitmap1;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }

    }

    public void cancelDownLoad() {
        threadLooper.shutdown();
    }


    public interface ImageLoadListener {
        public void loadImage(Bitmap bmp);
    }
}
