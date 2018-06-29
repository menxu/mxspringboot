package com.mx.webapi.auth;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by menxu on 18/6/28.
 */
public class MyHttpServletRequestWrapper extends javax.servlet.http.HttpServletRequestWrapper {

    private final Map<String, String[]> parameters;


    public MyHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        Map<String, String[]> t = super.getParameterMap();
        parameters = new HashMap<>(t);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return Collections.unmodifiableMap(parameters);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Enumeration<String> names = new Enumeration<String>() {
            private Iterator<String> iterator = new HashSet<>(parameters.keySet()).iterator();

            @Override
            public boolean hasMoreElements() {
                return iterator.hasNext();
            }

            @Override
            public String nextElement() {
                return iterator.next();
            }
        };

        return names;
    }

    @Override
    public String[] getParameterValues(String name) {
        return parameters.get(name);
    }

    @Override
    public String getParameter(String name) {
        String[] value = parameters.get(name);
        return value != null && value.length > 0 ? value[0] : null;
    }

    public void putParameter(String key, String[] values) {
        parameters.put(key, values);
    }
}
