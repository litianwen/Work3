package com.example.administrator.lesson21_sensormanager;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/11/21.
 */

public class OrientActivity extends AppCompatActivity {

    SensorManager manager;
    Sensor sensor;
    ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iv = new ImageView(this);
        iv.setPadding(10, 10, 10, 10);
        iv.setImageResource(R.mipmap.compass);
        setContentView(iv);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //方向
        sensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        if (sensor == null)
            finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        manager.registerListener(listener, sensor, SensorManager.SENSOR_STATUS_ACCURACY_HIGH);
    }

    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            float x = values[0];
//            float y = values[1];
//            float z = values[2];
            Log.e("TAG", "------------" + x);
            iv.startAnimation(Rotate(360 - x));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    float oldx = 0;

    //旋转动画
    public Animation Rotate(float x) {
        Animation anim = new RotateAnimation(oldx, x, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(500);
        anim.setFillAfter(true);
        oldx = x;
        return anim;
    }

    @Override
    protected void onStop() {
        super.onStop();
        manager.unregisterListener(listener);
    }
}
