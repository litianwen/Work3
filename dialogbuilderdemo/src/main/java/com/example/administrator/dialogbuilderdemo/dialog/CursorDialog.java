package com.example.administrator.dialogbuilderdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import com.example.administrator.dialogbuilderdemo.R;

/**
 * Created by Administrator on 2016/11/22.
 */

public class CursorDialog extends Dialog {


    private CursorDialog(Context context) {
        super(context);
    }

    private CursorDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    public void show() {
        super.show();
    }

    public void show(final Runnable run, final onLoadDataListener listener) {
        super.show();
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                listener.onLoadDataFinish();
            }
        };
        new Thread() {
            @Override
            public void run() {
                run.run();
                handler.sendEmptyMessage(1);
            }
        }.start();
    }

    public interface onLoadDataListener {
        public void onLoadDataFinish();
    }


    public static class Builder {

        private Context mContext;
        private String title;
        private boolean isFull;
        private boolean isFloating;
        private View view;

        private int width, height;


        public Builder setWidthAndHeight(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder floating() {
            this.isFloating = true;
            return this;
        }


        public Builder full() {
            //全屏
            this.isFull = true;
            return this;
        }

        public Builder setView(View layout) {
            this.view = layout;
            return this;
        }

        public Builder setView(int layout) {
            this.view = View.inflate(mContext, layout, null);
            return this;
        }


        public CursorDialog builder() {
            CursorDialog dialog;
            if (TextUtils.isEmpty(title)) {
                if (isFull)
                    dialog = new CursorDialog(mContext, R.style.CursorDialogThemeFullNotTitle);
                else if (isFloating)
                    dialog = new CursorDialog(mContext, R.style.CursorDialogThemeNotFloatingNotTitle);
                else
                    dialog = new CursorDialog(mContext, R.style.CursorNotTitle);
            } else {
                if (isFull)
                    dialog = new CursorDialog(mContext, R.style.CursorDialogThemeFull);
                else if (isFloating)
                    dialog = new CursorDialog(mContext, R.style.CursorDialogThemeNotFloating);
                else
                    dialog = new CursorDialog(mContext);
                dialog.setTitle(title);
            }
            if (width != 0 && height != 0) {
                //设置宽高
            }
            dialog.setContentView(view);
            return dialog;
        }
    }

}
