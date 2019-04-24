package com.empty.lquicklib.base;

import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;

import com.empty.lquicklib.mvvm.BaseViewModel;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by lizhe on 2019/4/24.
 *
 */

public abstract class BaseFragment <V extends ViewDataBinding, VM extends BaseViewModel> extends RxFragment {

    protected V binding;
    protected VM viewModel;
    private int viewModelId;


}
