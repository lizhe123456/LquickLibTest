package com.empty.lquicklibtest.ui;

import android.view.View;
import android.widget.TextView;

import com.empty.lquicklib.themvp.view.AppDelegate;
import com.empty.lquicklib.widget.CustomClickListenerAdapter;
import com.empty.lquicklibtest.R;

import butterknife.BindView;

/**
 * Created by lizhe on 2019/3/22.
 *
 */

public class TextDelegate extends AppDelegate  {


    @BindView(R.id.tv_text)
    TextView tvText;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_text;
    }

    @Override
    public void initWidget() {
        super.initWidget();

        setOnClickListener(new CustomClickListenerAdapter() {
            @Override
            public void onSingleClick(View view) {
                switch (view.getId()){
                    case R.id.tv_text:

                        break;
                }
            }
        },R.id.tv_text);
    }



}
