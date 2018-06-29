package com.mx.dto.base;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by menxu on 18/6/28.
 */
@Data
@Accessors(chain = true)
public class Response<T> {
    private int status;
    private String msg;
    private T data;

    public static <T> Response<T> ok(T data) {
        Response<T> response = new Response<T>();
        response.setData(data);
        response.setStatus(StatusCode.SUCCESS.getCode());
        response.setMsg(StatusCode.SUCCESS.getDesc());
        return response;
    }

    public static <T> Response<T> okMsg(T data,String msg) {
        Response<T> response = new Response<T>();
        response.setData(data);
        response.setMsg(msg);
        response.setStatus(StatusCode.SUCCESS.getCode());
        return response;
    }

    public static Response fail() {
        Response response = new Response();
        response.setStatus(StatusCode.FAIL.getCode());
        response.setMsg(StatusCode.FAIL.getDesc());
        return response;
    }

    public static Response fail(StatusCode statusCode) {
        Response response = new Response();
        response.setStatus(statusCode.getCode());
        response.setMsg(statusCode.getDesc());
        return response;
    }

    public static Response failMsg(String msg) {
        Response response = new Response();
        response.setStatus(StatusCode.FAIL.getCode());
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<PageData<T>> pageResponse(int pages, int perPage, long totalCount, int currentPage, int currentCount, T data) {
        Response<PageData<T>> response = new Response<>();
        response.setStatus(StatusCode.SUCCESS.getCode());
        response.setMsg(StatusCode.SUCCESS.getDesc());

        PageData<T> pageData = new PageData<>();

        PageData.Extra extra = pageData.new Extra();
        extra.setPages(pages);
        extra.setPerPage(perPage);
        extra.setCurrentPage(currentPage);
        extra.setCurrentCount(currentCount);
        extra.setTotalCount(totalCount);

        pageData.setExtra(extra);
        pageData.setList(data);

        response.setData(pageData);
        return response;
    }
}
