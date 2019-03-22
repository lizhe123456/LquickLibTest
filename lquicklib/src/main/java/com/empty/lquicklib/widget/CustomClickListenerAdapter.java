package com.empty.lquicklib.widget;

import android.view.View;

/**
 * Created by lizhe on 2019/3/6.
 * 点击事件防止重复点击
 */

public abstract class CustomClickListenerAdapter implements CustomClickListener {

    private long mLastClickTime;
    private long timeInterval = 1000L;

    public CustomClickListenerAdapter() {

    }

    public CustomClickListenerAdapter(long interval) {
        this.timeInterval = interval;
    }

    @Override
    public void onClick(View v) {
        long nowTime = System.currentTimeMillis();
        if (nowTime - mLastClickTime > timeInterval) {
            // 单次点击事件
            onSingleClick(v);
            mLastClickTime = nowTime;
        }
    }

}
