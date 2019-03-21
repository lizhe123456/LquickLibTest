package com.empty.lquicklib.error;

/**
 * Created by lizhe on 2019/3/21.
 *
 */

public class ApiException extends Exception {
    private int code;
    private String displayMessage;

    public ApiException(Throwable throwable) {
        super(throwable);
    }

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }
}
