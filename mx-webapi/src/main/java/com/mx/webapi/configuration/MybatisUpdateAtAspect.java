package com.mx.webapi.configuration;

import com.mx.common.date.GetUTCTimeUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Created by menxu on 18/6/28.
 */
@Aspect
@Component
public class MybatisUpdateAtAspect {
    private static Logger logger = LoggerFactory.getLogger(MybatisUpdateAtAspect.class);

    @Pointcut("execution(* com.mx.dao.*.*(..))")
    public void daoAspect() {}

    @Before("daoAspect()")
    public void setUpdateAt(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        Object[] args = point.getArgs();

        String insertPattern = ".*insert.*";
        String updatePattern = ".*update.*";

        Boolean isMatchinseart = Pattern.matches(insertPattern, methodName);
        Boolean isMatchUpdate = Pattern.matches(updatePattern, methodName);

        if ((isMatchinseart || isMatchUpdate ) && args.length == 1) {
            Class T = signature.getParameterTypes()[0];
            Object o = args[0];

            try {
                if (isMatchinseart) {
                    Method setcreatedAtMethod = T.getDeclaredMethod("setCreatedAt", LocalDateTime.class);
                    setcreatedAtMethod.invoke(o, GetUTCTimeUtil.getUTCTime());
                }
                Method setUpdateAtMethod = T.getDeclaredMethod("setUpdatedAt", LocalDateTime.class);
                setUpdateAtMethod.invoke(o, GetUTCTimeUtil.getUTCTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
