package com.partick.authservice.service;

import com.partick.authservice.pojo.User;

/**
 * @author partick_peng
 */
public interface AuthService {

    /**
     * 用户认证
     * @param username
     * @param password
     * @return
     */
    User auth(String username, String password);
}
