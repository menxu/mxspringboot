package com.mx.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mx.dao.mapper.DbAdminUserMapper;
import com.mx.dto.admin_user.AdminUserDetail;
import com.mx.dto.admin_user.AdminUserQuery;
import com.mx.dto.base.PageListData;
import com.mx.model.DbAdminUser;
import com.mx.model.DbAdminUserSearch;
import com.mx.service.AdminUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by menxu on 18/6/29.
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private DbAdminUserMapper dbAdminUserMapper;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PageListData<AdminUserDetail> selectAll(Integer page, Integer perPage, AdminUserQuery q) {
        PageHelper.startPage(page, perPage);

        List<DbAdminUser> dbAdminUserList = dbAdminUserMapper.selectAll(modelMapper.map(q, DbAdminUserSearch.class));
        Page<DbAdminUser> pageData = (Page<DbAdminUser>) dbAdminUserList;
        long total = pageData.getTotal();
        int pages = pageData.getPages();

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<DbAdminUser, AdminUserDetail>() {
            @Override
            protected void configure() {
                map().setCreatedAt(source.getCreatedAt());
            }
        });
        List<AdminUserDetail> adminUsers = modelMapper.map(dbAdminUserList, new TypeToken<List<AdminUserDetail>>() {}.getType());

        PageListData<AdminUserDetail> listData = new PageListData<>();
        listData.setData(adminUsers);
        listData.setTotal(total);
        listData.setPages(pages);

        return listData;
    }
}
