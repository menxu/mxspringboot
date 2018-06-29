package com.mx.dto.exceptions;

/**
 * Created by menxu on 18/6/28.
 */
public class AuthException extends RuntimeException{
    private String msg;

    public AuthException(String msg) {
        this.msg = msg;
    }
}
