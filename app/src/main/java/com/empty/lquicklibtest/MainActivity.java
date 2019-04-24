package com.empty.lquicklibtest;

import android.os.Bundle;

import com.empty.lquicklibtest.viewmodel.MainViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * Created by lizhe on 2019/4/2.
 *
 */

public class MainActivity extends BaseActivity<ActivityLoginBinding,MainViewModel>{
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_test;
    }

    @Override
    public int initVariableId() {
        return BRBR.viewModel;
    }
}
