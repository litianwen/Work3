package com.example.administrator.lesson20_baidumap_navigation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void init(View v) {
        NaviParaOption option = new NaviParaOption();
        option.startName("深大地铁站");
        option.startPoint(new LatLng(22.544109, 113.950338));
        option.endName("桃园地铁站");
        option.endPoint(new LatLng(22.538234, 113.931079));
        BaiduMapNavigation.openBaiduMapBikeNavi(option, this);
    }
}
