package com.empty.apppay;

/**
 * Created by lizhe on 2019/3/21.
 * 支付回调
 */

public interface PayCallback {

    void onSuccess(String st);

    void onFailure(int code, String errorMsg);

}
