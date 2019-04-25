package com.empty.lquicklib.intermediary;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.empty.lquicklib.error.SysException;
import com.empty.lquicklib.model.BaseResponse;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Company 武汉麦诺软创
 * Created by lizhe on 2018/5/2.
 * rx统一调度
 */

public class RxSchedulersHelper {

    /**
     * 生命周期绑定
     *
     * @param lifecycle Activity
     */
    public static <T> LifecycleTransformer bindToLifecycle(@NonNull Context lifecycle) {
        if (lifecycle instanceof LifecycleProvider) {
            return ((LifecycleProvider) lifecycle).bindToLifecycle();
        } else {
            throw new IllegalArgumentException("context not the LifecycleProvider type");
        }
    }

    /**
     * 生命周期绑定
     *
     * @param lifecycle Fragment
     */
    public static LifecycleTransformer bindToLifecycle(@NonNull Fragment lifecycle) {
        if (lifecycle instanceof LifecycleProvider) {
            return ((LifecycleProvider) lifecycle).bindToLifecycle();
        } else {
            throw new IllegalArgumentException("fragment not the LifecycleProvider type");
        }
    }

    /**
     * 生命周期绑定
     *
     * @param lifecycle Fragment
     */
    public static LifecycleTransformer bindToLifecycle(@NonNull LifecycleProvider lifecycle) {
        return lifecycle.bindToLifecycle();
    }

    /**
     * 线程调度器
     */
    public static FlowableTransformer schedulersTransformer() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 处理有返回（有值）
     */
    public static <T> FlowableTransformer<BaseResponse<T>, T> handleData() {
        return object -> object.flatMap(response -> {
            if (response.getStatus() == 1) {
                if (response.getResult() == null || TextUtils.isEmpty(JSON.toJSONString(response.getResult()))) {
                    return Flowable.error(new SysException(response.getMessage()));
                } else {
                    return createData(response.getResult());
                }
            } else {
                return Flowable.error(new SysException(response.getMessage()));
            }
        });
    }



    /**
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(T t) {
        return Flowable.create(emitter -> {
            if (!emitter.isCancelled()) {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

}
