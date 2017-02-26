package com.example.administrator.lesson19_xutilsdemo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.administrator.lesson19_xutilsdemo2.adapter.DuanZiAdapter;
import com.example.administrator.lesson19_xutilsdemo2.base.BeanCallback;
import com.example.administrator.lesson19_xutilsdemo2.bean.DuanZiBean;
import com.example.administrator.lesson19_xutilsdemo2.httputils.DuanZiHttpUtils;
import com.example.administrator.mylib.weiget.ScollListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener2<ScrollView> {

    @BindView(R.id.lv)
    ScollListView lv;
    @BindView(R.id.ptfsv)
    PullToRefreshScrollView ptfsv;
    DuanZiAdapter adapter;
    private List<DuanZiBean> mList = new ArrayList<>();

    int pager = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new DuanZiAdapter(this, mList);
        lv.setAdapter(adapter);
        ptfsv.setMode(PullToRefreshBase.Mode.BOTH);
        ptfsv.setOnRefreshListener(this);
        ptfsv.setRefreshing();
        ptfsv.smoothScrollTo(-50);
        doRequest();
    }


    public void doRequest() {
        DuanZiHttpUtils.getDuanZiFromPager(pager, new BeanCallback<List<DuanZiBean>>() {
            @Override
            public void onSuccess(List<DuanZiBean> duanZiBeen) {
                Log.e("TAG", "--->" + duanZiBeen.toString());
//                mList = duanZiBeen;
//                adapter.setList(mList);
                mList.addAll(duanZiBeen);
                adapter.notifyDataSetChanged();
                //滑动到最底下
            }

            @Override
            public void onFinish() {
                ptfsv.onRefreshComplete();
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
                mList.clear();
                List<DuanZiBean> list = DuanDao.getAllDuanZi();
                if (list != null)
                    mList.addAll(list);
                else {
                    Toast.makeText(getBaseContext(), "没有缓存信息", Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        pager = 1;
        mList.clear();
        doRequest();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        pager++;
        doRequest();
    }
}
