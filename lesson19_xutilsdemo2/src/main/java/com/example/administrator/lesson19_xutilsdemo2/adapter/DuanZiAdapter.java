package com.example.administrator.lesson19_xutilsdemo2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.lesson19_xutilsdemo2.R;
import com.example.administrator.lesson19_xutilsdemo2.bean.DuanZiBean;
import com.example.administrator.lesson19_xutilsdemo2.weiget.VideoPlayer;
import com.example.administrator.mylib.adapter.ListItemAdapter;
import com.example.administrator.mylib.weiget.CircleImageView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/16.
 */

public class DuanZiAdapter extends ListItemAdapter<DuanZiBean> {

    public DuanZiAdapter(Context context, List<DuanZiBean> list) {
        super(context, list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        //设置数据
        //holder.ivHead
        DuanZiBean bean = getItem(position);
        x.image().bind(holder.ivHead, bean.getProfile_image());
        holder.tvName.setText(bean.getName());
        holder.tvTime.setText(bean.getCreate_time());
        holder.flContent.removeAllViews();
        switch (bean.getType()) {
            case DuanZiBean.TYPE_IMAGE:
                ImageView iv;
                iv = new ImageView(mContext);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setAdjustViewBounds(true);
                holder.flContent.addView(iv);
                //做了裁剪
                //取消裁剪
                ImageOptions options = new ImageOptions.Builder()
                        .setCrop(false).setSize(mContext.getResources().getDisplayMetrics().widthPixels, 100).build();
                x.image().bind(iv, bean.getImage0(), options);
                break;
            case DuanZiBean.TYPE_MUSIC:
                //自定义View  LinearLayout  播放器
                break;
            case DuanZiBean.TYPE_VIDEO:
                Log.e("TAG", "-----------------------视频来了" + bean.toString());
                //自定义View  SurfaceView MediaPlayer
                VideoPlayer player = new VideoPlayer(mContext, bean.getVideo_uri());
                holder.flContent.addView(player);
                break;
            case DuanZiBean.TYPE_DUANZI:
                TextView tv = new TextView(mContext);
                tv.setText(bean.getText());
                holder.flContent.addView(tv);
                break;
        }

        return convertView;
    }


//adapter的方法，返回有多少类型

    static class ViewHolder {
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_share)
        TextView tvShare;
        @BindView(R.id.fl_content)
        FrameLayout flContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
