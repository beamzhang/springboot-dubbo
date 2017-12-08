package com.springboot.dubbo.user.api.service;


import com.github.pagehelper.PageInfo;
import com.springboot.dubbo.user.api.entity.User;

public interface UserService {
    User findByUsername(String username);
    User findByPhone(String phone);
    User findById(Long id);
    void save(User User);
    void update(User User);
    PageInfo<User> findAll(int pageNum, int pageSize);
    User findByEmail(String email);
    void resetPassword(Long id, String newPassword);
}
