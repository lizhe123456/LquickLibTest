package com.empty.lquicklibtest.api;

import com.empty.lquicklib.model.BaseResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by lizhe on 2019/4/25.
 */

public interface ApiSerice {

    @GET("api/home/listHotSearch")
    Flowable<BaseResponse> get();

}
