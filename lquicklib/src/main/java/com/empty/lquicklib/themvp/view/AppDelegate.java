/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.empty.lquicklib.themvp.view;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.blankj.utilcode.util.ToastUtils;
import com.empty.lquicklib.widget.CustomClickListener;
import org.jetbrains.annotations.NotNull;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * View delegate base class
 * 视图层代理的基类
 *
 * @author kymjs (http://www.kymjs.com/) on 10/23/15.
 */
public abstract class AppDelegate implements IDelegate {

    protected View rootView;
    private Unbinder mUnbinder;


    public abstract int getRootLayoutId();

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int rootLayoutId = getRootLayoutId();
        if (rootLayoutId != 0) {
            mUnbinder = ButterKnife.bind(getActivity());
        }
        rootView = inflater.inflate(rootLayoutId, container, false);
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    /**
     * 初始化视图
     */
    @Override
    public void initWidget() {

    }

    /**
     * @param listener 自定义监听，防止重复点击
     * @param ids 控件id
     */
    public void setOnClickListener(CustomClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            rootView.findViewById(id).setOnClickListener(listener);
        }
    }

    public void toast(CharSequence msg) {
        ToastUtils.showShort(msg);
    }

    public <T extends Activity> T getActivity() {
        return (T) rootView.getContext();
    }

    @Override
    public void onLifecycleChanged(@NotNull LifecycleOwner owner, @NotNull Lifecycle.Event event) {

    }

    @Override
    public void onDestroy(@NotNull LifecycleOwner owner) {
        mUnbinder.unbind();
    }

}
