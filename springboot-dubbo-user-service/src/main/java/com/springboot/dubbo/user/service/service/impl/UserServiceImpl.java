package com.springboot.dubbo.user.service.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.springboot.dubbo.user.api.entity.User;
import com.springboot.dubbo.user.api.enumeration.UserStatus;
import com.springboot.dubbo.user.api.service.UserService;
import com.springboot.dubbo.user.service.dao.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {
	
    @Autowired
    private UserMapper userMapper;
    
    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
    	log.info("根据用户名[{}]查询用户", username);
        return userMapper.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByPhone(String phone) {
        return userMapper.findByPhone(phone);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
    
    @Override
    @Transactional
    public void save(User User) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //对密码进行加密
        User.setPassword(passwordEncoder.encode(User.getPassword()));
        User.setRegTime(LocalDateTime.now());
        //设置用户状态为未激活
        User.setUserStatus(UserStatus.UNACTIVATED.getCode()+"");
        userMapper.insert(User);
    }

    @Override
    @Transactional
    @CacheEvict(value = "User",allEntries = true)
    public void update(User User) {
        userMapper.updateByPrimaryKeySelective(User);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "User",allEntries = true)
    public void resetPassword(Long id,String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User User = new User();
        User.setId(id);
        User.setPassword(passwordEncoder.encode(newPassword));
        userMapper.updateByPrimaryKeySelective(User);
    }

    @Override
    public PageInfo<User> findAll(int pageNum, int pageSize) {
        return userMapper.findAll(pageNum,pageSize).toPageInfo();
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

}
