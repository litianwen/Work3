package com.example.administrator.lesson19_xutilsdemo2.weiget;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.lesson19_xutilsdemo2.R;

import java.io.IOException;

/**
 * Created by Administrator on 2016/11/16.
 */

public class VideoPlayer extends RelativeLayout implements SurfaceHolder.Callback, View.OnClickListener {


    SurfaceView sv;//背景的视频
    ImageView iv;//停止后显示播放的按钮
    MediaPlayer player;
    String url;

    public VideoPlayer(Context context, String url) {
        super(context);
        this.url = url;
        sv = new SurfaceView(getContext());
        iv = new ImageView(getContext());
        this.addView(sv);
        this.addView(iv);
        iv.setImageResource(R.mipmap.player);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //为相对布局设置属性
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        iv.setLayoutParams(params);
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().widthPixels);
        sv.setLayoutParams(params2);
        sv.setOnClickListener(this);
        Log.e("TAG", "-------初始化组件");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        sv.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e("TAG", "----------------------界面创建");
        player = new MediaPlayer();
        player.setDisplay(holder);
        try {
            player.setDataSource(getContext(), Uri.parse(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.prepareAsync();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        player.start();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        player.pause();
                    }
                }.start();

            }
        });
        //    player.setDisplay(holder);
        //  player.start();
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                player.pause();
//            }
//        }.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (player != null) {
            if (player.isPlaying()) {
                player.stop();
            }
            player.release();
        }
        player = null;
    }

    @Override
    public void onClick(View v) {
        if (player.isPlaying()) {
            iv.setVisibility(View.VISIBLE);
            player.pause();
        } else {
            iv.setVisibility(View.GONE);
            player.start();
        }
    }
}
