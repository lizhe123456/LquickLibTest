package com.empty.lquicklib.error;

/**
 * Created by lizhe on 2019/3/21.
 */

public class SysException extends Exception {

    private String code;

    public SysException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

}
