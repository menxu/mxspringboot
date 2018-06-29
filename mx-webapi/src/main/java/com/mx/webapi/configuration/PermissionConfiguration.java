package com.mx.webapi.configuration;

import com.mx.dto.exceptions.AuthException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by menxu on 18/6/28.
 */
@Aspect
@Component
public class PermissionConfiguration {
    private static Logger logger = LoggerFactory.getLogger(PermissionConfiguration.class);

    private final static Set<String> NOT_NEED_PERMISSIONS = new HashSet<>();

    static {
        NOT_NEED_PERMISSIONS.add("/users/sign_out");
        NOT_NEED_PERMISSIONS.add("/users/sign_in");
    }

    @Pointcut("execution(* com.mx.controller.*.*(..))")
    public void permissionPointcut() { }

    @Before("permissionPointcut()")
    public void loggerBefore(JoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String token = request.getHeader("Auth-token");
        String authName = request.getHeader("Auth-name");

        String path = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

        logger.info("permissionPointcut requestString match path: " + path);

        if (NOT_NEED_PERMISSIONS.stream().anyMatch(s -> s.equalsIgnoreCase(path) || path.contains(s))) {
            return;
        }

        if ((token == null || authName == null) && false) {
            logger.info(authName + ",没有找到该用户");
            throw new AuthException("没有用户");
        }
    }

}
