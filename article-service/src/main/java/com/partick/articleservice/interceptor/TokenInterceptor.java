package com.partick.articleservice.interceptor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.partick.articleservice.annotation.JwtToken;
import com.partick.articleservice.db.pojo.User;
import com.partick.articleservice.dto.ResponseObject;
import com.partick.articleservice.utils.JWTUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 执行目标URL方法前，对方法中的JWT执行校验
 * @author partick_peng
 */
public class TokenInterceptor implements HandlerInterceptor {

    @Resource
    private JWTUtil jwtUtil;

    /**
     * 对Controller请求做前置处理
     *
     * @param request  原生请求对象
     * @param response 原生响应对象
     * @param handler  处理器对象
     * @return true -放行 false -立即响应
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //获取目标方法的Method对象
        Method method = handlerMethod.getMethod();

        response.setContentType("text/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //判断目标方法是否包含JwtToken注解
        if (method.isAnnotationPresent(JwtToken.class)) {
            String token = request.getHeader("token");

            if (token==null) {
                JwtToken jwtToken = method.getAnnotation(JwtToken.class);
                if (jwtToken.required()) {
                    response.setStatus(401);
                    ResponseObject<Object> responseObject = new ResponseObject<>("SecurityException","token不村子，请检查请求头是否包含token");
                    String json = mapper.writeValueAsString(responseObject);
                    response.getWriter().write(json);
                    return false;
                }
            }else {
                try {
                    String userJson = jwtUtil.checkJwt(token);
                    System.out.println(userJson);
                    User user = mapper.readValue(userJson, User.class);
                    request.setAttribute("userInfo", user);
                    return true;
                } catch (JsonParseException e) {
                    e.printStackTrace();
                    response.setStatus(500);
                    ResponseObject<Object> responseObject = new ResponseObject<>(e.getClass().getSimpleName(), e.getMessage());
                    String json = mapper.writeValueAsString(responseObject);
                    response.getWriter().write(json);
                    return false;
                } catch (JwtException e) {
                    e.printStackTrace();
                    response.setStatus(401);
                    ResponseObject<Object> responseObject = new ResponseObject<>(e.getClass().getSimpleName(), e.getMessage());
                    String json = mapper.writeValueAsString(responseObject);
                    response.getWriter().write(json);
                    return false;
                }
            }
        }
        return true;
    }
}
