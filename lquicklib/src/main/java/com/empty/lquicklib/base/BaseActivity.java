package com.empty.lquicklib.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.empty.lquicklib.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lizhe on 2019/3/19.
 * activity基类
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity{

    private Activity mActivity;
    protected P mPresenter;

    protected final String TAG = this.getClass().getSimpleName();
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUnbinder = ButterKnife.bind(this);
        initData(savedInstanceState);
    }

    //重新进入是调用
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mPresenter == null) mPresenter = obtainPresenter();
    }


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = convertAutoView(name, context, attrs);
        return view == null ? super.onCreateView(name, context, attrs) : view;
    }

    protected abstract View convertAutoView(String name, Context context, AttributeSet attrs);

    protected abstract void initData(Bundle savedInstanceState);
    
    @Nullable
    protected abstract P obtainPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();
        this.mPresenter = null;
        this.mUnbinder = null;
    }
}
