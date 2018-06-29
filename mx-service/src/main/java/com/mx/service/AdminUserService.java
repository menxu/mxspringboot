package com.mx.service;

import com.mx.dto.admin_user.AdminUserDetail;
import com.mx.dto.admin_user.AdminUserQuery;
import com.mx.dto.base.PageListData;

/**
 * Created by menxu on 18/6/29.
 */
public interface AdminUserService {
    PageListData<AdminUserDetail> selectAll(Integer page, Integer perPage, AdminUserQuery q);
}
