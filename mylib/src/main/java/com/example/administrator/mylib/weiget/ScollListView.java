package com.example.administrator.mylib.weiget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/11/10.
 */

/**
 * ScollGridView
 * ScollViewPager
 */
public class ScollListView extends ListView {
    public ScollListView(Context context) {
        super(context);
    }

    public ScollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //布局拉伸
        int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, height);
    }

}
