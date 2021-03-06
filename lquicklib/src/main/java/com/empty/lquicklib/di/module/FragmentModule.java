package com.empty.lquicklib.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.empty.lquicklib.di.scope.FragmentScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/8/18 0018.
 *
 */
@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity(){
        return fragment.getActivity();
    }
}
