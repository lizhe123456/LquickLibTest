package com.empty.lquicklibtest;


import com.empty.lquicklib.themvp.presenter.ActivityPresenter;

public class MainActivity extends ActivityPresenter<MainDelegate> {

    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }



}
