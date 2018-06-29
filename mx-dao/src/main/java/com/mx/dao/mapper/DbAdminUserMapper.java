package com.mx.dao.mapper;

import com.mx.model.DbAdminUser;
import com.mx.model.DbAdminUserSearch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DbAdminUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DbAdminUser record);

    int insertSelective(DbAdminUser record);

    DbAdminUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DbAdminUser record);

    int updateByPrimaryKey(DbAdminUser record);

    List<DbAdminUser> selectByNameAndEmail(@Param("name") String name, @Param("email") String email);

    List<DbAdminUser> selectAll(DbAdminUserSearch map);
}