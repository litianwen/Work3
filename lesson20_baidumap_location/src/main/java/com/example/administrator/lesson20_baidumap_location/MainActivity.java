package com.example.administrator.lesson20_baidumap_location;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapFragment;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends AppCompatActivity implements BDLocationListener {


    LocationClient client;

    BaiduMap mBaiduMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBaiduMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment)).getMapView().getMap();
    }

    public void location(View v) {
        //门牌号，定位城市
        LocationClientOption options = new LocationClientOption();
        options.setCoorType("bd09ll");
        options.setOpenGps(true);
        options.setIsNeedAddress(true);
        options.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //最重要一步 扫描时间 <=1000 定位1次
        options.setScanSpan(5000);
        client = new LocationClient(this, options);
        client.registerLocationListener(this);
        client.start();
    }


    boolean isFirst = true;

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        LatLng latlng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
        if (isFirst) {
            mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLngZoom(latlng, 18));
            mBaiduMap.setMyLocationEnabled(true);
            mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, null));
        }
        MyLocationData data = new MyLocationData.Builder()
                .latitude(latlng.latitude)
                .longitude(latlng.longitude)
                //方向传感器的手机方向
                .direction(270)
                .accuracy(1000)
                .build();
        mBaiduMap.setMyLocationData(data);
        Log.e("TAG", "----------------->>>" + bdLocation.getAddress().address + "   " + bdLocation.getCity());
        isFirst = false;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        client.stop();
    }
}
