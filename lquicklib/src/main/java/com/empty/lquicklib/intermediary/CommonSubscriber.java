package com.empty.lquicklib.intermediary;

import com.blankj.utilcode.util.ToastUtils;
import com.empty.lquicklib.error.ApiException;
import com.empty.lquicklib.error.HttpError;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by lizhe on 2018/3/14.
 *
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {

    @Override
    public void onNext(T t) {
        //下一步
    }

    @Override
    public void onComplete() {
        //请求完成

    }

    @Override
    public void onError(Throwable e) {
        //请求错误处理
        ApiException apiException = HttpError.handleThrowable(e);
        if (apiException.getDisplayMessage() != null) {
            ToastUtils.showShort(apiException.getDisplayMessage());
        }
    }
}

