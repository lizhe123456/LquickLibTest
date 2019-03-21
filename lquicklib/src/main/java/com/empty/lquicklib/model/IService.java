package com.empty.lquicklib.model;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by lizhe on 2019/3/21.
 *
 */

public interface IService {

    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @Headers({HEADER_API_VERSION})
    @GET("/users")
    Observable<List> getData(@Query("since") int lastIdQueried, @Query("per_page") int perPage);

}
