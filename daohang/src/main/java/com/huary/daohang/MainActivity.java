package com.huary.daohang;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.baidu.navisdk.adapter.BaiduNaviManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BaiduNaviManager manager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNavi();

    }

    public void suanlu(View v) {
        if (manager == null) {
            Toast.makeText(this, "等待初始化", Toast.LENGTH_SHORT).show();
            return;
        }
        List<BNRoutePlanNode> nodes = new ArrayList<>();
        //116.387596,39.913385
        //114.063812,22.558929
        nodes.add(new BNRoutePlanNode(113.938481, 22.566939, "机场", "直升机机场", BNRoutePlanNode.CoordinateType.BD09LL));
        nodes.add(new BNRoutePlanNode(114.063812, 22.558929, "莲花山", "深圳莲花山公园", BNRoutePlanNode.CoordinateType.BD09LL));
        manager.launchNavigator(this, nodes, BaiduNaviManager.RoutePlanPreference.ROUTE_PLAN_MOD_RECOMMEND,
                false, new BaiduNaviManager.RoutePlanListener() {
                    @Override
                    public void onJumpToNavigator() {
                        //跳转到导航
                        Intent intent = new Intent(MainActivity.this, MyActivity.class);
//                        bundle.putSerializable(ROUTE_PLAN_NODE, (BNRoutePlanNode) mBNRoutePlanNode);
//                        intent.putExtra()
                        startActivity(intent);
                    }

                    @Override
                    public void onRoutePlanFailed() {

                    }
                });

    }


    String authinfo;

    /**
     * 内部TTS播报状态回传handler
     */
    private Handler ttsHandler = new Handler() {
        public void handleMessage(Message msg) {
            int type = msg.what;
            switch (type) {
                case BaiduNaviManager.TTSPlayMsgType.PLAY_START_MSG: {
                    Log.e("TAG", "-------------------------------播报");
                    break;
                }
                case BaiduNaviManager.TTSPlayMsgType.PLAY_END_MSG: {
                    break;
                }
                default:
                    break;
            }
        }
    };

    public void showToastMsg(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 内部TTS播报状态回调接口
     */
    private BaiduNaviManager.TTSPlayStateListener ttsPlayStateListener = new BaiduNaviManager.TTSPlayStateListener() {

        @Override
        public void playEnd() {
            showToastMsg("TTSPlayStateListener : TTS play end");
        }

        @Override
        public void playStart() {
            showToastMsg("TTSPlayStateListener : TTS play start");
        }
    };

    private void initNavi() {
        manager = BaiduNaviManager.getInstance();
        manager.init(this, Environment.getExternalStorageDirectory().getAbsolutePath(), "daohang",
                new BaiduNaviManager.NaviInitListener() {
                    @Override
                    public void onAuthResult(int status, String msg) {
                        if (0 == status) {
                            authinfo = "key校验成功!";
                        } else {
                            authinfo = "key校验失败, " + msg;
                        }
                        MainActivity.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, authinfo, Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    public void initSuccess() {
                        Toast.makeText(MainActivity.this, "百度导航引擎初始化成功", Toast.LENGTH_SHORT).show();
                        //启动导航

                    }

                    public void initStart() {
                        Toast.makeText(MainActivity.this, "百度导航引擎初始化开始", Toast.LENGTH_SHORT).show();
                    }

                    public void initFailed() {
                        Toast.makeText(MainActivity.this, "百度导航引擎初始化失败", Toast.LENGTH_SHORT).show();
                    }
                }, null, ttsHandler, ttsPlayStateListener/*mTTSCallback*/);
    }
}
