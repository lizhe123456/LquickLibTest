package com.empty.lquicklib.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.empty.lquicklib.mvp.presenter.IPresenter;

import butterknife.Unbinder;

/**
 * Created by lizhe on 2019/3/22.
 *
 */

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView {

    P mPresenter;

    private Unbinder mUnbinder;

    public BaseActivity() {
        try {
            mPresenter = getDelegateClass().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("create mPresenter error");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("create mPresenter error");
        }
    }


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.bindView(getLayoutInflater().inflate(setLayout(),null,false));
        mPresenter.returnActivity(this);
        getLifecycle().addObserver(mPresenter);
    }

    @LayoutRes
    protected abstract int setLayout();

    @Override
    public IPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mPresenter == null) {
            try {
                mPresenter = getDelegateClass().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("create mPresenter error");
            } catch (IllegalAccessException e) {
                throw new RuntimeException("create mPresenter error");
            }
        }
    }


    protected abstract Class<P> getDelegateClass();


    @Override
    protected void onDestroy() {
        mPresenter = null;
        super.onDestroy();
    }
}
