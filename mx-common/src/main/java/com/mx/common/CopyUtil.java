package com.mx.common;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by menxu on 18/6/28.
 */
public class CopyUtil {
    public static <T extends Object> List<T> list(Class<? extends Object> t, List<? extends Object> sources) {
        List<T> ts = new ArrayList<T>();

        sources.forEach((e) -> {
            T m = null;
            try {
                m = (T) t.newInstance();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
            BeanUtils.copyProperties(e, m);
            ts.add(m);
        });
        return ts;
    }
}
