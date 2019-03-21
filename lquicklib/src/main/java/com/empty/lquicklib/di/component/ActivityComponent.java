package com.empty.lquicklib.di.component;

import android.app.Activity;
import com.empty.lquicklib.di.module.ActivityModule;
import com.empty.lquicklib.di.scope.ActivityScope;
import dagger.Component;

/**
 * Created by lizhe on 2017/12/11 0011.
 *
 */
@ActivityScope
@Component( modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

}
