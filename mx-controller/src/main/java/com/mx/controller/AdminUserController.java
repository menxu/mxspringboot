package com.mx.controller;

import com.mx.dto.admin_user.AdminUserDetail;
import com.mx.dto.admin_user.AdminUserQuery;
import com.mx.dto.base.PageData;
import com.mx.dto.base.PageListData;
import com.mx.dto.base.PageRequest;
import com.mx.dto.base.Response;
import com.mx.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by menxu on 18/6/29.
 */
@RestController
@RequestMapping("/admin_users")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(value = "/list", name = "用户列表")
    public Response<PageData<List<AdminUserDetail>>> list(@RequestBody PageRequest<AdminUserQuery> pageRequest) {
        PageListData<AdminUserDetail> data = adminUserService.selectAll(pageRequest.getPage(), pageRequest.getPerPage(), pageRequest.getQ());
        Response<PageData<List<AdminUserDetail>>> response = Response.pageResponse(data.getPages(), pageRequest.getPerPage(), data.getTotal(), pageRequest.getPage(), data.getCurrentCount(), data.getData());
        return response;
    }

    @RequestMapping(value = "/list2", name = "用户列表")
    public Response<PageData<List<AdminUserDetail>>> list2() {
        PageRequest pageRequest = new PageRequest();
        PageListData<AdminUserDetail> data = adminUserService.selectAll(pageRequest.getPage(), pageRequest.getPerPage(), new AdminUserQuery());
        Response<PageData<List<AdminUserDetail>>> response = Response.pageResponse(data.getPages(), pageRequest.getPerPage(), data.getTotal(), pageRequest.getPage(), data.getCurrentCount(), data.getData());
        return response;
    }
}
