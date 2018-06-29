package com.mx.webapi.configuration;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by menxu on 18/6/28.
 */
@Aspect
@Component
public class ControllerLogInterceptor {

    private static Logger logger = LoggerFactory.getLogger(ControllerLogInterceptor.class);


    @Pointcut("execution(* com.mx.controller.*.*(..))")
    public void controllerMethodPointcut() {
    }

    @Around("controllerMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint point) throws Throwable {
        StringBuilder sb = new StringBuilder();

        MethodSignature signature = (MethodSignature) point.getSignature();

        Method method = signature.getMethod();
        Object[] args = point.getArgs();

        sb.append("Class Name: ").append(method.getDeclaringClass().getName()).append('\n')
                .append("Method Name: ").append(method.getName()).append('\n')
                .append("Start Time: ").append(LocalDateTime.now()).append('\n');


        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        try {
            ServletInputStream list = request.getInputStream();
            if (!(Objects.equals(method.getDeclaringClass().getName(), "com.mx.controller.PolicyController")
                    && Objects.equals(method.getName(), "callBack"))) {
                for (Object arg : args) {
                    sb.append("Request Text: ").append(JSON.toJSONString(arg)).append('\n');
                }
            }
        } catch (IOException e) {
            logger.error("Ignored exceptions: ", e);
        }

        StopWatch sw = new StopWatch();
        sw.start();
        Object result = null;
        try {
            result = point.proceed();
            sb.append("Response Text: ").append(JSON.toJSONString(result)).append('\n');
        } catch (Throwable e) {
            sb.append("Call exceptions: ").append(e).append('\n');
            logger.error("Call exceptions: ", e);
            throw e;
        } finally {
            sw.stop();
            sb.append("Duration: ").append(sw.getTotalTimeMillis()).append('\n');
            logger.info(sb.toString());
        }

        return result;
    }

}
