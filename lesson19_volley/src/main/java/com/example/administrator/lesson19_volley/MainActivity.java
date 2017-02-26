package com.example.administrator.lesson19_volley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
    }

    public void net1(View v) {
        //使用Volley需要一个队列
        //高并发
        RequestQueue queue = Volley.newRequestQueue(this);

        Request request = new StringRequest("https://www.github.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "responese:" + response);
            }
        }, error);
        //将请求添加到队列，就开始执行了
        queue.add(request);
    }

    public void net2(View v) {
        RequestQueue queue = Volley.newRequestQueue(this);
        //appliaction/json
        Request requset = new StringRequest(Request.Method.POST, "http://route.showapi.com/109-35", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "---" + response);
            }
        }, error) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("showapi_appid", "13074");
                map.put("showapi_sign", "ea5b4bf2e140498bb772d1bf2a51a7a0");
                return map;
            }
        };
        queue.add(requset);
    }


    private Response.ErrorListener error = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("TAG", "----访问错误：" + error.getMessage());
        }
    };

    public void net3(View v) {
        RequestQueue queue = Volley.newRequestQueue(this);

        Request request = new ImageRequest("https://www.showapi.com/images/apiLogo/20150606/5423acc973f41c03173a186a_22b6c6381e3444449b2cfb3724e63c6c.jpg", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                iv.setImageBitmap(response);
            }
            //防止OOM   图像裁剪 压缩品质 三级缓存
        }, 200, 200, ImageView.ScaleType.FIT_XY, Bitmap.Config.RGB_565, error);
        queue.add(request);

    }

    public void net4(View v) {
        //ImageLoader + AysncHttpClient
        RequestQueue queue = Volley.newRequestQueue(this);
        ImageLoader loader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            LruCache<String, Bitmap> bitmapLruCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory() / 8)) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getByteCount();
                }
            };

            @Override
            public Bitmap getBitmap(String url) {
                return bitmapLruCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                bitmapLruCache.put(url, bitmap);
            }
        });

        loader.get("http://www.skycn.com/up/2015/previews_19145.jpg", new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                iv.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }
}
