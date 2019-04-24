package com.empty.lquicklib.model;

import android.app.Activity;
import android.arch.lifecycle.LifecycleObserver;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by lizhe on 2019/3/22.
 * Presenter层 持有Model层对象 与View层双向绑定
 *
 */

public interface IPresenter<M> extends LifecycleObserver{

    M getModel();

    void bindView(View view);

    void returnActivity(Activity activity);

    void returnFragment(Fragment fragment);

}
