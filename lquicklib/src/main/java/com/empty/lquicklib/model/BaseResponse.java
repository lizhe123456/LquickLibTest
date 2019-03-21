package com.empty.lquicklib.model;

/**
 * Company 武汉麦诺软创
 * Created by lizhe on 2018/8/20.
 */

public class BaseResponse<T> {

   private String Message;

   private int Status;

   private T Result;

   private String ServerTime;


    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public T getResult() {
        return Result;
    }

    public void setResult(T result) {
        Result = result;
    }

    public String getServerTime() {
        return ServerTime;
    }

    public void setServerTime(String serverTime) {
        ServerTime = serverTime;
    }
}
