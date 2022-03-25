package com.partick.authservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.partick.authservice.mapper.UserMapper;
import com.partick.authservice.pojo.User;
import com.partick.authservice.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author partick_peng
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class AuthServiceImpl implements AuthService {

    @Resource
    private UserMapper userMapper;

    /**
     * 用户认证
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User auth(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", password);
        User user = userMapper.selectOne(queryWrapper);
        if (user==null) {
            throw new SecurityException("用户名或密码错误");
        }
        return user;
    }
}
