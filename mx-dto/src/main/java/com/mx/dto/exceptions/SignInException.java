package com.mx.dto.exceptions;

/**
 * Created by menxu on 18/6/28.
 */
public class SignInException extends RuntimeException {
    private String msg;

    public SignInException(String msg) {
        this.msg = msg;
    }
}
