package com.huary.daohang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.baidu.navisdk.adapter.BNRouteGuideManager;
import com.baidu.navisdk.adapter.BNaviSettingManager;

/**
 * Created by Administrator on 2016/7/29.
 */
public class MyActivity extends AppCompatActivity {

    private void initSetting() {
        BNaviSettingManager.setDayNightMode(BNaviSettingManager.DayNightMode.DAY_NIGHT_MODE_DAY);
        BNaviSettingManager.setShowTotalRoadConditionBar(BNaviSettingManager.PreViewRoadCondition.ROAD_CONDITION_BAR_SHOW_ON);
        BNaviSettingManager.setVoiceMode(BNaviSettingManager.VoiceMode.Veteran);
        BNaviSettingManager.setPowerSaveMode(BNaviSettingManager.PowerSaveMode.DISABLE_MODE);
        BNaviSettingManager.setRealRoadCondition(BNaviSettingManager.RealRoadCondition.NAVI_ITS_ON);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = BNRouteGuideManager.getInstance().onCreate(this, new BNRouteGuideManager.OnNavigationListener() {
            @Override
            public void onNaviGuideEnd() {

            }

            @Override
            public void notifyOtherAction(int i, int i1, int i2, Object o) {

            }
        });
//        View view = null;
//        BaiduNaviCommonModule mBaiduNaviCommonModule = NaviModuleFactory.getNaviModuleManager().getNaviCommonModule(
//                NaviModuleImpl.BNaviCommonModuleConstants.ROUTE_GUIDE_MODULE, this,
//                BNaviBaseCallbackModel.BNaviBaseCallbackConstants.CALLBACK_ROUTEGUIDE_TYPE, new BNRouteGuideManager.OnNavigationListener() {
//                    @Override
//                    public void onNaviGuideEnd() {
//
//                    }
//
//                    @Override
//                    public void notifyOtherAction(int i, int i1, int i2, Object o) {
//
//                    }
//                });
//        if (mBaiduNaviCommonModule != null) {
//            mBaiduNaviCommonModule.onCreate();
//            view = mBaiduNaviCommonModule.getView();
//        }
        if (view != null)
            setContentView(view);
        initSetting();
    }

}
