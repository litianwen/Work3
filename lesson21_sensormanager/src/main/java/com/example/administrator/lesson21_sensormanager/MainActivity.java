package com.example.administrator.lesson21_sensormanager;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;
import static android.hardware.Sensor.TYPE_ALL;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    SensorManager manager;
    List<Sensor> sensors;
    Sensor sensor;

    SoundPool pool = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        initSensor();
        //获取一个加速度传感器
        sensor = manager.getDefaultSensor(TYPE_ACCELEROMETER);
        id = pool.load(this, R.raw.ringout, 100);

    }

    public void get(View v) {

    }

    //注册事件

    @Override
    protected void onStart() {
        super.onStart();
        //精准度 == 耗电量
        manager.registerListener(listener, sensor, SensorManager.SENSOR_STATUS_ACCURACY_LOW);
    }

    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //传感器改变时
            float[] values = event.values;
            float max = sensor.getMaximumRange();
            //0.8的最大值
            float yao = max / 5 * 4;
            // 3个数据
            float x = values[0];
            float y = values[1];
            float z = values[2];

            if (Math.abs(x) > yao || Math.abs(y) > yao) {
                pool.play(id, 1, 1, 0, 0, 1);
            }

            Log.e("TAG", "--------------" + max + "    " + x + "   " + y + "   " + z);
            List<String> list;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //当精准度改变时
        }
    };

    //解绑


    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterListener(listener);
    }

    private void initSensor() {
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //
        sensors = manager.getSensorList(TYPE_ALL);
        lv.setAdapter(adapter);
    }

    private BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return sensors.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getBaseContext(), android.R.layout.simple_list_item_1, null);
            }
            TextView tv = (TextView) convertView;
            Sensor sensor = sensors.get(position);


            tv.setText(sensor.getName() + "   ");
            switch (sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    tv.append("  加速度传感器");
                    break;
                case Sensor.TYPE_LIGHT:
                    tv.append("  光线");
                    break;
                case Sensor.TYPE_GRAVITY:
                    tv.append("   重力");
                    break;
                default:
                    tv.append("  其他");
                    break;
            }
            return convertView;
        }
    };
}
