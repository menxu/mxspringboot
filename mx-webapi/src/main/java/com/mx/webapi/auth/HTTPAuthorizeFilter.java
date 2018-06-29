package com.mx.webapi.auth;

import com.alibaba.fastjson.JSON;
import com.mx.dto.base.Response;
import com.mx.dto.base.StatusCode;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by menxu on 18/6/28.
 */
public class HTTPAuthorizeFilter implements Filter {

    @Autowired
    private Logger logger;

    private final static Set<String> NOT_NEED_LOGIN = new HashSet<>();

    static {
        NOT_NEED_LOGIN.add("/users/sign_in");
        NOT_NEED_LOGIN.add("/orders/callBack");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Content-Type,Auth-token,Auth-name");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");

        if (httpServletRequest.getMethod().equalsIgnoreCase("options")) {
            return;
        }

        String url = httpServletRequest.getRequestURI();
        logger.info("请求url: " + url);

        if (NOT_NEED_LOGIN.stream().anyMatch(s->s.equalsIgnoreCase(url)||url.contains(s))) {
            filterChain.doFilter(httpServletRequest,servletResponse);
            return;
        }

//        String token = httpServletRequest.getHeader("Auth-token");
//        String authName = httpServletRequest.getHeader("Auth-name");
//
//        if (StringUtils.isEmpty(token)) {
//            httpServletResponse.getWriter().write(JSON.toJSONString(Response.fail(StatusCode.TOKENISNOTNULL)));
//            logger.info("Auth-token为空");
//            return;
//        }
//
//        if (StringUtils.isEmpty(authName)) {
//            httpServletResponse.getWriter().write(JSON.toJSONString(Response.fail(StatusCode.AUTHNAMEISNOTNULL)));
//            logger.info("Auth-name为空");
//            return;
//        }

        MyHttpServletRequestWrapper httpServletRequestWrapper = new MyHttpServletRequestWrapper(httpServletRequest);
        httpServletRequestWrapper.putParameter("testPutParam", new String[]{"test put param"});

        filterChain.doFilter(httpServletRequestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
