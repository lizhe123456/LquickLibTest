package com.empty.lquicklib.model;

import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.empty.lquicklib.error.SysException;
import com.empty.lquicklib.presenter.BaseModel;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;

/**
 * Created by lizhe on 2019/3/21.
 * 统一处理
 */

public class DataManager implements BaseModel{


    private IRepositoryManager iRepositoryManager;

    public <T> FlowableTransformer<BaseResponse<T>,T> createObservable(){
        return object -> object.flatMap(tkyResponse -> {
            if (tkyResponse.getStatus() == 1) {

                return createData(tkyResponse);
            }else {
                return Flowable.error(new SysException(tkyResponse));
            }
        });
    }

    /**
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
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


    @Override
    public void destroy() {

    }
}
