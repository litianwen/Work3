package com.example.administrator.mvc.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mvc.R;
import com.example.administrator.mvc.bean.CangTouShiBean;
import com.example.administrator.mvc.callback.BeanCallback;
import com.example.administrator.mvc.modle.CangTouShiModle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //逻辑判断，UI操作

    RadioGroup rg_57, rg_ct, rg_yy;
    EditText et_key;
    TextView tv_show;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registerListener();
    }

    private void registerListener() {
        //逻辑控制
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = et_key.getText().toString();
                if (TextUtils.isEmpty(key)) {
                    Toast.makeText(MainActivity.this, "key不允许为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String num = rg_57.getCheckedRadioButtonId() == R.id.rb_5 ? "5" : "7";
                String type = null;
                switch (rg_ct.getCheckedRadioButtonId()) {
                    case R.id.rb_ct:
                        type = "1";
                        break;
                    case R.id.rb_cw:
                        type = "2";
                        break;
                    case R.id.rb_cz:
                        type = "3";
                        break;
                    case R.id.rb_dz:
                        type = "4";
                        break;
                    case R.id.rb_dj:
                        type = "5";
                        break;
                }
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
                final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                dialog.setTitle("提示");
                dialog.setMessage("开始请求");
                dialog.show();

                CangTouShiModle modle = new CangTouShiModle();
                modle.doquest(num, type, yy, key, new BeanCallback<CangTouShiBean>() {
                    @Override
                    public void onSuccess(final CangTouShiBean cangTouShiBean) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                List<String> list = cangTouShiBean.getShowapi_res_body().getList();
                                tv_show.setText("");
                                for (String s : list) {
                                    tv_show.append(s + "\n");
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(final String msg) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });


            }
        });
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
    }
}
