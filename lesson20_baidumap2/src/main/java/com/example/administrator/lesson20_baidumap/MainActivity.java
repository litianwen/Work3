package com.example.administrator.lesson20_baidumap;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapFragment;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends AppCompatActivity implements BDLocationListener, RadioGroup.OnCheckedChangeListener {

    BaiduMap mBaidumap;

    LocationClient client;

    boolean isFirst = true;

    RadioGroup rg;

    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initSensor();
        location();
    }

    private void initSensor() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_STATUS_ACCURACY_LOW);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(listener);
        sensorManager = null;
    }

    float x;
    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            x = event.values[0];
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void initView() {
        rg = (RadioGroup) findViewById(R.id.rg_tab);
        rg.check(R.id.rb_normal);
        rg.setOnCheckedChangeListener(this);
        mBaidumap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment)).getBaiduMap();
    }

    private void location() {
        LocationClientOption options = new LocationClientOption();
        options.setScanSpan(3000);
        options.setCoorType("bd09ll");
        options.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        client = new LocationClient(this, options);
        client.registerLocationListener(this);
        client.start();
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (isFirst) {
            MapStatusUpdate status = MapStatusUpdateFactory.newLatLngZoom(new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude()), 16);
            mBaidumap.setMapStatus(status);
            isFirst = false;
            mBaidumap.setMyLocationEnabled(true);
            MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, null);
            mBaidumap.setMyLocationConfigeration(config);
        }
        //显示
        MyLocationData mydata = new MyLocationData.Builder().latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude())
                .accuracy(1000)
                .direction(x)
                .build();
        mBaidumap.setMyLocationData(mydata);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        MyLocationConfiguration config;
        switch (checkedId) {
            case R.id.rb_compass:
                config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.COMPASS, true, null);
                break;
            case R.id.rb_follow:
                config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, null);
                break;
            default:
                config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, null);
        }
        mBaidumap.setMyLocationConfigeration(config);
    }
}
