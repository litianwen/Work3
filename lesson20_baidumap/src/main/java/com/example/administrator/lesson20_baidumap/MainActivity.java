package com.example.administrator.lesson20_baidumap;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;

import static java.lang.System.out;

public class MainActivity extends AppCompatActivity {

    MapView mapView;
    BaiduMap mBaiduMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.mapView);
        out.print("");
        //控制器
        mBaiduMap = mapView.getMap();
        //将地图定位到本地
        //113.95169,22.536269经纬度
        //设置地图的状态
        //地图纬经度
        final LatLng latlng = new LatLng(22.536269, 113.95169);
        //1-20级  20级室内地图
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(latlng, 18);
        mBaiduMap.setMapStatus(mapStatusUpdate);
//        //打开交通图
//        mBaiduMap.setTrafficEnabled(true);
//        //打开热力图
//        mBaiduMap.setBaiduHeatMapEnabled(true);
//
        //打开卫星图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);


        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                //地点
            }

            @Override
            public void onMarkerDragStart(Marker marker) {

            }
        });
//        //监听事件
//        //地图改变时的监听， 缩放等级
//        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
//            @Override
//            public void onMapStatusChangeStart(MapStatus mapStatus) {
//
//            }
//
//            @Override
//            public void onMapStatusChange(MapStatus mapStatus) {
//                //清除所有覆盖物
//                mBaiduMap.clear();
//                if (mapStatus.zoom >= 15) {
//                    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
//
//                    //创建覆盖物
//                    OverlayOptions options = new MarkerOptions().position(latlng).icon(bitmap);
//                    //添加覆盖物
//                    mBaiduMap.addOverlay(options);
//                }
//            }
//
//            @Override
//            public void onMapStatusChangeFinish(MapStatus mapStatus) {
//
//            }
//        });
//        mBaiduMap.setOnMapClickListener();
//        mBaiduMap.setOnMapDoubleClickListener();

        //覆盖物点击的监听
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getBaseContext(), "点击了机器人", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mBaiduMap.clear();
                BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);

                //创建覆盖物
                OverlayOptions options = new MarkerOptions().position(latlng).icon(bitmap).draggable(true)
                        .animateType(MarkerOptions.MarkerAnimateType.drop);
                //添加覆盖物
                Marker ov = (Marker) mBaiduMap.addOverlay(options);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });


        //Overlay
        TextOptions textOptions = new TextOptions().text("百度地图哈哈").position(latlng).fontSize(72).bgColor(Color.BLACK).fontColor(Color.WHITE);
        mBaiduMap.addOverlay(textOptions);

        CircleOptions circleOptions = new CircleOptions().center(latlng).radius(1000).stroke(new Stroke(10, Color.RED))
                .fillColor(Color.TRANSPARENT);
        mBaiduMap.addOverlay(circleOptions);


        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //在当前的地方 弹出一个infoWindow
                ImageView iv = new ImageView(getBaseContext());
                iv.setImageResource(R.mipmap.ic_launcher);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), "点击事件", Toast.LENGTH_SHORT).show();
                    }
                });
                //Y轴的偏移量
                InfoWindow infoWindow = new InfoWindow(iv, latLng, -50);
                mBaiduMap.showInfoWindow(infoWindow);
            }

            //兴趣点
            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                Toast.makeText(MainActivity.this, mapPoi.getName() + "   " + mapPoi.getUid(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }
}
