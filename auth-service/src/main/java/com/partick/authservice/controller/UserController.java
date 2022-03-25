package com.partick.authservice.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.partick.authservice.dto.ResponseObject;
import com.partick.authservice.pojo.User;
import com.partick.authservice.service.AuthService;
import com.partick.authservice.utils.JWTUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author partick_peng
 */
@RestController
public class UserController {
    @Resource
    private AuthService authService;

    @Resource
    private JWTUtil jwtUtil;

    @PostMapping("/auth")
    public ResponseObject<Map> auth(String username, String password) {
        Map data= new LinkedHashMap<>();
        try {
            User user = authService.auth(username, password);
            data.put("user", user);

            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            //JWT不应该包含敏感信息
            user.setPassword(null);
            String userJson = mapper.writeValueAsString(user);
            String token = jwtUtil.createToken(userJson);
            data.put("token", token);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseObject<Map>(e.getClass().getSimpleName(), e.getMessage());
        }
        return new ResponseObject<Map>(data);
    }
}
