package com.empty.lquicklibtest;

import android.os.Bundle;

import com.empty.lquicklib.base.BaseActivity;
import com.empty.lquicklibtest.databinding.ActivityTestBinding;
import com.empty.lquicklibtest.viewmodel.MainViewModel;
import com.empty.lquicklibtest.BR;


/**
 * Created by lizhe on 2019/4/2.
 *
 */

public class MainActivity extends BaseActivity<ActivityTestBinding,MainViewModel> {


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_test;
    }

}
