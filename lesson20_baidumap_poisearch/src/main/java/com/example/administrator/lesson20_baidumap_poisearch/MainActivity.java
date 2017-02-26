package com.example.administrator.lesson20_baidumap_poisearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapFragment;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.BusLineOverlay;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.busline.BusLineResult;
import com.baidu.mapapi.search.busline.BusLineSearch;
import com.baidu.mapapi.search.busline.BusLineSearchOption;
import com.baidu.mapapi.search.busline.OnGetBusLineSearchResultListener;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;


public class MainActivity extends AppCompatActivity {

    MapView mapView;

    BaiduMap mBaiduMap;

    PoiSearch poiSearch;

    EditText et;


    BusLineSearch busLineSearch;

    RoutePlanSearch routePlanSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //从Fragment中获取地图
        mapView = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment)).getMapView();
        et = (EditText) findViewById(R.id.et);
        mBaiduMap = mapView.getMap();
        //深圳
        final LatLng latlng = new LatLng(22.536269, 113.95169);
        //设置到深圳
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLngZoom(latlng, 15));


        poiSearch = PoiSearch.newInstance();
        busLineSearch = BusLineSearch.newInstance();
        routePlanSearch = RoutePlanSearch.newInstance();
//        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                PoiInfo info = marker.getExtraInfo().getParcelable("info");
//                Toast.makeText(MainActivity.this, info.name + "   " + info.address + "   " + info.phoneNum, Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
    }

    //Poi搜索监听
    OnGetPoiSearchResultListener searchListener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            mBaiduMap.clear();
            //获取结果
            //Log.e("TAG", "---返回结果：" + poiResult.getAllPoi().size());
            //判断返回结果
            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
//                //请求成功
//                List<PoiInfo> list = poiResult.getAllPoi();
//                for (int i = 0; i < list.size(); i++) {
//                    //10
//                    PoiInfo info = list.get(i);
//                    int id = 0;
//                    try {
//                        id = R.mipmap.class.getField("icon_mark" + (char) (97 + i)).getInt(null);
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    } catch (NoSuchFieldException e) {
//                        e.printStackTrace();
//                    }
//                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(id);
//                    MarkerOptions options = new MarkerOptions().icon(bitmapDescriptor).position(info.location);
//                    Marker marker = (Marker) mBaiduMap.addOverlay(options);
//                    Bundle bundle = new Bundle();
//                    bundle.putParcelable("info", info);
//                    marker.setExtraInfo(bundle);
//                }

                MyOverlay overlay = new MyOverlay(mBaiduMap);
                //设置数据
                overlay.setData(poiResult);
                //添加到地图
                overlay.addToMap();
                //将显示视图拉倒正好可以看到所有POI兴趣点的缩放等级
                overlay.zoomToSpan();//计算工具
                mBaiduMap.setOnMarkerClickListener(overlay);
            } else {
                //...失败
            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            //获取详细结果
            Log.e("TAG", "-----" + poiDetailResult.getAddress() + "  " + poiDetailResult.getShopHours() + "  " + poiDetailResult.getPrice() + "  ");
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
            //室内
        }
    };

    public void search(View v) {
        String key = et.getText().toString();
        poiSearch.setOnGetPoiSearchResultListener(searchListener);
        //发起检索
        //城市
        //poiSearch.searchInCity(new PoiCitySearchOption().city("深圳").keyword(key).pageCapacity(10));
        final LatLng latlng = new LatLng(22.536269, 113.95169);
        poiSearch.searchNearby(new PoiNearbySearchOption().keyword(key).location(latlng).radius(10000));

    }

    OnGetPoiSearchResultListener busLinePoiListener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                for (int i = 0; i < poiResult.getAllPoi().size(); i++) {
                    PoiInfo info = poiResult.getAllPoi().get(i);
                    if (info.type == PoiInfo.POITYPE.SUBWAY_LINE) {
                        //发起公交检索
                        busLineSearch.searchBusLine(new BusLineSearchOption().city("深圳").uid(info.uid));
                        return;
                    }
                }
            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
    };

    public void busSearch(View v) {
        String key = et.getText().toString();
        poiSearch.setOnGetPoiSearchResultListener(busLinePoiListener);
        poiSearch.searchInCity(new PoiCitySearchOption().keyword(key).city("深圳"));
        busLineSearch.setOnGetBusLineSearchResultListener(new OnGetBusLineSearchResultListener() {
            @Override
            public void onGetBusLineResult(BusLineResult busLineResult) {
                MyBusOverlay ovlayer = new MyBusOverlay(mBaiduMap);
                ovlayer.setData(busLineResult);
                ovlayer.addToMap();
                ovlayer.zoomToSpan();
                mBaiduMap.setOnMarkerClickListener(ovlayer);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        poiSearch.destroy();
        busLineSearch.destroy();
        routePlanSearch.destroy();
    }


    class MyOverlay extends PoiOverlay {

        /**
         * 构造函数
         *
         * @param baiduMap 该 PoiOverlay 引用的 BaiduMap 对象
         */
        public MyOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int i) {
            PoiInfo poiInfo = getPoiResult().getAllPoi().get(i);
//            发起一个详细检索
//            poiSearch.searchPoiDetail(new PoiDetailSearchOption().poiUid(poiInfo.uid));
            //路线规划
            routePlanSearch.setOnGetRoutePlanResultListener(new OnGetRoutePlanResultListener() {
                @Override
                public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                    if (walkingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                        if (walkingRouteResult.getRouteLines().size() > 0) {
                            WalkingRouteLine line = walkingRouteResult.getRouteLines().get(0);
                            mBaiduMap.clear();
                            MyWalkingOverlay overlay = new MyWalkingOverlay(mBaiduMap);
                            overlay.setData(line);
                            overlay.addToMap();
                            overlay.zoomToSpan();
                            mBaiduMap.setOnMarkerClickListener(overlay);
                        }
                    }
                }

                @Override
                public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

                }

                @Override
                public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

                }

                @Override
                public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

                }

                @Override
                public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

                }

                @Override
                public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

                }
            });
            routePlanSearch.walkingSearch(new WalkingRoutePlanOption().from(PlanNode.withCityNameAndPlaceName("深圳", "科苑地铁站")).to(PlanNode.withLocation(poiInfo.location)))
            ;
            return true;
        }
    }

    class MyBusOverlay extends BusLineOverlay {

        /**
         * 构造函数
         *
         * @param baiduMap 该BusLineOverlay所引用的 BaiduMap 对象
         */
        public MyBusOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }
    }

    class MyWalkingOverlay extends WalkingRouteOverlay {

        public MyWalkingOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }
    }

}
