package com.example.administrator.dialogbuilderdemo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.dialogbuilderdemo.dialog.CursorDialog;

public class MainActivity extends AppCompatActivity implements CursorDialog.onLoadDataListener, Runnable {
    CursorDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new CursorDialog.Builder(this)
                .full()
                .setView(R.layout.dialoglayout)
                .builder();
        dialog.show(this, this);

    }

    @Override
    public void onLoadDataFinish() {
        dialog.dismiss();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
