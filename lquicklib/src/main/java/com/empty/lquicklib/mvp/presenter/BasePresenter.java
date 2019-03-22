package com.empty.lquicklib.mvp.presenter;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import com.empty.lquicklib.mvp.model.IModel;

/**
 * Created by lizhe on 2019/3/22.
 *
 */

public class BasePresenter<M extends IModel> implements IPresenter<M>{

    private View mRootView;
    private Activity mActivity;
    private Fragment mFragment;


    @Override
    public M getModel() {
        return null;
    }

    @Override
    public void bindView(View view) {
        mRootView = view;
    }

    public void returnActivity(Activity activity){
        mActivity = activity;
    }

    public void returnFragment(Fragment fragment){
        mFragment = fragment;
    }

}
