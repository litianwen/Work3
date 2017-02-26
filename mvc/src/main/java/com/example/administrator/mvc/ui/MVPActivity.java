package com.example.administrator.mvc.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.mvc.R;
import com.example.administrator.mvc.presenter.MvpPresenter;
import com.example.administrator.mvc.view.IMvpView;


/**
 * Created by Administrator on 2016/11/22.
 */

public class MVPActivity extends AppCompatActivity implements IMvpView, View.OnClickListener {

    RadioGroup rg_57, rg_ct, rg_yy;
    EditText et_key;
    TextView tv_show;
    Button btn_submit;


    MvpPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        presenter = new MvpPresenter(this);
    }

    private void initView() {
        rg_57 = (RadioGroup) findViewById(R.id.rg_57);
        rg_57.check(R.id.rb_5);
        rg_ct = (RadioGroup) findViewById(R.id.rg_ct);
        rg_ct.check(R.id.rb_ct);
        rg_yy = (RadioGroup) findViewById(R.id.rg_yy);
        rg_yy.check(R.id.rb_1y);
        et_key = (EditText) findViewById(R.id.et_key);
        tv_show = (TextView) findViewById(R.id.tv_show);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
    }

    @Override
    public String getNum() {
        return rg_57.getCheckedRadioButtonId() == R.id.rb_5 ? "5" : "7";
    }

    @Override
    public String getType() {
//        String type = null;
//        switch (rg_ct.getCheckedRadioButtonId()) {
//            case R.id.rb_ct:
//                type = "1";
//                break;
//            case R.id.rb_cw:
//                type = "2";
//                break;
//            case R.id.rb_cz:
//                type = "3";
//                break;
//            case R.id.rb_dz:
//                type = "4";
//                break;
//            case R.id.rb_dj:
//                type = "5";
//                break;
//        }
        EditText et = (EditText) findViewById(R.id.et_type);
        return et.getText().toString();
    }

    @Override
    public String getYY() {
        String yy = null;
        switch (rg_yy.getCheckedRadioButtonId()) {
            case R.id.rb_1y:
                yy = "1";
                break;
            case R.id.rb_2y:
                yy = "2";
                break;
            case R.id.rb_3y:
                yy = "3";
                break;
        }
        return yy;
    }

    @Override
    public String getKey() {
        return et_key.getText().toString();
    }

    ProgressDialog dialog;

    @Override
    public void showDialog() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("提示");
        dialog.setMessage("开始请求");
        dialog.show();

    }

    @Override
    public void dismissDialog() {
        dialog.dismiss();
    }

    @Override
    public void setText(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_show.setText(text);
            }
        });

    }

    @Override
    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showToast(msg);
            }
        });
    }

    @Override
    public void onClick(View v) {
        //开始请求
        presenter.getData();
    }
}
