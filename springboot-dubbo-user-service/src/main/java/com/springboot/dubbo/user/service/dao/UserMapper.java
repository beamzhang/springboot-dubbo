package com.springboot.dubbo.user.service.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.springboot.dubbo.user.api.entity.User;

@Mapper
public interface UserMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User findByUsername(@Param("username") String username);
    
    User findByPhone(@Param("phone") String phone);
    
    Page<User> findAll(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
    
    User findByEmail(@Param("email") String email);
}