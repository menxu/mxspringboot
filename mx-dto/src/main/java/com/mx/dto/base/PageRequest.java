package com.mx.dto.base;

import lombok.Setter;

/**
 * Created by menxu on 18/6/29.
 */
@Setter
public class PageRequest<T> {
    public Integer getPage() {
        return page == null ? 1 : page;
    }

    public Integer getPerPage() {
        return perPage == null ? 20 : perPage;
    }

    private Integer page;
    private Integer perPage;

    private T q;

    public T getQ() {
        return q;
    }
}
