package com.mx.dto.base;

import lombok.Data;

import java.util.List;

/**
 * Created by menxu on 18/6/29.
 */
@Data
public class PageListData<T> {
    public List<T> data;
    public long total;
    public int pages;
    public int currentCount;

    public int getCurrentCount() {
        return data.size();
    }
}
