package com.example.administrator.mvc.presenter;

import com.example.administrator.mvc.bean.CangTouShiBean;
import com.example.administrator.mvc.callback.BeanCallback;
import com.example.administrator.mvc.interfaces.ICangTouShi;
import com.example.administrator.mvc.modle.CangTouShiModle;
import com.example.administrator.mvc.view.IMvpView;

/**
 * Created by Administrator on 2016/11/22.
 */

public class MvpPresenter {

    IMvpView iMvpView;
    ICangTouShi cangTouShi;

    //构造方法
    public MvpPresenter(IMvpView view) {
        this.iMvpView = view;
        cangTouShi = new CangTouShiModle();
    }


    public void getData() {
        //判断
        if (iMvpView.getKey().equals("")) {
            iMvpView.showToast("key不能为空");
            return;
        }
        iMvpView.showDialog();
        cangTouShi.doquest(iMvpView.getNum(), iMvpView.getType(), iMvpView.getYY(), iMvpView.getKey(), new BeanCallback<CangTouShiBean>() {
            @Override
            public void onSuccess(CangTouShiBean cangTouShiBean) {
                String msg = "";
                for (String s : cangTouShiBean.getShowapi_res_body().getList()) {
                    msg += s + "\n";
                }
                iMvpView.setText(msg);
                iMvpView.dismissDialog();
            }

            @Override
            public void onError(String msg) {
                iMvpView.showToast(msg);
                iMvpView.dismissDialog();
            }
        });
    }

}
