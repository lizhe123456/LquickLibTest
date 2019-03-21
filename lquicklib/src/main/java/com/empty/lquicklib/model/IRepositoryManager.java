package com.empty.lquicklib.model;

import android.content.Context;
import android.support.annotation.NonNull;

import com.empty.lquicklib.presenter.BaseModel;

/**
 * Created by lizhe on 2019/3/21.
 *
 */

public interface IRepositoryManager {

    /**
     * 根据传入的 Class 创建对应的仓库
     *
     * @param repository 仓库 class
     * @param <T>        仓库 类型
     * @return 仓库
     */
    @NonNull
    <T extends BaseModel> T createRepository(@NonNull Class<T> repository);

    /**
     * 根据传入的 Class 创建对应的 Retrofit service
     *
     * @param service Retrofit service class
     * @param <T>     Retrofit service 类型
     * @return Retrofit service
     */
    @NonNull
    <T> T createRetrofitService(@NonNull Class<T> service);

    /**
     * 根据传入的 Class 创建对应的 RxCache service
     *
     * @param cache RxCache service class
     * @param <T>   RxCache service 类型
     * @return RxCache service
     */
    @NonNull
    <T> T createCacheService(@NonNull Class<T> cache);

    /**
     * 清理所有缓存
     */
    void clearAllCache();

    /**
     * 获取 {@link Context}
     *
     * @return {@link Context}
     */
    @NonNull
    Context getContext();

}
