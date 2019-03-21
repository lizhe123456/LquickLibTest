package com.empty.lquicklib.di.component;

import android.app.Activity;
import com.empty.lquicklib.di.module.FragmentModule;
import com.empty.lquicklib.di.scope.FragmentScope;
import dagger.Component;

/**
 * Created by lizhe on 2017/12/11 0011.
 *
 */
@FragmentScope
@Component(modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

}
