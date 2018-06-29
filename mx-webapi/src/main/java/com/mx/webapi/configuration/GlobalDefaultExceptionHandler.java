package com.mx.webapi.configuration;

import com.mx.dto.base.Response;
import com.mx.dto.base.StatusCode;
import com.mx.dto.exceptions.AuthException;
import com.mx.dto.exceptions.SignInException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by menxu on 18/6/28.
 */
@RestController
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @Autowired
    private Logger logger;

    @ExceptionHandler(value = {AuthException.class})
    @ResponseBody
    public Response handleAuthException(AuthException e) {
        return Response.fail(StatusCode.NOPERMISSION);
    }

    @ExceptionHandler(value = {SignInException.class})
    @ResponseBody
    public Response handleSignInException(SignInException e) {
        return Response.fail(StatusCode.AUTHFAIL);
    }

    @ExceptionHandler(value = {Exception.class, NoHandlerFoundException.class})
    @ResponseBody
    public Response Handler(HttpServletRequest httpServletRequest, Exception e) {
        logger.error("请求出错:" + httpServletRequest.getRequestURL(), e);
        return Response.fail(StatusCode.FAIL);
    }

}
