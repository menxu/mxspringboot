package com.mx.dto.base;

import lombok.Getter;

/**
 * Created by menxu on 18/6/28.
 */
@Getter
public enum StatusCode {
    SUCCESS(1, "成功"),
    FAIL(-1, "失败"),
    TOKENISNOTNULL(-6, "Auth-token不能为空"),
    AUTHNAMEISNOTNULL(-7, "Auth-name不能为空"),

    NOPERMISSION(-7777, "用户没有权限"),
    AUTHFAIL(-8888, "用户授权信息验证失败")
    ;

    private int code;
    private String desc;

    StatusCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
