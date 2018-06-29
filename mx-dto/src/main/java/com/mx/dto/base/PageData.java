package com.mx.dto.base;

import com.github.pagehelper.Page;
import com.mx.common.CopyUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by menxu on 18/6/28.
 */
@Data
public class PageData<T> {
    private Extra extra;
    private T list;

    @Data
    @Accessors(chain = true)
    public class Extra {
        private int pages;
        private int perPage;
        private long totalCount;
        private int currentPage;
        private int currentCount;
    }

    public static <T, E> PageData<List<E>> getByList(List<T> daoList, Class<E> dtoClass) {
        PageData<List<E>> pageData = new PageData<>();

        List<E> datas = CopyUtil.list(dtoClass, daoList);
        Page<T> pageList = (Page<T>) daoList;

        PageData.Extra extra = pageData.new Extra();
        extra.setPages(pageList.getPages());
        extra.setPerPage(pageList.getPageSize());
        extra.setCurrentPage(pageList.getPageNum());
        extra.setCurrentCount(pageList.size());
        extra.setTotalCount(pageList.getTotal());

        pageData.setExtra(extra);
        pageData.setList(datas);

        return pageData;
    }

}
